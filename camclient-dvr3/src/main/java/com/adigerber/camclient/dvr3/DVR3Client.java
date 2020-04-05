package com.adigerber.camclient.dvr3;

import com.adigerber.camclient.core.events.ServerEvent;
import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.core.exceptions.CamClientIOException;
import com.adigerber.camclient.core.net.CameraClient;
import com.adigerber.camclient.core.util.NetworkUtil;
import com.adigerber.camclient.dvr3.msg.ClientCommand;
import com.adigerber.camclient.dvr3.msg.ServerCommand;
import com.adigerber.camclient.dvr3.msg.client.LoginRequest;
import com.adigerber.camclient.dvr3.msg.server.DeviceInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class DVR3Client extends CameraClient {
    private ByteChannel channel;
    private DeviceInfo deviceInfo = new DeviceInfo();

    private static final int MESSAGE_HEADER = 0x31313131;
    private static final int PACK_FLAG = 0x4B434150; // "PACK" in little endian
    private static final Logger logger = LogManager.getLogger(DVR3Client.class);

    public DVR3Client(ByteChannel channel) {
        this.channel = channel;
    }

    @Override
    public void init() throws CamClientException {
        deviceInfo.deserialize(channel);
        logger.debug("DeviceInfo: {}", deviceInfo);
    }

    @Override
    public void login(String username, String password) throws CamClientException {
        LoginRequest request = new LoginRequest(username, password);
        logger.debug("Requesting login");
        writeCommand(request);
    }

    @Override
    public void disconnect() {
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServerEvent[] readEvents() throws CamClientException {
        ServerCommand command = readMessage();

        if (command == null) {
            return ServerEvent.empty;
        }

        return command.toEvents();
    }

    private ByteBuffer createCommandHeader(ClientCommand command, int size) {
        if (command == null) {
            return NetworkUtil.emptyBuffer;
        }

        ByteBuffer buffer = NetworkUtil.newLEByteBuffer(16)
            .putInt(command.getType())
            .putInt(command.getCommandId())
            .putInt(command.getVersion())
            .putInt(size)
            .flip();
        return buffer;
    }

    private void writeCommand(ClientCommand command) throws CamClientIOException {
        ByteBuffer commandBuffer = command == null ? NetworkUtil.emptyBuffer : command.serialize();
        ByteBuffer dataBuffer = createCommandHeader(command, commandBuffer.remaining());
        ByteBuffer headBuffer = NetworkUtil.newLEByteBuffer(8)
            .putInt(MESSAGE_HEADER)
            .putInt(dataBuffer.remaining() + commandBuffer.remaining())
            .flip();

        try {
            channel.write(headBuffer);
            channel.write(dataBuffer);
            channel.write(commandBuffer);
        } catch (IOException e) {
            throw new CamClientIOException("Error writing command", e);
        }
    }

    private ServerCommand readMessage() throws CamClientException {
        ByteBuffer headBuffer = NetworkUtil.read(channel, 8);
        int header = headBuffer.getInt();

        if (header != MESSAGE_HEADER) {
            logger.error("Potentially malformed data read: DVR3 message got header {} (expected {})", header, MESSAGE_HEADER);
            // TODO: Maybe disconnect?
        }

        int dataSize = headBuffer.getInt();

        if (dataSize == 0) {
            return null;
        }

        ByteBuffer dataBuffer = NetworkUtil.read(channel, dataSize);

        if (dataBuffer.getInt(0) == PACK_FLAG) {
            logger.warn("PACK commands are not yet supported.");
            return null;
        } else {
            return readCommand(dataBuffer);
        }
    }

    private ServerCommand readCommand(ByteBuffer buffer) throws CamClientException {
        if (buffer.remaining() < 16) {
            logger.error("Malformed data read: Command message has only {} bytes available, expected at least 16", buffer.remaining());
            // TODO: maybe throw an exception?
            return null;
        }

        int type = buffer.getInt();
        int id = buffer.getInt();
        int version = buffer.getInt();
        int length = buffer.getInt();

        if (length != buffer.remaining()) {
            logger.error("Malformed data read: Command message length is {} but buffer has {} bytes remaining", length, buffer.remaining());
            // TODO: maybe throw an exception?
            return null;
        }

        logger.info("Got command type {} id {} version {} len {}", type, id, version, length);
        ServerCommand command = ServerCommandFactory.read(type, id, version, buffer);
        logger.always().log(command);

        if (buffer.remaining() != 0) {
            logger.warn("Command buffer not exhausted; type = {} (buffer remainder: {})", command.getClass(), buffer.remaining());
        }

        return command;
    }

    DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    void keepAlive() throws CamClientIOException {
        writeCommand(null);
    }
}

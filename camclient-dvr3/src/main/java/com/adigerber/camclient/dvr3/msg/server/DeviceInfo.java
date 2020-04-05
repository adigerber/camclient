package com.adigerber.camclient.dvr3.msg.server;

import com.adigerber.camclient.core.exceptions.CamClientIOException;
import com.adigerber.camclient.core.util.NetworkUtil;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class DeviceInfo {
    private int head;
    private int deviceType;
    private int productType;
    private int networkProtocolVersion;
    private int configVersion;

    public void deserialize(ReadableByteChannel channel) throws CamClientIOException {
        ByteBuffer buffer = NetworkUtil.read(channel, 64);
        head = buffer.getInt();
        deviceType = buffer.getInt();
        productType = buffer.getInt();
        networkProtocolVersion = buffer.getInt();
        configVersion = buffer.getInt();
    }

    public int getHead() {
        return head;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public int getProductType() {
        return productType;
    }

    public int getNetworkProtocolVersion() {
        return networkProtocolVersion;
    }

    public int getConfigVersion() {
        return configVersion;
    }
}

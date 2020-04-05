package com.adigerber.camclient.dvr3;

import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.dvr3.exceptions.UnrecognizedCommandTypeException;
import com.adigerber.camclient.dvr3.msg.ServerCommand;
import com.adigerber.camclient.dvr3.msg.ServerCommandReader;
import com.adigerber.camclient.dvr3.msg.server.LoginFailure;
import com.adigerber.camclient.dvr3.msg.server.LoginSuccess;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerCommandFactory {
    private static Map<Integer, ServerCommandReader> readers;

    static {
        List<ServerCommandReader> readerList = new ArrayList<>();
        readerList.add(new LoginFailure.Reader());
        readerList.add(new LoginSuccess.Reader());
        readers = new HashMap<>();

        for (ServerCommandReader reader : readerList) {
            readers.put(reader.getCommandType(), reader);
        }
    }

    public static ServerCommand read(int type, int id, int version, ByteBuffer buffer) throws CamClientException {
        ServerCommandReader reader = readers.get(type);

        if (reader == null) {
            throw new UnrecognizedCommandTypeException(type, id, version);
        }

        return reader.deserialize(buffer);
    }
}

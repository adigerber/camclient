package com.adigerber.camclient.dvr3.msg;

import java.nio.ByteBuffer;

public interface ServerCommandReader {
    int getCommandType();

    int getCommandId();

    int getCommandVersion();

    ServerCommand deserialize(ByteBuffer buffer);
}

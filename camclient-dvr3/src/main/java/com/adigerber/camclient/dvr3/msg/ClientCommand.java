package com.adigerber.camclient.dvr3.msg;

import java.nio.ByteBuffer;

public interface ClientCommand {
    int getType();

    int getCommandId();

    int getVersion();

    ByteBuffer serialize();
}

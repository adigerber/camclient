package com.adigerber.camclient.dvr3.msg.server;

import com.adigerber.camclient.core.events.LoginEvent;
import com.adigerber.camclient.core.events.ServerEvent;
import com.adigerber.camclient.dvr3.msg.ServerCommand;
import com.adigerber.camclient.dvr3.msg.ServerCommandReader;

import java.nio.ByteBuffer;

public class LoginFailure implements ServerCommand {
    private int errorCode;

    public LoginFailure(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public ServerEvent[] toEvents() {
        return new ServerEvent[] {
            new LoginEvent(false, getErrorCode())
        };
    }

    @Override
    public String toString() {
        return "LoginFailure{" +
            "errorCode=" + errorCode +
            '}';
    }

    public static class Reader implements ServerCommandReader {
        @Override
        public int getCommandType() {
            return 0x00010002;
        }

        @Override
        public int getCommandId() {
            return 0;
        }

        @Override
        public int getCommandVersion() {
            return 4;
        }

        @Override
        public LoginFailure deserialize(ByteBuffer buffer) {
            int errorCode = buffer.getInt();
            return new LoginFailure(errorCode);
        }
    }
}

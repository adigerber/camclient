package com.adigerber.camclient.dvr3.exceptions;

import com.adigerber.camclient.core.exceptions.CamClientException;

public class UnrecognizedCommandTypeException extends CamClientException {
    private final int type;
    private final int id;
    private final int version;

    public UnrecognizedCommandTypeException(int type, int id, int version) {
        super("Received unrecognized command from server: type = " + type + ", ID = " + id + ", version = " + version);
        this.type = type;
        this.id = id;
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}

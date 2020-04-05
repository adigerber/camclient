package com.adigerber.camclient.core.exceptions;

public class UnrecognizedServerTypeException extends CamClientException {
    private int serverType;

    public UnrecognizedServerTypeException(int serverType) {
        super("Camera returned unrecognized server type: " + serverType);
        this.serverType = serverType;
    }

    public int getServerType() {
        return serverType;
    }
}

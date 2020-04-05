package com.adigerber.camclient.core.exceptions;

import com.adigerber.camclient.core.http.models.ServerType;

public class ProtocolImplementationMissingException extends CamClientException {
    private ServerType serverType;

    public ProtocolImplementationMissingException(ServerType serverType) {
        super("Missing protocol implementation for server type " + serverType);
    }
}

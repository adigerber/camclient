package com.adigerber.camclient.core.exceptions;

import java.io.IOException;

public class CamClientIOException extends CamClientException {
    public CamClientIOException(IOException cause) {
        this("An I/O exception has occurred.", cause);
    }

    public CamClientIOException(String message, IOException cause) {
        super(message, cause);
    }
}

package com.adigerber.camclient.core.exceptions;

public class CamClientException extends Exception {
    public CamClientException(String message) {
        super(message);
    }

    public CamClientException(String message, Object... args) {
        super(String.format(message, args));
    }

    public CamClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public CamClientException(Throwable cause) {
        super(cause);
    }
}

package com.adigerber.camclient.core.events;

public class LoginEvent implements ServerEvent {
    private final boolean successful;
    private final int errorCode;

    public LoginEvent(boolean successful, int errorCode) {
        this.successful = successful;
        this.errorCode = errorCode;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "LoginEvent{" +
            "successful=" + successful +
            ", errorCode=" + errorCode +
            '}';
    }
}

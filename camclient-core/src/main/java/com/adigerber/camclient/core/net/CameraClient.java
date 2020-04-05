package com.adigerber.camclient.core.net;

import com.adigerber.camclient.core.events.ServerEvent;
import com.adigerber.camclient.core.exceptions.CamClientException;

public abstract class CameraClient {
    public abstract void init() throws CamClientException;

    public abstract void login(String username, String password) throws CamClientException;

    public abstract void disconnect();

    public abstract ServerEvent[] readEvents() throws CamClientException;
}

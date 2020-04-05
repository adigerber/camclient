package com.adigerber.camclient.core.net;

import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.core.http.models.Discovery;
import com.adigerber.camclient.core.http.models.ServerType;

public interface CameraClientFactory {
    ServerType[] getSupportedServerTypes();

    CameraClient create(Discovery discovery) throws CamClientException;
}

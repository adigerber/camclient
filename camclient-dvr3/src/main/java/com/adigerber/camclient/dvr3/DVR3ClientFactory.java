package com.adigerber.camclient.dvr3;

import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.core.http.models.Discovery;
import com.adigerber.camclient.core.http.models.ServerType;
import com.adigerber.camclient.core.net.CameraClient;
import com.adigerber.camclient.core.net.CameraClientFactory;
import com.adigerber.camclient.core.util.NetworkUtil;

import java.nio.channels.ByteChannel;

public class DVR3ClientFactory implements CameraClientFactory {
    @Override
    public ServerType[] getSupportedServerTypes() {
        return new ServerType[] { ServerType.DVR3 };
    }

    @Override
    public CameraClient create(Discovery discovery) throws CamClientException {
        ByteChannel channel = NetworkUtil.connect(discovery.getHost(), discovery.getCommandPort());
        return new DVR3Client(channel);
    }
}

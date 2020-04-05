package com.adigerber.camclient.core;

import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.core.exceptions.ProtocolImplementationMissingException;
import com.adigerber.camclient.core.http.Discoverer;
import com.adigerber.camclient.core.http.models.Discovery;
import com.adigerber.camclient.core.net.CameraClient;
import com.adigerber.camclient.core.net.CameraClientFactory;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Arrays;
import java.util.ServiceLoader;

public class CameraConnectorFacade {
    private static ServiceLoader<CameraClientFactory> loader;

    static {
        loader = ServiceLoader.load(CameraClientFactory.class);
    }

    public static CameraClient connect(String host, int httpPort) throws CamClientException {
        Discoverer discoverer = new Discoverer(HttpClientBuilder.create().build());
        Discovery discovery = discoverer.discover(host, httpPort);

        for (CameraClientFactory factory : loader) {
            if (Arrays.asList(factory.getSupportedServerTypes()).contains(discovery.getServerType())) {
                return factory.create(discovery);
            }
        }

        throw new ProtocolImplementationMissingException(discovery.getServerType());
    }
}

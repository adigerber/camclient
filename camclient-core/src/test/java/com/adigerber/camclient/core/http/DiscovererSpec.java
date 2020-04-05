package com.adigerber.camclient.core.http;

import com.adigerber.camclient.core.http.models.Discovery;
import com.adigerber.camclient.core.http.models.ServerType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscovererSpec {
    @Test
    public void discovery_onMockServer_returnsExpectedValues() throws Exception {
        int netPort = 6040;
        ServerType serverType = ServerType.DVR3;

        try (TestHttpServer server = new TestHttpServer()) {
            server.setFile(
                "/server.js",
                "NetPort=" + netPort + System.lineSeparator() +
                    "NetAddr=\"\"" + System.lineSeparator() +
                    "ServerType=" + serverType.getId() + System.lineSeparator() +
                    "bUseNat=false" + System.lineSeparator()
            );

            Discoverer discoverer = new Discoverer(HttpClientBuilder.create().build());
            Discovery discovery = discoverer.discover(server.getHostname(), server.getListeningPort());

            assertEquals(serverType, discovery.getServerType());
            assertEquals(netPort, discovery.getCommandPort());
        }
    }
}

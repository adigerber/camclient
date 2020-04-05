package com.adigerber.camclient.core.http;

import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.core.exceptions.CamClientIOException;
import com.adigerber.camclient.core.exceptions.UnrecognizedServerTypeException;
import com.adigerber.camclient.core.http.models.Discovery;
import com.adigerber.camclient.core.http.models.ServerType;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Discoverer {
    private Executor executor;

    public Discoverer(HttpClient http) {
        this(Executor.newInstance(http));
    }

    public Discoverer(Executor executor) {
        this.executor = executor;
    }

    public Discovery discover(String host, int port) throws CamClientException {
        String url = String.format("http://%s:%d/server.js", host, port);
        String content;

        try {
            content = executor.execute(Request.Get(url))
                .returnContent()
                .asString();
        } catch (IOException e) {
            throw new CamClientIOException("Failed to connect to HTTP server", e);
        }

        Map<String, String> properties = Arrays.stream(content.split(System.lineSeparator()))
            .map(String::trim)
            .map(x -> x.split("=", 2))
            .collect(Collectors.toMap(x -> x[0], x -> x[1]));
        int serverTypeNo = Integer.parseInt(properties.get("ServerType"));
        ServerType serverType = ServerType.byId(serverTypeNo);
        int dataPort;
        int commandPort;

        switch (serverType) {
            case NewCard:
                dataPort = Integer.parseInt(properties.get("DataPort"));
                commandPort = Integer.parseInt(properties.get("CmdPort"));
                break;
            case DVR3:
            case DVR4:
            case NVMS:
            case DVRDVS:
            case IPCamera:
                dataPort = -1;
                commandPort = Integer.parseInt(properties.get("NetPort"));
                break;
            default:
                throw new UnrecognizedServerTypeException(serverTypeNo);
        }

        return new Discovery(serverType, host, dataPort, commandPort);
    }
}

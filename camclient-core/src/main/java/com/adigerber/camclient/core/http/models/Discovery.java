package com.adigerber.camclient.core.http.models;

public class Discovery {
    private final ServerType serverType;
    private final String host;
    private final int dataPort;
    private final int commandPort;

    public Discovery(ServerType serverType, String host, int dataPort, int commandPort) {
        this.serverType = serverType;
        this.host = host;
        this.dataPort = dataPort;
        this.commandPort = commandPort;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public String getHost() {
        return host;
    }

    public int getDataPort() {
        return dataPort;
    }

    public int getCommandPort() {
        return commandPort;
    }

    @Override
    public String toString() {
        return "Discovery{" +
            "serverType=" + serverType +
            ", dataPort=" + dataPort +
            ", commandPort=" + commandPort +
            '}';
    }
}

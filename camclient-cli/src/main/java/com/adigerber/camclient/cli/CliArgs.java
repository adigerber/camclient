package com.adigerber.camclient.cli;

import com.beust.jcommander.Parameter;

public class CliArgs {
    @Parameter(
        names = { "-h", "--host" },
        required = true,
        description = "The camera's hostname/IP address"
    )
    private String host;

    @Parameter(
        names = {"-P", "--port" },
        required = true,
        description = "The camera's HTTP server port"
    )
    private int port;

    @Parameter(
        names = { "-u", "--username" },
        required = true,
        description = "The username used to log into the camera"
    )
    private String username;

    @Parameter(
        names = { "-p", "--password" },
        required = true,
        password = true,
        description = "The password used to log into the camera"
    )
    private String password;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

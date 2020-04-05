package com.adigerber.camclient.cli;

import com.adigerber.camclient.core.CameraConnectorFacade;
import com.adigerber.camclient.core.events.ServerEvent;
import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.core.net.CameraClient;
import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] args) {
        CliArgs cliArgs = new CliArgs();
        JCommander.newBuilder()
            .addObject(cliArgs)
            .build()
            .parse(args);

        try {
            CameraClient client = CameraConnectorFacade.connect(cliArgs.getHost(), cliArgs.getPort());
            client.init();
            client.login(cliArgs.getUsername(), cliArgs.getPassword());

            for (ServerEvent event : client.readEvents()) {
                System.out.println(event);
            }
        } catch (CamClientException e) {
            System.err.println("CamClient error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error has occurred.");
            e.printStackTrace();
        }
    }
}

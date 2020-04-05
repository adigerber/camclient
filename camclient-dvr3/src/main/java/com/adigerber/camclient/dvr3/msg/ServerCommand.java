package com.adigerber.camclient.dvr3.msg;

import com.adigerber.camclient.core.events.ServerEvent;

public interface ServerCommand {
    ServerEvent[] toEvents();
}

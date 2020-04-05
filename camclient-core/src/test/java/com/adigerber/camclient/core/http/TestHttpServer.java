package com.adigerber.camclient.core.http;

import fi.iki.elonen.NanoHTTPD;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

public class TestHttpServer extends NanoHTTPD implements Closeable {
    private Map<String, String> files;

    public TestHttpServer() throws Exception {
        super("127.0.0.1", (int)(Math.random() * 20000 + 10000));
        files = new HashMap<>();
        start();
    }

    public void setFile(String fileName, String content) {
        files.put(fileName, content);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String path = session.getUri();

        if (files.containsKey(path)) {
            return newFixedLengthResponse(files.get(path));
        }

        return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not Found: " + path);
    }

    @Override
    public void close() {
        stop();
    }
}

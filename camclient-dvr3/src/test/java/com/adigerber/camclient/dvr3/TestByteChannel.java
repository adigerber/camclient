package com.adigerber.camclient.dvr3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class TestByteChannel implements ByteChannel {
    private ByteArrayInputStream in;
    private ByteArrayOutputStream out;

    public TestByteChannel() {
        out = new ByteArrayOutputStream();
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        int length = dst.remaining();
        byte[] buffer = new byte[length];
        int count = in.read(buffer);
        dst.put(buffer, 0, count);
        return count;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        int length = src.remaining();
        byte[] buffer = new byte[length];
        src.get(buffer);
        out.write(buffer);
        return length;
    }

    public void setInput(byte[] src) {
        in = new ByteArrayInputStream(src);
    }

    public byte[] getOutput() {
        byte[] buffer = out.toByteArray();
        out = new ByteArrayOutputStream();
        return buffer;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
    }
}

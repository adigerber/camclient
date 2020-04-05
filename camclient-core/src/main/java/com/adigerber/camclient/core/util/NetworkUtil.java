package com.adigerber.camclient.core.util;

import com.adigerber.camclient.core.exceptions.CamClientIOException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Provides utility functions for network-related operations.
 */
public class NetworkUtil {
    /**
     * Represents an empty byte buffer which can be used to cause no-op.
     */
    public static final ByteBuffer emptyBuffer = ByteBuffer.allocate(0);

    /**
     * Reads {@code size} bytes from the channel and returns a buffer with the contents.
     * This is a convenience method which wraps {@link IOException}s as {@link CamClientIOException}s.
     *
     * @param channel The channel to read from.
     * @param size    The size of the buffer to allocate and the amount of bytes to read.
     * @return A {@link ByteBuffer} with the bytes that were read from {@code channel}.
     * @throws CamClientIOException if the underlying read operation threw an {@link IOException}, or if reached EOF.
     */
    public static ByteBuffer read(ReadableByteChannel channel, int size) throws CamClientIOException {
        ByteBuffer buffer = newLEByteBuffer(size);

        try {
            int readCount = channel.read(buffer);

            if (readCount == -1) {
                throw new CamClientIOException("Channel read returned EOF", new ClosedChannelException());
            }
        } catch (IOException e) {
            throw new CamClientIOException("Channel read caused an exception to be thrown", e);
        }

        return buffer.flip();
    }

    /**
     * Creates a socket channel connected to the given address.
     *
     * @param host The hostname/IP address of the target.
     * @param port The port of the target.
     * @return A {@link SocketChannel} that's connected to the given address.
     * @throws CamClientIOException if the connection attempt threw an {@link IOException}.
     */
    public static SocketChannel connect(String host, int port) throws CamClientIOException {
        try {
            SocketAddress address = new InetSocketAddress(host, port);
            SocketChannel channel = SocketChannel.open(address);
            return channel;
        } catch (IOException e) {
            throw new CamClientIOException("Failed to connect to " + host + ":" + port, e);
        }
    }

    /**
     * Converts the given string to a byte array of size {@code length}.
     * If {@code length} is greater than the length of the string, the string will be "padded" with zeroes to the right.
     * If {@code length} is less than the length of the string, the string will be truncated.
     *
     * @param str    The string to convert.
     * @param length The length of the byte array.
     * @see #bytesToZeroTerminatedString(byte[])
     */
    public static byte[] stringToFixedLengthBytes(String str, int length) {
        byte[] strBytes = str.getBytes(StandardCharsets.ISO_8859_1);
        byte[] result = new byte[length];
        System.arraycopy(strBytes, 0, result, 0, Math.min(length - 1, strBytes.length));
        return result;
    }

    /**
     * Converts the bytes in the given array, until the first 0 byte, to a string.
     *
     * @param bytes The bytes to convert.
     * @see #stringToFixedLengthBytes(String, int)
     * @see #readFixedLengthString(ByteBuffer, int)
     */
    public static String bytesToZeroTerminatedString(byte[] bytes) {
        int length = 0;

        while (length < bytes.length && bytes[length] != 0) {
            length++;
        }

        return new String(bytes, 0, length);
    }

    /**
     * Reads a fixed-length zero-terminated string from buffer.
     *
     * @param buffer The buffer to read the string from.
     * @param size   The size of the string to read.
     * @see #bytesToZeroTerminatedString(byte[])
     */
    public static String readFixedLengthString(ByteBuffer buffer, int size) {
        byte[] stringBuffer = new byte[size];
        buffer.get(stringBuffer);
        return bytesToZeroTerminatedString(stringBuffer);
    }

    /**
     * Returns a new little-endian {@link ByteBuffer} with the given {@code capacity}.
     * This is a convenience method which should be used instead of {@link ByteBuffer#allocate(int)} and
     * {@link ByteBuffer#order(ByteOrder)}.
     *
     * @param capacity The amount of bytes to allocate in this byte buffer.
     */
    public static ByteBuffer newLEByteBuffer(int capacity) {
        return ByteBuffer.allocate(capacity).order(ByteOrder.LITTLE_ENDIAN);
    }
}

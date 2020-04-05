package com.adigerber.camclient.dvr3;

import com.adigerber.camclient.core.events.LoginEvent;
import com.adigerber.camclient.core.events.ServerEvent;
import com.adigerber.camclient.core.exceptions.CamClientException;
import com.adigerber.camclient.core.exceptions.CamClientIOException;
import com.adigerber.camclient.core.util.NetworkUtil;
import com.adigerber.camclient.dvr3.msg.server.DeviceInfo;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.function.Predicate;

import static java.util.Arrays.stream;
import static org.junit.Assert.*;

public class DVR3ClientSpec {
    @Test(timeout = 1000)
    public void init_withDeviceInfo_readsDeviceInfo() throws CamClientException {
        TestByteChannel channel = new TestByteChannel();
        channel.setInput(new byte[] {
            0x68, 0x65, 0x61, 0x64, 0x00, 0x00, 0x00, 0x00, 0x26, 0x2a, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00,
            0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        });
        DVR3Client dvr3 = new DVR3Client(channel);
        dvr3.init();
        DeviceInfo deviceInfo = dvr3.getDeviceInfo();
        assertNotNull(deviceInfo);
        assertEquals(0x64616568, deviceInfo.getHead());
        assertEquals(0, deviceInfo.getDeviceType());
        assertEquals(10790, deviceInfo.getProductType());
        assertEquals(4, deviceInfo.getNetworkProtocolVersion());
        assertEquals(3, deviceInfo.getConfigVersion());
    }

    @Test
    public void login_withValidValues_writesLoginRequest() throws CamClientException {
        TestByteChannel channel = new TestByteChannel();
        DVR3Client dvr3 = new DVR3Client(channel);
        dvr3.login("my-username", "my-password");
        byte[] expected = toArray(
            NetworkUtil.newLEByteBuffer(4096)
                // message header
                .putInt(0x31313131).putInt(0x00000088)
                // command header
                .putInt(0x00000101).putInt(0x00000000).putInt(0x00000000).putInt(0x00000078)
                // data
                .putInt(0x00000003)
                .putInt(0x00000000)
                .put(new byte[] {
                    // length = 11
                    'm', 'y', '-', 'u', 's', 'e', 'r', 'n', 'a', 'm', 'e',
                    // filler = 25
                    0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0,
                    0,
                })
                .put(new byte[] {
                    // length = 11
                    'm', 'y', '-', 'p', 'a', 's', 's', 'w', 'o', 'r', 'd',
                    // filler = 25
                    0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0,
                    0,
                })
                .put(new byte[28])
                .put(new byte[6])
                .put(new byte[2])
                .putInt(0x00000004)
        );
        assertArrayEquals(expected, channel.getOutput());
    }

    @Test
    public void keepAlive_withMockChannel_writesEmptyMessage() throws CamClientIOException {
        TestByteChannel channel = new TestByteChannel();
        DVR3Client dvr3 = new DVR3Client(channel);
        dvr3.keepAlive();
        byte[] expected = new byte[] {
            0x31, 0x31, 0x31, 0x31, 0x00, 0x00, 0x00, 0x00
        };
        assertArrayEquals(expected, channel.getOutput());
    }

    @Test
    public void readEvents_withLoginFailure_returnsUnsuccessfulLoginEvent() throws CamClientException {
        TestByteChannel channel = new TestByteChannel();
        DVR3Client dvr3 = new DVR3Client(channel);
        int errorCode = 30;
        channel.setInput(toArray(
            NetworkUtil.newLEByteBuffer(4096)
                // message header
                .putInt(0x31313131).putInt(0x00000014)
                // command header
                .putInt(0x00010002).putInt(0x00000000).putInt(0x00000004).putInt(0x00000004)
                // data
                .putInt(errorCode)
        ));
        ServerEvent[] events = dvr3.readEvents();
        assertThat(events, hasEvent(LoginEvent.class, x -> !x.isSuccessful() && x.getErrorCode() == 30));
    }

    @Test
    public void readEvents_withLoginSuccess_returnsSuccessfulLoginEvent() throws CamClientException {
        TestByteChannel channel = new TestByteChannel();
        DVR3Client dvr3 = new DVR3Client(channel);
        channel.setInput(toArray(
            NetworkUtil.newLEByteBuffer(4096)
                // message header
                .putInt(0x31313131).putInt(0x0000016c)
                // command header
                .putInt(0x00010001).putInt(0x00000000).putInt(0x00000004).putInt(0x0000015c)
                // data
                // doesn't really matter, because the data is published in different events.
                .put(new byte[0x15c])
        ));
        ServerEvent[] events = dvr3.readEvents();
        assertThat(events, hasEvent(LoginEvent.class, LoginEvent::isSuccessful));
    }

    private byte[] toArray(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }

    private <T extends ServerEvent> Matcher<ServerEvent[]> hasEvent(Class<T> clazz, Predicate<T> predicate) {
        return new BaseMatcher<>() {
            @Override
            public boolean matches(Object item) {
                return stream((ServerEvent[])item).anyMatch(event -> event.getClass() == clazz && predicate.test((T)event));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Did not match event");
            }
        };
    }
}

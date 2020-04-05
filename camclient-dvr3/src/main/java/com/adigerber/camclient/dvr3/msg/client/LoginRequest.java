package com.adigerber.camclient.dvr3.msg.client;

import java.nio.ByteBuffer;

import com.adigerber.camclient.core.util.NetworkUtil;
import com.adigerber.camclient.dvr3.msg.ClientCommand;

public class LoginRequest implements ClientCommand {
    private int loginType;
    private byte[] ip;
    private String username;
    private String password;
    private String computerName;
    private byte[] mac;
    private byte[] resv;

    public static final int NETWORK_PROTOCOL_VERSION = 4;

    public LoginRequest(String username, String password) {
        this(3, new byte[4], username, password, "", new byte[6], new byte[2]);
    }

    public LoginRequest(int loginType, byte[] ip, String username, String password, String computerName, byte[] mac, byte[] resv) {
        if (ip == null || ip.length != 4) {
            throw new IllegalArgumentException("ip");
        }

        if (username == null) {
            throw new NullPointerException("username");
        }

        if (password == null) {
            throw new NullPointerException("password");
        }

        if (computerName == null) {
            throw new NullPointerException("computerName");
        }

        if (mac == null || mac.length != 6) {
            throw new IllegalArgumentException("mac");
        }

        if (resv == null || resv.length != 2) {
            throw new IllegalArgumentException("resv");
        }

        this.loginType = loginType;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.computerName = computerName;
        this.mac = mac;
        this.resv = resv;
    }

    public int getLoginType() {
        return loginType;
    }

    public byte[] getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getComputerName() {
        return computerName;
    }

    public byte[] getMac() {
        return mac;
    }

    public byte[] getResv() {
        return resv;
    }

    @Override
    public int getType() {
        return 0x00000101;
    }

    @Override
    public int getCommandId() {
        return 0;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public ByteBuffer serialize() {
        ByteBuffer buffer = NetworkUtil.newLEByteBuffer(120)
            .putInt(getLoginType())
            .put(getIp())
            .put(NetworkUtil.stringToFixedLengthBytes(getUsername(), 36))
            .put(NetworkUtil.stringToFixedLengthBytes(getPassword(), 36))
            .put(NetworkUtil.stringToFixedLengthBytes(getComputerName(), 28))
            .put(getMac())
            .put(getResv())
            .putInt(NETWORK_PROTOCOL_VERSION)
            .flip();
        return buffer;
    }
}

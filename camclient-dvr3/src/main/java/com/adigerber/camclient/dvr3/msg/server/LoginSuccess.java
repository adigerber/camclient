package com.adigerber.camclient.dvr3.msg.server;

import com.adigerber.camclient.core.events.LoginEvent;
import com.adigerber.camclient.core.events.ServerEvent;
import com.adigerber.camclient.core.util.NetworkUtil;
import com.adigerber.camclient.dvr3.msg.ServerCommand;
import com.adigerber.camclient.dvr3.msg.ServerCommandReader;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class LoginSuccess implements ServerCommand {
    private int permissionFlags;
    private long liveChannel;
    private long recordChannel;
    private long playbackChannel;
    private long backupChannel;
    private long ptzControlChannel;
    private long remoteViewChannel;
    private byte localVideoInputNo;
    private byte audioInputNo;
    private byte sensorInputNo;
    private byte relayOutputNo;
    private int displayResolutionMask;
    private byte videoOutputNo;
    private byte netVideoOutputNo;
    private byte netVideoInputNo;
    private byte ivsNo;
    private byte presetNumOneChannel;
    private byte cruiseNumOneChannel;
    private byte presetNumOneCruise;
    private byte trackNumOneChannel;
    private byte userNo;
    private byte netClientNo;
    private byte netFirstStreamNo;
    private byte deviceType;
    private byte doblueStream;
    private byte audioStream;
    private byte talkAudio;
    private byte passwordCheck;
    private byte brightness;
    private byte contrast;
    private byte saturation;
    private byte hue;
    private short videoInputNo;
    private short deviceID;
    private int videoFormat;
    private int[] functions;
    private byte[] deviceIP;
    private byte[] deviceMac;
    private int buildDate;
    private int buildTime;
    private String deviceName;
    private String firmwareVersion;
    private String kernelVersion;
    private String hardwareVersion;
    private String mcuVersion;

    public LoginSuccess(int permissionFlags, long liveChannel, long recordChannel, long playbackChannel, long backupChannel, long ptzControlChannel, long remoteViewChannel, byte localVideoInputNo, byte audioInputNo, byte sensorInputNo, byte relayOutputNo, int displayResolutionMask, byte videoOutputNo, byte netVideoOutputNo, byte netVideoInputNo, byte ivsNo, byte presetNumOneChannel, byte cruiseNumOneChannel, byte presetNumOneCruise, byte trackNumOneChannel, byte userNo, byte netClientNo, byte netFirstStreamNo, byte deviceType, byte doblueStream, byte audioStream, byte talkAudio, byte passwordCheck, byte brightness, byte contrast, byte saturation, byte hue, short videoInputNo, short deviceID, int videoFormat, int[] functions, byte[] deviceIP, byte[] deviceMac, int buildDate, int buildTime, String deviceName, String firmwareVersion, String kernelVersion, String hardwareVersion, String mcuVersion) {
        this.permissionFlags = permissionFlags;
        this.liveChannel = liveChannel;
        this.recordChannel = recordChannel;
        this.playbackChannel = playbackChannel;
        this.backupChannel = backupChannel;
        this.ptzControlChannel = ptzControlChannel;
        this.remoteViewChannel = remoteViewChannel;
        this.localVideoInputNo = localVideoInputNo;
        this.audioInputNo = audioInputNo;
        this.sensorInputNo = sensorInputNo;
        this.relayOutputNo = relayOutputNo;
        this.displayResolutionMask = displayResolutionMask;
        this.videoOutputNo = videoOutputNo;
        this.netVideoOutputNo = netVideoOutputNo;
        this.netVideoInputNo = netVideoInputNo;
        this.ivsNo = ivsNo;
        this.presetNumOneChannel = presetNumOneChannel;
        this.cruiseNumOneChannel = cruiseNumOneChannel;
        this.presetNumOneCruise = presetNumOneCruise;
        this.trackNumOneChannel = trackNumOneChannel;
        this.userNo = userNo;
        this.netClientNo = netClientNo;
        this.netFirstStreamNo = netFirstStreamNo;
        this.deviceType = deviceType;
        this.doblueStream = doblueStream;
        this.audioStream = audioStream;
        this.talkAudio = talkAudio;
        this.passwordCheck = passwordCheck;
        this.brightness = brightness;
        this.contrast = contrast;
        this.saturation = saturation;
        this.hue = hue;
        this.videoInputNo = videoInputNo;
        this.deviceID = deviceID;
        this.videoFormat = videoFormat;
        this.functions = functions;
        this.deviceIP = deviceIP;
        this.deviceMac = deviceMac;
        this.buildDate = buildDate;
        this.buildTime = buildTime;
        this.deviceName = deviceName;
        this.firmwareVersion = firmwareVersion;
        this.kernelVersion = kernelVersion;
        this.hardwareVersion = hardwareVersion;
        this.mcuVersion = mcuVersion;
    }

    public int getPermissionFlags() {
        return permissionFlags;
    }

    public long getLiveChannel() {
        return liveChannel;
    }

    public long getRecordChannel() {
        return recordChannel;
    }

    public long getPlaybackChannel() {
        return playbackChannel;
    }

    public long getBackupChannel() {
        return backupChannel;
    }

    public long getPtzControlChannel() {
        return ptzControlChannel;
    }

    public long getRemoteViewChannel() {
        return remoteViewChannel;
    }

    public byte getLocalVideoInputNo() {
        return localVideoInputNo;
    }

    public byte getAudioInputNo() {
        return audioInputNo;
    }

    public byte getSensorInputNo() {
        return sensorInputNo;
    }

    public byte getRelayOutputNo() {
        return relayOutputNo;
    }

    public int getDisplayResolutionMask() {
        return displayResolutionMask;
    }

    public byte getVideoOutputNo() {
        return videoOutputNo;
    }

    public byte getNetVideoOutputNo() {
        return netVideoOutputNo;
    }

    public byte getNetVideoInputNo() {
        return netVideoInputNo;
    }

    public byte getIvsNo() {
        return ivsNo;
    }

    public byte getPresetNumOneChannel() {
        return presetNumOneChannel;
    }

    public byte getCruiseNumOneChannel() {
        return cruiseNumOneChannel;
    }

    public byte getPresetNumOneCruise() {
        return presetNumOneCruise;
    }

    public byte getTrackNumOneChannel() {
        return trackNumOneChannel;
    }

    public byte getUserNo() {
        return userNo;
    }

    public byte getNetClientNo() {
        return netClientNo;
    }

    public byte getNetFirstStreamNo() {
        return netFirstStreamNo;
    }

    public byte getDeviceType() {
        return deviceType;
    }

    public byte getDoblueStream() {
        return doblueStream;
    }

    public byte getAudioStream() {
        return audioStream;
    }

    public byte getTalkAudio() {
        return talkAudio;
    }

    public byte getPasswordCheck() {
        return passwordCheck;
    }

    public byte getBrightness() {
        return brightness;
    }

    public byte getContrast() {
        return contrast;
    }

    public byte getSaturation() {
        return saturation;
    }

    public byte getHue() {
        return hue;
    }

    public short getVideoInputNo() {
        return videoInputNo;
    }

    public short getDeviceID() {
        return deviceID;
    }

    public int getVideoFormat() {
        return videoFormat;
    }

    public int[] getFunctions() {
        return functions;
    }

    public byte[] getDeviceIP() {
        return deviceIP;
    }

    public byte[] getDeviceMac() {
        return deviceMac;
    }

    public int getBuildDate() {
        return buildDate;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public String getMcuVersion() {
        return mcuVersion;
    }

    @Override
    public ServerEvent[] toEvents() {
        return new ServerEvent[] {
            new LoginEvent(true, 0)
        };
    }

    @Override
    public String toString() {
        return "LoginSuccess{" +
            "permissionFlags=" + permissionFlags +
            ", liveChannel=" + liveChannel +
            ", recordChannel=" + recordChannel +
            ", playbackChannel=" + playbackChannel +
            ", backupChannel=" + backupChannel +
            ", ptzControlChannel=" + ptzControlChannel +
            ", remoteViewChannel=" + remoteViewChannel +
            ", localVideoInputNo=" + localVideoInputNo +
            ", audioInputNo=" + audioInputNo +
            ", sensorInputNo=" + sensorInputNo +
            ", relayOutputNo=" + relayOutputNo +
            ", displayResolutionMask=" + displayResolutionMask +
            ", videoOutputNo=" + videoOutputNo +
            ", netVideoOutputNo=" + netVideoOutputNo +
            ", netVideoInputNo=" + netVideoInputNo +
            ", ivsNo=" + ivsNo +
            ", presetNumOneChannel=" + presetNumOneChannel +
            ", cruiseNumOneChannel=" + cruiseNumOneChannel +
            ", presetNumOneCruise=" + presetNumOneCruise +
            ", trackNumOneChannel=" + trackNumOneChannel +
            ", userNo=" + userNo +
            ", netClientNo=" + netClientNo +
            ", netFirstStreamNo=" + netFirstStreamNo +
            ", deviceType=" + deviceType +
            ", doblueStream=" + doblueStream +
            ", audioStream=" + audioStream +
            ", talkAudio=" + talkAudio +
            ", passwordCheck=" + passwordCheck +
            ", brightness=" + brightness +
            ", contrast=" + contrast +
            ", saturation=" + saturation +
            ", hue=" + hue +
            ", videoInputNo=" + videoInputNo +
            ", deviceID=" + deviceID +
            ", videoFormat=" + videoFormat +
            ", functions=" + Arrays.toString(functions) +
            ", deviceIP=" + Arrays.toString(deviceIP) +
            ", deviceMac=" + Arrays.toString(deviceMac) +
            ", buildDate=" + buildDate +
            ", buildTime=" + buildTime +
            ", deviceName='" + deviceName + '\'' +
            ", firmwareVersion='" + firmwareVersion + '\'' +
            ", kernelVersion='" + kernelVersion + '\'' +
            ", hardwareVersion='" + hardwareVersion + '\'' +
            ", mcuVersion='" + mcuVersion + '\'' +
            '}';
    }

    public static class Reader implements ServerCommandReader {
        @Override
        public int getCommandType() {
            return 0x00010001;
        }

        @Override
        public int getCommandId() {
            return 0;
        }

        @Override
        public int getCommandVersion() {
            return 4;
        }

        @Override
        public ServerCommand deserialize(ByteBuffer buffer) {
            int permissionFlags = buffer.getInt();
            long liveChannel = buffer.getLong();
            long recordChannel = buffer.getLong();
            long playbackChannel = buffer.getLong();
            long backupChannel = buffer.getLong();
            long ptzControlChannel = buffer.getLong();
            long remoteViewChannel = buffer.getLong();
            byte localVideoInputNo = buffer.get();
            byte audioInputNo = buffer.get();
            byte sensorInputNo = buffer.get();
            byte relayOutputNo = buffer.get();
            int displayResolutionMask = buffer.getInt();
            byte videoOutputNo = buffer.get();
            byte netVideoOutputNo = buffer.get();
            byte netVideoInputNo = buffer.get();
            byte ivsNo = buffer.get();
            byte presetNumOneChannel = buffer.get();
            byte cruiseNumOneChannel = buffer.get();
            byte presetNumOneCruise = buffer.get();
            byte trackNumOneChannel = buffer.get();
            byte userNo = buffer.get();
            byte netClientNo = buffer.get();
            byte netFirstStreamNo = buffer.get();
            byte deviceType = buffer.get();
            byte doblueStream = buffer.get();
            byte audioStream = buffer.get();
            byte talkAudio = buffer.get();
            byte passwordCheck = buffer.get();
            byte brightness = buffer.get();
            byte contrast = buffer.get();
            byte saturation = buffer.get();
            byte hue = buffer.get();
            short videoInputNo = buffer.getShort();
            short deviceID = buffer.getShort();
            int videoFormat = buffer.getInt();
            int[] functions = new int[8];

            for (int i = 0; i < functions.length; i++) {
                functions[i] = buffer.getInt();
            }

            byte[] deviceIP = new byte[4];
            buffer.get(deviceIP);
            byte[] deviceMac = new byte[8];
            buffer.get(deviceMac);
            int buildDate = buffer.getInt();
            int buildTime = buffer.getInt();
            String deviceName = NetworkUtil.readFixedLengthString(buffer, 36);
            String firmwareVersion = NetworkUtil.readFixedLengthString(buffer, 36);
            String kernelVersion = NetworkUtil.readFixedLengthString(buffer, 64);
            String hardwareVersion = NetworkUtil.readFixedLengthString(buffer, 36);
            String mcuVersion = NetworkUtil.readFixedLengthString(buffer, 36);

            return new LoginSuccess(
                permissionFlags,
                liveChannel,
                recordChannel,
                playbackChannel,
                backupChannel,
                ptzControlChannel,
                remoteViewChannel,
                localVideoInputNo,
                audioInputNo,
                sensorInputNo,
                relayOutputNo,
                displayResolutionMask,
                videoOutputNo,
                netVideoOutputNo,
                netVideoInputNo,
                ivsNo,
                presetNumOneChannel,
                cruiseNumOneChannel,
                presetNumOneCruise,
                trackNumOneChannel,
                userNo,
                netClientNo,
                netFirstStreamNo,
                deviceType,
                doblueStream,
                audioStream,
                talkAudio,
                passwordCheck,
                brightness,
                contrast,
                saturation,
                hue,
                videoInputNo,
                deviceID,
                videoFormat,
                functions,
                deviceIP,
                deviceMac,
                buildDate,
                buildTime,
                deviceName,
                firmwareVersion,
                kernelVersion,
                hardwareVersion,
                mcuVersion
            );
        }
    }
}

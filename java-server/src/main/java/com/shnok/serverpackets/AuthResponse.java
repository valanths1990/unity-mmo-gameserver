package com.shnok.serverpackets;

public class AuthResponse extends ServerPacket {

    public enum AuthResponseType {
        ALLOW,
        ALREADY_CONNECTED,
        INVALID_USERNAME
    }

    public AuthResponse(AuthResponseType reason) {
        super((byte)0x01);
        writeB((byte)reason.ordinal());
        buildPacket();
    }
}
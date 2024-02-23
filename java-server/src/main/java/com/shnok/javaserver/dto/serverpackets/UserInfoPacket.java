package com.shnok.javaserver.dto.serverpackets;

import com.shnok.javaserver.dto.ServerPacket;
import com.shnok.javaserver.enums.ServerPacketType;
import com.shnok.javaserver.model.object.entity.PlayerInstance;

public class UserInfoPacket extends ServerPacket {
    public UserInfoPacket(PlayerInstance player) {
        super(ServerPacketType.UserInfo.getValue());

        writeI(player.getId());
        writeS(player.getName());
        writeF(player.getPosX());
        writeF(player.getPosY());
        writeF(player.getPosZ());
        writeI(player.getLeftHandId());
        writeI(player.getRightHandId());
        writeI(player.getStatus().getMoveSpeed());
        writeI(player.getTemplate().getBasePAtkSpd());
        writeI(player.getTemplate().getBaseMAtkSpd());
        writeI(player.getStatus().getLevel());
        writeI(player.getStatus().getHp());
        writeI(player.getStatus().getMaxHp());
        writeI(player.getStatus().getMp());
        writeI(player.getStatus().getMaxMp());
        writeI(player.getStatus().getCp());
        writeI(player.getStatus().getMaxCp());
        buildPacket();
    }
}

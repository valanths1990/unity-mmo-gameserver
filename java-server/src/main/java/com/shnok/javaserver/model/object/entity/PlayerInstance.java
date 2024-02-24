package com.shnok.javaserver.model.object.entity;

import com.shnok.javaserver.dto.ServerPacket;
import com.shnok.javaserver.dto.serverpackets.ApplyDamagePacket;
import com.shnok.javaserver.dto.serverpackets.UserInfoPacket;
import com.shnok.javaserver.model.item.Inventory;
import com.shnok.javaserver.model.item.PlayerInventory;
import com.shnok.javaserver.model.knownlist.PlayerKnownList;
import com.shnok.javaserver.model.status.PlayerStatus;
import com.shnok.javaserver.model.status.Status;
import com.shnok.javaserver.model.template.PlayerTemplate;
import com.shnok.javaserver.thread.GameClientThread;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
public class PlayerInstance extends Entity {
    public final String name;
    private GameClientThread gameClient;
    private PlayerInventory inventory;

    public PlayerInstance(int id, String name) {
        super(id);
        this.name = name;
    }

    public PlayerInstance(String name, PlayerTemplate playerTemplate) {
        this.name = name;
        this.template = playerTemplate;
        this.status = new PlayerStatus(playerTemplate);
        // TODO load weapons from DB
        this.leftHandId = 0;
        this.rightHandId = 2369;
    }

    @Override
    public PlayerKnownList getKnownList() {
        if ((super.getKnownList() == null) || !(super.getKnownList() instanceof PlayerKnownList)) {
            setKnownList(new PlayerKnownList(this));
        }
        return (PlayerKnownList) super.getKnownList();
    }

    // Send packet to player
    public boolean sendPacket(ServerPacket packet) {
        if(gameClient.isClientReady() && gameClient.isAuthenticated()) {
            if(gameClient.sendPacket(packet)) {
                if(packet instanceof UserInfoPacket) {
                    log.debug("[{}] Sending user packet", getGameClient().getCurrentPlayer().getId());
                }
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    @Override
    public void inflictDamage(Entity attacker, int value) {
        super.inflictDamage(attacker, value);

        status.setHp(Math.max(status.getHp() - value, 0));
        if (status.getHp() == 0) {
            onDeath();
        }
    }

    @Override
    public boolean onHitTimer(Entity target, int damage, boolean criticalHit) {
        if(super.onHitTimer(target, damage, criticalHit)) {
            ApplyDamagePacket applyDamagePacket = new ApplyDamagePacket(
                    getId(), target.getId(), damage, target.getStatus().getHp(), criticalHit);
            broadcastPacket(applyDamagePacket);
            sendPacket(applyDamagePacket);
            return true;
        }

        return false;
    }

    @Override
    public final PlayerStatus getStatus() {
        return (PlayerStatus) super.getStatus();
    }

    @Override
    public void setStatus(Status status) {
        this.status = (PlayerStatus) status;
    }

    @Override
    public boolean canMove() {
        return canMove;
    }

    @Override
    public void onDeath() {

    }
}

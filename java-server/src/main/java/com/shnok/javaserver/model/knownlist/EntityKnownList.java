package com.shnok.javaserver.model.knownlist;

import com.shnok.javaserver.model.GameObject;
import com.shnok.javaserver.model.entity.Entity;
import com.shnok.javaserver.model.entity.NpcInstance;
import com.shnok.javaserver.model.entity.PlayerInstance;
import com.shnok.javaserver.util.VectorUtils;
import javolution.util.FastList;
import javolution.util.FastMap;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;
import java.util.Map;

@Log4j2
public class EntityKnownList extends ObjectKnownList  {
    private Map<Integer, PlayerInstance> knownPlayers;
    private Map<Integer, Integer> knownRelations;

    public EntityKnownList(Entity activeChar) {
        super(activeChar);
    }

    @Override
    public boolean addKnownObject(GameObject object) {
        return addKnownObject(object, null);
    }

    @Override
    public boolean addKnownObject(GameObject object, Entity dropper) {
        if (!super.addKnownObject(object, dropper)) {
            return false;
        }
        if (object instanceof PlayerInstance) {
            getKnownPlayers().put(object.getId(), (PlayerInstance) object);
            log.debug("[{}] KnownPlayers: {}", getActiveObject().getId(), getKnownPlayers().size());
        }

        return true;
    }

    /**
     * Return True if the PlayerInstance is in knownPlayer of the Entity.<BR>
     * <BR>
     * @param player The PlayerInstance to search in knownPlayer
     * @return
     */
    public final boolean knowsThePlayer(PlayerInstance player) {
        return (getActiveChar() == player) || getKnownPlayers().containsKey(player.getId());
    }

    /** Remove all GameObject from knownObjects and knownPlayer of the Entity then cancel Attak or Cast and notify AI. */
    @Override
    public final void removeAllKnownObjects()
    {
        super.removeAllKnownObjects();
        getKnownPlayers().clear();

        /*// Set target of the Entity to null
        // Cancel Attack or Cast
        getActiveChar().setTarget(null);

        // Cancel AI Task
        if (getActiveChar().hasAI()) {
            getActiveChar().setAI(null);
        }*/
    }

    @Override
    public boolean removeKnownObject(GameObject object) {
        if (!super.removeKnownObject(object)) {
            return false;
        }
        if (object instanceof PlayerInstance) {
            getKnownPlayers().remove(object.getId());
            log.debug("[{}] KnownPlayers: {}", getActiveObject().getId(), getKnownPlayers().size());
        }
        // If object is targeted by the Entity, cancel Attack or Cast
        /*if (object == getActiveChar().getTarget()) {
            getActiveChar().setTarget(null);
        }*/
        return true;
    }

    public Entity getActiveChar() {
        return (Entity) super.getActiveObject();
    }

    @Override
    public int getDistanceToForgetObject(GameObject object) {
        return 0;
    }

    @Override
    public int getDistanceToWatchObject(GameObject object) {
        return 0;
    }

    public Collection<Entity> getKnownCharacters() {
        FastList<Entity> result = new FastList<>();

        for (GameObject obj : getKnownObjects().values()) {
            if ((obj != null) && (obj instanceof Entity)) {
                result.add((Entity) obj);
            }
        }

        return result;
    }

    public Collection<Entity> getKnownCharactersInRadius(long radius) {
        FastList<Entity> result = new FastList<>();

        for (GameObject obj : getKnownObjects().values()) {
            if (obj instanceof PlayerInstance) {
                if (VectorUtils.checkIfInRange((int) radius, getActiveChar(), obj)) {
                    result.add((PlayerInstance) obj);
                }
            } else if (obj instanceof NpcInstance) {
                if (VectorUtils.checkIfInRange((int) radius, getActiveChar(), obj)) {
                    result.add((NpcInstance) obj);
                }
            }
        }

        return result;
    }

    public final Map<Integer, PlayerInstance> getKnownPlayers() {
        if (knownPlayers == null) {
            knownPlayers = new FastMap<Integer, PlayerInstance>().shared();
        }
        return knownPlayers;
    }

    public final Collection<PlayerInstance> getKnownPlayersInRadius(long radius) {
        FastList<PlayerInstance> result = new FastList<>();
        for (PlayerInstance player : getKnownPlayers().values()) {
            if (VectorUtils.checkIfInRange((int) radius, getActiveChar(), player)) {
                result.add(player);
            }
        }

        return result;
    }
}
package com.shnok.javaserver.gameserver.network.serverpackets.item;

import com.shnok.javaserver.gameserver.model.actor.Playable;

public class InventoryUpdate extends AbstractInventoryUpdate
{
	public InventoryUpdate(Playable playable)
	{
		super(playable);
	}
}
package com.shnok.javaserver.gameserver.network.serverpackets.item;

import java.util.Set;

import com.shnok.javaserver.gameserver.model.actor.Player;
import com.shnok.javaserver.gameserver.model.item.instance.ItemInstance;
import com.shnok.javaserver.gameserver.model.item.kind.Item;
import com.shnok.javaserver.gameserver.network.serverpackets.L2GameServerPacket;

public class ItemList extends L2GameServerPacket
{
	private final Set<ItemInstance> _items;
	private final boolean _showWindow;
	
	public ItemList(Player player, boolean showWindow)
	{
		// Enforce the clearance of update list upon a full ItemList send, which means no weight update will be done.
		player.getInventory().clearUpdateList();
		
		// Manually calculate and set the weight, since we got no items manipulation to automatically call it.
		player.getInventory().updateWeight();
		
		_items = player.getInventory().getItems();
		_showWindow = showWindow;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x1b);
		writeH(_showWindow ? 0x01 : 0x00);
		writeH(_items.size());
		
		for (ItemInstance temp : _items)
		{
			Item item = temp.getItem();
			
			writeH(item.getType1());
			writeD(temp.getObjectId());
			writeD(temp.getItemId());
			writeD(temp.getCount());
			writeH(item.getType2());
			writeH(temp.getCustomType1());
			writeH(temp.isEquipped() ? 0x01 : 0x00);
			writeD(item.getBodyPart());
			writeH(temp.getEnchantLevel());
			writeH(temp.getCustomType2());
			writeD((temp.isAugmented()) ? temp.getAugmentation().getId() : 0x00);
			writeD(temp.getDisplayedManaLeft());
			writeD(temp.getLocationSlot()); //l2-unity
		}
	}
}
package com.shnok.javaserver.gameserver.network.serverpackets.unused;

import com.shnok.javaserver.gameserver.network.serverpackets.L2GameServerPacket;

public class PledgeShowMemberListDelete extends L2GameServerPacket
{
	private final String _player;
	
	public PledgeShowMemberListDelete(String playerName)
	{
		_player = playerName;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x56);
		writeS(_player);
	}
}
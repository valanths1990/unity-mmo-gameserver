package com.shnok.javaserver.gameserver.network.serverpackets.unused;

import com.shnok.javaserver.gameserver.network.serverpackets.L2GameServerPacket;

public class ExCloseMPCC extends L2GameServerPacket
{
	public static final ExCloseMPCC STATIC_PACKET = new ExCloseMPCC();
	
	private ExCloseMPCC()
	{
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x26);
	}
}
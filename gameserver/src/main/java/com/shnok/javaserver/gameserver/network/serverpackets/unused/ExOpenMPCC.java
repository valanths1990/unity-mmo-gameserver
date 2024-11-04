package com.shnok.javaserver.gameserver.network.serverpackets.unused;

import com.shnok.javaserver.gameserver.network.serverpackets.L2GameServerPacket;

public class ExOpenMPCC extends L2GameServerPacket
{
	public static final ExOpenMPCC STATIC_PACKET = new ExOpenMPCC();
	
	private ExOpenMPCC()
	{
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x25);
	}
}
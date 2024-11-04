package com.shnok.javaserver.gameserver.network.serverpackets.auth;

import com.shnok.javaserver.gameserver.network.serverpackets.L2GameServerPacket;

public class ServerClose extends L2GameServerPacket
{
	public static final ServerClose STATIC_PACKET = new ServerClose();
	
	private ServerClose()
	{
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0x26);
	}
}
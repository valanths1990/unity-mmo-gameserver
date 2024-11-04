package com.shnok.javaserver.gameserver.model.records;

import com.shnok.javaserver.commons.data.StatSet;

public record ClanHallDeco(String name, int type, int level, int depth, int days, int price)
{
	public ClanHallDeco(StatSet set)
	{
		this(set.getString("name", ""), set.getInteger("type", 0), set.getInteger("level", 0), set.getInteger("depth"), set.getInteger("days"), set.getInteger("price"));
	}
}
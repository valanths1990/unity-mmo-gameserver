package com.shnok.javaserver.gameserver.network.serverpackets.unused;

import java.util.ArrayList;
import java.util.List;

import com.shnok.javaserver.gameserver.data.xml.PlayerData;
import com.shnok.javaserver.gameserver.enums.actors.ClassId;
import com.shnok.javaserver.gameserver.model.actor.template.PlayerTemplate;
import com.shnok.javaserver.gameserver.network.serverpackets.L2GameServerPacket;

public class NewCharacterSuccess extends L2GameServerPacket
{
	private final List<PlayerTemplate> _templates = new ArrayList<>();
	
	public static final NewCharacterSuccess STATIC_PACKET = new NewCharacterSuccess();
	
	private NewCharacterSuccess()
	{
		_templates.add(PlayerData.getInstance().getTemplate(0));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.HUMAN_FIGHTER));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.HUMAN_MYSTIC));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.ELVEN_FIGHTER));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.ELVEN_MYSTIC));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.DARK_FIGHTER));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.DARK_MYSTIC));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.ORC_FIGHTER));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.ORC_MYSTIC));
		_templates.add(PlayerData.getInstance().getTemplate(ClassId.DWARVEN_FIGHTER));
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x17);
		writeD(_templates.size());
		
		for (PlayerTemplate template : _templates)
		{
			writeD(template.getRace().ordinal());
			writeD(template.getClassId().getId());
			writeD(0x46);
			writeD(template.getBaseSTR());
			writeD(0x0a);
			writeD(0x46);
			writeD(template.getBaseDEX());
			writeD(0x0a);
			writeD(0x46);
			writeD(template.getBaseCON());
			writeD(0x0a);
			writeD(0x46);
			writeD(template.getBaseINT());
			writeD(0x0a);
			writeD(0x46);
			writeD(template.getBaseWIT());
			writeD(0x0a);
			writeD(0x46);
			writeD(template.getBaseMEN());
			writeD(0x0a);
		}
	}
}
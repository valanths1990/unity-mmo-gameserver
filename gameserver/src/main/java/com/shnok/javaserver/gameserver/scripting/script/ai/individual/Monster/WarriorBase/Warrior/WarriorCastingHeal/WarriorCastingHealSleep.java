package com.shnok.javaserver.gameserver.scripting.script.ai.individual.Monster.WarriorBase.Warrior.WarriorCastingHeal;

import com.shnok.javaserver.commons.random.Rnd;

import com.shnok.javaserver.gameserver.enums.actors.NpcSkillType;
import com.shnok.javaserver.gameserver.model.actor.Creature;
import com.shnok.javaserver.gameserver.model.actor.Npc;
import com.shnok.javaserver.gameserver.model.actor.Playable;
import com.shnok.javaserver.gameserver.skills.L2Skill;

public class WarriorCastingHealSleep extends WarriorCastingHeal
{
	public WarriorCastingHealSleep()
	{
		super("ai/individual/Monster/WarriorBase/Warrior/WarriorCastingHeal");
	}
	
	public WarriorCastingHealSleep(String descr)
	{
		super(descr);
	}
	
	protected final int[] _npcIds =
	{
		21664,
		21687,
		21710,
		21733,
		21756,
		21779
	};
	
	@Override
	public void onAttacked(Npc npc, Creature attacker, int damage, L2Skill skill)
	{
		if (attacker instanceof Playable)
		{
			final Creature mostHated = npc.getAI().getAggroList().getMostHatedCreature();
			if (mostHated != attacker && Rnd.get(100) < 33)
				npc.getAI().addCastDesire(attacker, getNpcSkillByType(npc, NpcSkillType.MAGIC_SLEEP), 1000000);
		}
		super.onAttacked(npc, attacker, damage, skill);
	}
	
	@Override
	public void onClanAttacked(Npc caller, Npc called, Creature attacker, int damage, L2Skill skill)
	{
		final Creature mostHated = called.getAI().getAggroList().getMostHatedCreature();
		if (mostHated != null && mostHated == attacker && Rnd.get(100) < 33)
			called.getAI().addCastDesire(attacker, getNpcSkillByType(called, NpcSkillType.MAGIC_SLEEP), 1000000);
		
		super.onClanAttacked(caller, called, attacker, damage, skill);
	}
}
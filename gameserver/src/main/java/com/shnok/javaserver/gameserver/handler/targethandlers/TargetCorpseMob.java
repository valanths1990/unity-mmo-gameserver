package com.shnok.javaserver.gameserver.handler.targethandlers;

import com.shnok.javaserver.gameserver.enums.skills.SkillTargetType;
import com.shnok.javaserver.gameserver.enums.skills.SkillType;
import com.shnok.javaserver.gameserver.handler.ITargetHandler;
import com.shnok.javaserver.gameserver.model.actor.Creature;
import com.shnok.javaserver.gameserver.model.actor.Playable;
import com.shnok.javaserver.gameserver.model.actor.instance.Monster;
import com.shnok.javaserver.gameserver.model.actor.instance.Pet;
import com.shnok.javaserver.gameserver.model.actor.template.NpcTemplate;
import com.shnok.javaserver.gameserver.network.SystemMessageId;
import com.shnok.javaserver.gameserver.skills.L2Skill;
import com.shnok.javaserver.gameserver.taskmanager.DecayTaskManager;

public class TargetCorpseMob implements ITargetHandler
{
	@Override
	public SkillTargetType getTargetType()
	{
		return SkillTargetType.CORPSE_MOB;
	}
	
	@Override
	public Creature[] getTargetList(Creature caster, Creature target, L2Skill skill)
	{
		return new Creature[]
		{
			target
		};
	}
	
	@Override
	public Creature getFinalTarget(Creature caster, Creature target, L2Skill skill)
	{
		return target;
	}
	
	@Override
	public boolean meetCastConditions(Playable caster, Creature target, L2Skill skill, boolean isCtrlPressed)
	{
		// Verify if a corpse exists. Can't do anything on Pets.
		final Long time = DecayTaskManager.getInstance().get(target);
		if (time == null || target instanceof Pet)
		{
			caster.sendPacket(SystemMessageId.INVALID_TARGET);
			return false;
		}
		
		// Harvest can be done anytime.
		if (skill.getSkillType() == SkillType.HARVEST)
		{
			if (!(target instanceof Monster))
			{
				caster.sendPacket(SystemMessageId.THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN);
				return false;
			}
			
			return true;
		}
		
		// Cast NpcTemplate over Creature template since only pets, servitors and NPCs are stored into DecayTaskManager, and all inherits from NpcTemplate.
		final NpcTemplate template = (NpcTemplate) target.getTemplate();
		
		// Check last corpse action time. That behavior exists whenever the target isn't seeded or spoiled.
		final boolean isSeededOrSpoiled = target instanceof Monster targetMonster && (targetMonster.getSeedState().isSeeded() || targetMonster.getSpoilState().isSpoiled());
		if (!isSeededOrSpoiled && System.currentTimeMillis() >= time - (template.getCorpseTime() * 1000 / 2))
		{
			caster.sendPacket(SystemMessageId.CORPSE_TOO_OLD_SKILL_NOT_USED);
			return false;
		}
		
		// Sweep always end as true if the target is a Monster ; otherwise, it's always false.
		if (skill.getSkillType() == SkillType.SWEEP && !(target instanceof Monster))
		{
			caster.sendPacket(SystemMessageId.SWEEPER_FAILED_TARGET_NOT_SPOILED);
			return false;
		}
		
		return true;
	}
}
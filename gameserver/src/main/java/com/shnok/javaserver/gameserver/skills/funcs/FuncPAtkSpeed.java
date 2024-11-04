package com.shnok.javaserver.gameserver.skills.funcs;

import com.shnok.javaserver.gameserver.enums.skills.Stats;
import com.shnok.javaserver.gameserver.model.actor.Creature;
import com.shnok.javaserver.gameserver.skills.Formulas;
import com.shnok.javaserver.gameserver.skills.L2Skill;
import com.shnok.javaserver.gameserver.skills.basefuncs.Func;

/**
 * @see Func
 */
public class FuncPAtkSpeed extends Func
{
	private static final FuncPAtkSpeed INSTANCE = new FuncPAtkSpeed();
	
	private FuncPAtkSpeed()
	{
		super(null, Stats.POWER_ATTACK_SPEED, 10, 0, null);
	}
	
	@Override
	public double calc(Creature effector, Creature effected, L2Skill skill, double base, double value)
	{
		return value * Formulas.DEX_BONUS[effector.getStatus().getDEX()];
	}
	
	public static Func getInstance()
	{
		return INSTANCE;
	}
}
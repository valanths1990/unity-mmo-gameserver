package com.shnok.javaserver.gameserver.handler.itemhandlers;

import com.shnok.javaserver.Config;
import com.shnok.javaserver.gameserver.data.xml.RecipeData;
import com.shnok.javaserver.gameserver.enums.actors.OperateType;
import com.shnok.javaserver.gameserver.handler.IItemHandler;
import com.shnok.javaserver.gameserver.model.actor.Playable;
import com.shnok.javaserver.gameserver.model.actor.Player;
import com.shnok.javaserver.gameserver.model.item.instance.ItemInstance;
import com.shnok.javaserver.gameserver.model.records.Recipe;
import com.shnok.javaserver.gameserver.network.SystemMessageId;
import com.shnok.javaserver.gameserver.network.serverpackets.unused.RecipeBookItemList;
import com.shnok.javaserver.gameserver.network.serverpackets.SystemMessage;
import com.shnok.javaserver.gameserver.skills.L2Skill;

public class Recipes implements IItemHandler
{
	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse)
	{
		if (!(playable instanceof Player player))
			return;
		
		if (!Config.IS_CRAFTING_ENABLED)
		{
			player.sendMessage("Crafting is disabled, you cannot register this recipe.");
			return;
		}
		
		if (player.isCrafting())
		{
			player.sendPacket(SystemMessageId.CANT_ALTER_RECIPEBOOK_WHILE_CRAFTING);
			return;
		}
		
		final Recipe recipe = RecipeData.getInstance().getRecipeByItemId(item.getItemId());
		if (recipe == null)
			return;
		
		if (player.getRecipeBook().hasRecipe(recipe.id()))
		{
			player.sendPacket(SystemMessageId.RECIPE_ALREADY_REGISTERED);
			return;
		}
		
		final boolean isDwarven = recipe.isDwarven();
		if (isDwarven)
		{
			if (!player.hasDwarvenCraft())
				player.sendPacket(SystemMessageId.CANT_REGISTER_NO_ABILITY_TO_CRAFT);
			else if (player.getOperateType() == OperateType.MANUFACTURE)
				player.sendPacket(SystemMessageId.CANT_ALTER_RECIPEBOOK_WHILE_CRAFTING);
			else if (recipe.level() > player.getSkillLevel(L2Skill.SKILL_CREATE_DWARVEN))
				player.sendPacket(SystemMessageId.CREATE_LVL_TOO_LOW_TO_REGISTER);
			else if (player.getRecipeBook().get(isDwarven).size() >= player.getStatus().getDwarfRecipeLimit())
				player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.UP_TO_S1_RECIPES_CAN_REGISTER).addNumber(player.getStatus().getDwarfRecipeLimit()));
			else if (player.destroyItem(item.getObjectId(), 1, false))
			{
				player.getRecipeBook().putRecipe(recipe, isDwarven, true);
				player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_ADDED).addItemName(item));
				player.sendPacket(new RecipeBookItemList(player, isDwarven));
			}
		}
		else
		{
			if (!player.hasCommonCraft())
				player.sendPacket(SystemMessageId.CANT_REGISTER_NO_ABILITY_TO_CRAFT);
			else if (player.getOperateType() == OperateType.MANUFACTURE)
				player.sendPacket(SystemMessageId.CANT_ALTER_RECIPEBOOK_WHILE_CRAFTING);
			else if (recipe.level() > player.getSkillLevel(L2Skill.SKILL_CREATE_COMMON))
				player.sendPacket(SystemMessageId.CREATE_LVL_TOO_LOW_TO_REGISTER);
			else if (player.getRecipeBook().get(isDwarven).size() >= player.getStatus().getCommonRecipeLimit())
				player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.UP_TO_S1_RECIPES_CAN_REGISTER).addNumber(player.getStatus().getCommonRecipeLimit()));
			else if (player.destroyItem(item.getObjectId(), 1, false))
			{
				player.getRecipeBook().putRecipe(recipe, isDwarven, true);
				player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_ADDED).addItemName(item));
				player.sendPacket(new RecipeBookItemList(player, isDwarven));
			}
		}
	}
}
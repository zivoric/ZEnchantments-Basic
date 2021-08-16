package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class InconsistencyCurse extends CustomEnch {

	public InconsistencyCurse(EnchantmentHolder holder) {
		super(holder, "inconsistency_curse");
	}

	@Override
	public String getDisplayName() {
		return "Curse of Inconsistency";
	}
	
	@Override
	public boolean isCursed() {
		return true;
	}
	
	@Override
	public boolean isTreasure() {
		return true;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public int getPriority() {
		return 1;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}
	
	
	@Override
	public void onBreakBlock(BlockBreakEvent event, List<Integer> levels, List<ItemStack> items) {
		if (Math.random() < 0.15) {
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 0.4F, 0.5F);
			event.setCancelled(true);
		}
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.TOOL;
	}

}

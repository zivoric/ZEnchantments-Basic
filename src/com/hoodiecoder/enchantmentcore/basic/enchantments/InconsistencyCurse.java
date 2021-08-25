package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import com.hoodiecoder.enchantmentcore.enchant.BlockHandler;
import org.bukkit.Sound;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class InconsistencyCurse extends CustomEnch implements BlockHandler {

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
	public Rarity getEnchantmentRarity() {
		return Rarity.VERY_RARE;
	}

	@Override
	public void onPlaceBlock(Player player, List<Integer> levels, List<ItemStack> items, BlockPlaceEvent blockPlaceEvent) {

	}

	@Override
	public void onBreakBlock(Player player, List<Integer> levels, List<ItemStack> items, BlockBreakEvent event) {
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

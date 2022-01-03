package io.zivoric.enchantmentcore.basic.enchantments;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.enchant.BlockHandler;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;
import org.bukkit.Sound;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class InconsistencyCurse extends CustomEnch implements BlockHandler {

	public InconsistencyCurse(Plugin plugin) {
		super(plugin, "inconsistency_curse");
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

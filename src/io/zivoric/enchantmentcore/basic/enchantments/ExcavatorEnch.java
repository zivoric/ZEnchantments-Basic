package io.zivoric.enchantmentcore.basic.enchantments;

import java.util.Arrays;
import java.util.List;

import io.zivoric.enchantmentcore.enchant.BlockHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.EnchantmentHolder;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;

public class ExcavatorEnch extends CustomEnch implements BlockHandler {
	public ExcavatorEnch(EnchantmentHolder holder) {
		super(holder, "excavator");
	}

	@Override
	public String getDisplayName() {
		return "Excavator";
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.VERY_RARE;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.TOOL;
	}

	@Override
	public void onPlaceBlock(Player player, List<Integer> levels, List<ItemStack> items, BlockPlaceEvent blockPlaceEvent) {

	}

	@Override
	public void onBreakBlock(Player player, List<Integer> levels, List<ItemStack> items, BlockBreakEvent event) {
		if (event.isCancelled()) return;
		List<Material> disallowed = Arrays.asList(Material.BEDROCK, Material.BARRIER, Material.COMMAND_BLOCK, Material.BEDROCK, Material.END_PORTAL_FRAME, Material.END_PORTAL, Material.NETHER_PORTAL, Material.STRUCTURE_BLOCK);
		Block block = event.getBlock();
		org.bukkit.Location locDiff = block.getLocation().subtract(player.getLocation().add(0, 1, 0));
		int xMod = (int) Math.signum(locDiff.getX());
		if (xMod == 0) xMod = -1;
		int yMod = (int) Math.signum(locDiff.getY());
		if (yMod == 0) yMod = -1;
		int zMod = (int) Math.signum(locDiff.getZ());
		if (zMod == 0) zMod = -1;
		for (int x = 0; xMod > 0 ? x <= xMod : x >= xMod; x+=xMod) {
			for (int y = 0; yMod > 0 ? y <= yMod : y >= yMod; y+=yMod) {
				for (int z = 0; zMod > 0 ? z <= zMod : z >= zMod; z+=zMod) {
					Block current = player.getWorld().getBlockAt(block.getLocation().add(x, y, z));
					Material currentMaterial = current.getType();
					if (player.getGameMode().equals(org.bukkit.GameMode.CREATIVE)) {
						current.setType(Material.AIR);
					} else {
						if (!disallowed.contains(current.getType())) {
							current.breakNaturally(items.get(0));
						} if (currentMaterial != current.getType()) {
							ItemMeta im = items.get(0).getItemMeta();
							if (im==null) return;
							if (!im.isUnbreakable()) {
								Damageable is = (Damageable) im;
								if (im.getEnchantLevel(Enchantment.DURABILITY) != -1) {
									double random = Math.random()*(1+im.getEnchantLevel(Enchantment.DURABILITY));
									if ((int) random == 0) {
										is.setDamage(is.getDamage()+1);
									}
								} else {
									is.setDamage(is.getDamage()+1);
								}
								if (is.getDamage() < items.get(0).getType().getMaxDurability()) {
									items.get(0).setItemMeta((ItemMeta) is);
								} else {
									if (items.get(0).equals(player.getInventory().getItemInMainHand())) {
										player.getInventory().setItemInMainHand(null);
									} else if (items == player.getInventory().getItemInOffHand()) {
										player.getInventory().setItemInOffHand(null);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

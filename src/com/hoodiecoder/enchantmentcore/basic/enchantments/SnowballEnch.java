package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class SnowballEnch extends CustomEnch {
	public SnowballEnch(EnchantmentHolder holder) {
		super(holder, "snowball_bow");
	}

	@Override
	public String getDisplayName() {
		return "Snowball";
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.UNFINDABLE;
	}
	@Override
	public void onShootBow(EntityShootBowEvent event, List<Integer> levels, List<ItemStack> items) {
		Player player = (Player) event.getEntity();
		Projectile proj = (Projectile) event.getProjectile();
		event.setCancelled(true);
		player.launchProjectile(Snowball.class).setVelocity(proj.getVelocity());
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}
}

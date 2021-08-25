package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import com.hoodiecoder.enchantmentcore.enchant.ShootBowHandler;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class SnowballEnch extends CustomEnch implements ShootBowHandler {
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
	public Rarity getEnchantmentRarity() {
		return Rarity.UNFINDABLE;
	}
	@Override
	public void onShootBow(LivingEntity entity, List<Integer> levels, List<ItemStack> items, EntityShootBowEvent event) {
		Projectile proj = (Projectile) event.getProjectile();
		event.setCancelled(true);
		entity.launchProjectile(Snowball.class).setVelocity(proj.getVelocity());
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}
}

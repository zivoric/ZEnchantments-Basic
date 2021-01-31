package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.EnchantmentSlotEnum;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.RarityEnum;

public class SnowballEnch extends CustomEnch {
	public SnowballEnch(EnchantmentHolder holder) {
		super(holder);
	}

	@Override
	public String getDisplayName() {
		return "Snowball";
	}

	@Override
	public EnchantmentSlotEnum getEnchantmentSlot() {
		return EnchantmentSlotEnum.BOW;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public String getName() {
		return "snowball";
	}

	@Override
	public RarityEnum getRarity() {
		return RarityEnum.VERY_RARE;
	}
	@Override
	public void onShootBow(EntityShootBowEvent event, List<Integer> levels, List<ItemStack> items) {
		Player player = (Player) event.getEntity();
		Projectile proj = (Projectile) event.getProjectile();
		event.setCancelled(true);
		player.launchProjectile(Snowball.class).setVelocity(proj.getVelocity());
	}
}

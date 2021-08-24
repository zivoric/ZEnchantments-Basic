package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class LifestealEnch extends CustomEnch {
	public LifestealEnch(EnchantmentHolder holder) {
		super(holder, "life_steal");
	}

	@Override
	public String getDisplayName() {
		return "Lifesteal";
	}

	@Override
	public int getMaxLevel() {
		return 10;
	}
	@Override
	public int getModifiedMin(int enchLevel) {
		return 1 + enchLevel * 7;
	}

	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.COMMON;
	}
	@Override
	public void onDealDamage(EntityDamageByEntityEvent event, List<Integer> levels, List<ItemStack> items) {
		Player player = (Player) event.getDamager();
		double finalDamage = event.getFinalDamage();
		double health = player.getHealth();
		double newHealth = health + finalDamage*(0.1*(levels.get(0)));
		if (newHealth > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
			newHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
		}
		player.setHealth(newHealth);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

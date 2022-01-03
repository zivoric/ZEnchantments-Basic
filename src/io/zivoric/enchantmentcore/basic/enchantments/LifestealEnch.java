package io.zivoric.enchantmentcore.basic.enchantments;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.enchant.DamageHandler;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class LifestealEnch extends CustomEnch implements DamageHandler {
	public LifestealEnch(Plugin plugin) {
		super(plugin, "life_steal");
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
	public void onDealDamage(LivingEntity entity, List<Integer> levels, List<ItemStack> items, EntityDamageByEntityEvent event) {
		double finalDamage = event.getFinalDamage();
		double health = entity.getHealth();
		double newHealth = health + finalDamage*(0.1*(levels.get(0)));
		if (newHealth > entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
			newHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
		}
		entity.setHealth(newHealth);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

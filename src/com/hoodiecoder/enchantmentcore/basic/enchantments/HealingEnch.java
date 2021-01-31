package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.EnchantmentSlotEnum;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.RarityEnum;

public class HealingEnch extends CustomEnch {
	public HealingEnch(EnchantmentHolder holder) {
		super(holder);
	}

	@Override
	public String getDisplayName() {
		return "Healing";
	}

	@Override
	public EnchantmentSlotEnum getEnchantmentSlot() {
		return EnchantmentSlotEnum.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public String getName() {
		return "healing";
	}

	@Override
	public RarityEnum getRarity() {
		return RarityEnum.VERY_RARE;
	}
	@Override
	public void onDealDamage(EntityDamageByEntityEvent event, List<Integer> levels, List<ItemStack> items) {
		LivingEntity entity = (LivingEntity) event.getEntity();
		double oldDamage = event.getDamage();
		double health = entity.getHealth();
		double newHealth = health + levels.get(0)*0.25*oldDamage;
		if (newHealth > entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
			newHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
		}
		event.setDamage(0.0);
		entity.setHealth(newHealth);
	}
}

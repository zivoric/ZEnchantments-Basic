package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.EnchantmentSlotEnum;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.RarityEnum;

public class LevitatorEnch extends CustomEnch {
	public LevitatorEnch(EnchantmentHolder holder) {
		super(holder);
	}

	@Override
	public String getDisplayName() {
		return "Levitator";
	}

	@Override
	public EnchantmentSlotEnum getEnchantmentSlot() {
		return EnchantmentSlotEnum.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public String getName() {
		return "levitator";
	}

	@Override
	public RarityEnum getRarity() {
		return RarityEnum.RARE;
	}
	@Override
	public void onDealDamage(EntityDamageByEntityEvent event, List<Integer> levels, List<ItemStack> items) {
		LivingEntity entity = (LivingEntity) event.getEntity();
		PotionEffect levitate = new PotionEffect(PotionEffectType.LEVITATION, 4*20*levels.get(0), 1, true);
		entity.addPotionEffect(levitate);
	}
}

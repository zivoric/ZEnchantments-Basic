package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import com.hoodiecoder.enchantmentcore.enchant.DamageHandler;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class DecayEnch extends CustomEnch implements DamageHandler {
	public DecayEnch(EnchantmentHolder holder) {
		super(holder, "decay");
	}

	@Override
	public String getDisplayName() {
		return "Decay";
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.RARE;
	}
	@Override
	public void onDealDamage(LivingEntity entity, List<Integer> levels, List<ItemStack> items, EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof LivingEntity)) return;
		LivingEntity target = (LivingEntity) event.getEntity();
		PotionEffect wither = new PotionEffect(PotionEffectType.WITHER, 100, levels.get(0)-1, true);
		target.addPotionEffect(wither);
	}

	@Override
	public void onTakeDamage(LivingEntity livingEntity, List<Integer> list, List<ItemStack> list1, EntityDamageEvent entityDamageEvent) {
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

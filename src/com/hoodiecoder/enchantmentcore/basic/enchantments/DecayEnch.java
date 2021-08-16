package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class DecayEnch extends CustomEnch {
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
	public Rarity getRarity() {
		return Rarity.RARE;
	}
	@Override
	public void onDealDamage(EntityDamageByEntityEvent event, List<Integer> levels, List<ItemStack> items) {
		LivingEntity entity = (LivingEntity) event.getEntity();
		PotionEffect wither = new PotionEffect(PotionEffectType.WITHER, 100, levels.get(0)-1, true);
		entity.addPotionEffect(wither);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

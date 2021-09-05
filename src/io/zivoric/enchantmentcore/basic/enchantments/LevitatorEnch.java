package io.zivoric.enchantmentcore.basic.enchantments;

import java.util.List;

import io.zivoric.enchantmentcore.enchant.DamageHandler;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.EnchantmentHolder;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;

public class LevitatorEnch extends CustomEnch implements DamageHandler {
	public LevitatorEnch(EnchantmentHolder holder) {
		super(holder, "levitator");
	}

	@Override
	public String getDisplayName() {
		return "Levitator";
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.UNFINDABLE;
	}
	@Override
	public void onTakeDamage(LivingEntity livingEntity, List<Integer> list, List<ItemStack> list1, EntityDamageEvent entityDamageEvent) {
	}
	@Override
	public void onDealDamage(LivingEntity entity, List<Integer> levels, List<ItemStack> items, EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof LivingEntity)) return;
		LivingEntity target = (LivingEntity) event.getEntity();
		PotionEffect levitate = new PotionEffect(PotionEffectType.LEVITATION, 4*20*levels.get(0), 1, true);
		target.addPotionEffect(levitate);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

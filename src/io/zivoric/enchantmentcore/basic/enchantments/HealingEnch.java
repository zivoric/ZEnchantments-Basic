package io.zivoric.enchantmentcore.basic.enchantments;

import java.util.List;

import io.zivoric.enchantmentcore.enchant.DamageHandler;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.EnchantmentHolder;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;

public class HealingEnch extends CustomEnch implements DamageHandler {
	public HealingEnch(EnchantmentHolder holder) {
		super(holder, "healing");
	}

	@Override
	public String getDisplayName() {
		return "Healing";
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}
	
	@Override
	public boolean isCompatibleWith(Enchantment other) {
		if (other.getKey().getKey().equals("life_steal")) return false;
		return true;
	}
	
	@Override
	public boolean isTreasure() {
		return true;
	}
	
	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.VERY_RARE;
	}
	@Override
	public void onTakeDamage(LivingEntity livingEntity, List<Integer> list, List<ItemStack> list1, EntityDamageEvent entityDamageEvent) {
	}
	@Override
	public void onDealDamage(LivingEntity damager, List<Integer> levels, List<ItemStack> items, EntityDamageByEntityEvent event) {
		LivingEntity entity = (LivingEntity) event.getEntity();
		double oldDamage = event.getDamage();
		double health = entity.getHealth();
		double newHealth = health + levels.get(0)*0.25*oldDamage;
		if (newHealth > entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
			newHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
		}
		event.setCancelled(true);
		if (damager instanceof Player)
			((Player)damager).playSound(damager.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0F, 1.5F);
		if (entity instanceof Player)
			((Player)entity).playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0F, 1.5F);
		if (newHealth > health)
			entity.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation().add(0, 1, 0), 12, 0.35, 0.35, 0.35);
		entity.setHealth(newHealth);
	}
	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

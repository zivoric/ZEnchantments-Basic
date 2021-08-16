package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class HealingEnch extends CustomEnch {
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
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}
	@Override
	public void onDealDamage(EntityDamageByEntityEvent event, List<Integer> levels, List<ItemStack> items) {
		LivingEntity entity = (LivingEntity) event.getEntity();
		Player player = (Player)event.getDamager();
		double oldDamage = event.getDamage();
		double health = entity.getHealth();
		double newHealth = health + levels.get(0)*0.25*oldDamage;
		if (newHealth > entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
			newHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
		}
		event.setCancelled(true);
		
		player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0F, 1.5F);
		if (entity instanceof Player)
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1.0F, 1.5F);
		if (newHealth > health)
			entity.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation().add(0, 1, 0), 12, 0.35, 0.35, 0.35);
		entity.setHealth(newHealth);
	}
	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

package io.zivoric.enchantmentcore.basic.enchantments;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.enchant.DamageHandler;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class LevitatorEnch extends CustomEnch implements DamageHandler {
	public LevitatorEnch(Plugin plugin) {
		super(plugin, "levitator");
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

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

public class EnergizerEnch extends CustomEnch implements DamageHandler {
	public EnergizerEnch(Plugin plugin) {
		super(plugin, "energizer");
	}

	@Override
	public String getDisplayName() {
		return "Energizer";
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.VERY_RARE;
	}
	@Override
	public void onDealDamage(LivingEntity entity, List<Integer> levels, List<ItemStack> items, EntityDamageByEntityEvent event) {
		int[] scaling = {3,3,3,3};
		int scale = 0;
		for (int i = 0; i < levels.size(); i++) {
			scale += scaling[i];
		}
		int speedLevel = entity.hasPotionEffect(PotionEffectType.SPEED) ? entity.getPotionEffect(PotionEffectType.SPEED).getAmplifier(): -1;
		if (speedLevel>2) speedLevel = 2;
		PotionEffect scalingSpeed = new PotionEffect(PotionEffectType.SPEED, 5*(scale), speedLevel+1, true);
		entity.addPotionEffect(scalingSpeed);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
	}
}

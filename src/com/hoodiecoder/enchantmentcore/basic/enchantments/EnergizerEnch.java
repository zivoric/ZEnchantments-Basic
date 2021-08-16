package com.hoodiecoder.enchantmentcore.basic.enchantments;

import java.util.List;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.utils.EnchEnums.Rarity;

public class EnergizerEnch extends CustomEnch {
	public EnergizerEnch(EnchantmentHolder holder) {
		super(holder, "energizer");
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
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}
	@Override
	public void onDealDamage(EntityDamageByEntityEvent event, List<Integer> levels, List<ItemStack> items) {
		Player player = (Player) event.getDamager();
		int[] scaling = {3,3,3,3};
		int scale = 0;
		for (int i = 0; i < levels.size(); i++) {
			scale += scaling[i];
		}
		int speedLevel = player.hasPotionEffect(PotionEffectType.SPEED) ? player.getPotionEffect(PotionEffectType.SPEED).getAmplifier(): -1;
		if (speedLevel>2) speedLevel = 2;
		PotionEffect scalingSpeed = new PotionEffect(PotionEffectType.SPEED, 5*(scale), speedLevel+1, true);
		player.addPotionEffect(scalingSpeed);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
	}
}

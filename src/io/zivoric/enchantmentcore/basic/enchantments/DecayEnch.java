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

public class DecayEnch extends CustomEnch implements DamageHandler {
	public DecayEnch(Plugin plugin) {
		super(plugin, "decay");
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
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
}

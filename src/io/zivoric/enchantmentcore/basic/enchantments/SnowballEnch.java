package io.zivoric.enchantmentcore.basic.enchantments;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.enchant.ShootBowHandler;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class SnowballEnch extends CustomEnch implements ShootBowHandler {
	public SnowballEnch(Plugin plugin) {
		super(plugin, "snowball_bow");
	}

	@Override
	public String getDisplayName() {
		return "Snowball";
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.UNFINDABLE;
	}
	@Override
	public void onShootBow(LivingEntity entity, List<Integer> levels, List<ItemStack> items, EntityShootBowEvent event) {
		Projectile proj = (Projectile) event.getProjectile();
		event.setCancelled(true);
		entity.launchProjectile(Snowball.class).setVelocity(proj.getVelocity());
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}
}

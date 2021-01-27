package com.hoodiecoder.enchantmentcore.basic;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.hoodiecoder.enchantmentcore.CustomEnchListener;
import com.hoodiecoder.enchantmentcore.ListenerType;
import com.hoodiecoder.enchantmentcore.CoreEnchParent;

public class BasicEnchListener extends CustomEnchListener {
	public BasicEnchListener(Plugin implementer, CoreEnchParent[] enchs) {
		super(implementer, enchs);
	}

	@Override
	public void onCustomEvent(Event event, CoreEnchParent ench, List<Integer> levels, List<ItemStack> items, ListenerType ltype) {
		Player player;
		int enchCount = levels.size();
		switch (ltype) {
		case ENTITY_DEAL_DAMAGE:
			EntityDamageByEntityEvent dmgEvent = (EntityDamageByEntityEvent) event;
			if (!(dmgEvent.getEntity() instanceof LivingEntity)) return;
			LivingEntity entity = (LivingEntity) dmgEvent.getEntity();
			player = (Player) dmgEvent.getDamager();
			double damage = dmgEvent.getDamage();
			if (ench.equals(BasicEnchPlugin.getInstance().LEVITATOR)) {
				PotionEffect levitate = new PotionEffect(PotionEffectType.LEVITATION, 4*20*levels.get(0), 1, true);
				entity.addPotionEffect(levitate);
				return;
			} else if (ench.equals(BasicEnchPlugin.getInstance().LIFE_STEAL)) {
				double finalDamage = dmgEvent.getFinalDamage();
				double health = player.getHealth();
				double newHealth = health + finalDamage*(0.1*(levels.get(0)));
				if (newHealth > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
					newHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
				}
				player.setHealth(newHealth);
				return;
			} else if (ench.equals(BasicEnchPlugin.getInstance().DECAY)) {
				PotionEffect wither = new PotionEffect(PotionEffectType.WITHER, 100, levels.get(0)-1, true);
				entity.addPotionEffect(wither);
				return;
			} else if (ench.equals(BasicEnchPlugin.getInstance().HEALING)) {
				double oldDamage = damage;
				double health = entity.getHealth();
				double newHealth = health + levels.get(0)*0.25*oldDamage;
				if (newHealth > entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
					newHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
				}
				dmgEvent.setDamage(0.0);
				entity.setHealth(newHealth);
				return;
			}
			if (ench.equals(BasicEnchPlugin.getInstance().ENERGIZER)) {
				int[] scaling = {3,3,3,3};
				int scale = 0;
				for (int i = 0; i < enchCount; i++) {
					scale += scaling[i];
				}
				int speedLevel = player.hasPotionEffect(PotionEffectType.SPEED) ? player.getPotionEffect(PotionEffectType.SPEED).getAmplifier(): -1;
				if (speedLevel>2) speedLevel = 2;
				PotionEffect scalingSpeed = new PotionEffect(PotionEffectType.SPEED, 5*(scale), speedLevel+1, true);
				player.addPotionEffect(scalingSpeed);
				return;
			}
			break;
		case ENTITY_SHOOT_BOW:
			EntityShootBowEvent bowEvent = (EntityShootBowEvent) event;
			player = (Player) bowEvent.getEntity();
			Projectile proj = (Projectile) bowEvent.getProjectile();
			if (ench.equals(BasicEnchPlugin.getInstance().SNOWBALL)) {
				bowEvent.setCancelled(true);
				player.launchProjectile(Snowball.class).setVelocity(proj.getVelocity());
			}
			return;
		case ENTITY_BECOME_TARGETED:
			EntityTargetEvent targetEvent = (EntityTargetEvent) event;
			player = (Player) targetEvent.getTarget();
			if (ench.equals(BasicEnchPlugin.getInstance().DOUBLE_AGENT)) {
				EntityTargetEvent.TargetReason[] neutralReasons = new EntityTargetEvent.TargetReason[]{TargetReason.TARGET_ATTACKED_ENTITY, TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, TargetReason.TARGET_ATTACKED_OWNER, TargetReason.REINFORCEMENT_TARGET};
				EntityTargetEvent.TargetReason[] invalidReasons = new EntityTargetEvent.TargetReason[]{TargetReason.TEMPT, TargetReason.CUSTOM, TargetReason.UNKNOWN};
				boolean canBeCanceled = true;
				int scale = 0;
				for (int i = 0; i < enchCount; i++) {
					scale += levels.get(i);
				}
				if (scale < 4) {
					canBeCanceled = false;
				} else if (scale >= 4 && scale < 8) {
					for (TargetReason t : neutralReasons) {
						if (t == targetEvent.getReason()) {
							canBeCanceled = false;
						}
					}
				}
				for (TargetReason t : invalidReasons) {
					if (t == targetEvent.getReason()) {
						canBeCanceled = false;
					}
				}
				if (canBeCanceled) {
					targetEvent.setCancelled(true);
				}
			}
			return;
		case ENTITY_BREAK_BLOCK:
			BlockBreakEvent bEvent = (BlockBreakEvent) event;
			List<Material> disallowed = Arrays.asList(Material.BEDROCK, Material.BARRIER, Material.COMMAND_BLOCK, Material.BEDROCK, Material.END_PORTAL_FRAME, Material.END_PORTAL, Material.NETHER_PORTAL, Material.STRUCTURE_BLOCK);
			player = bEvent.getPlayer();
			Block block = bEvent.getBlock();
			org.bukkit.Location locDiff = block.getLocation().subtract(player.getLocation().add(0, 1, 0));
			int xMod = (int) Math.signum(locDiff.getX());
			if (xMod == 0) xMod = -1;
			int yMod = (int) Math.signum(locDiff.getY());
			if (yMod == 0) yMod = -1;
			int zMod = (int) Math.signum(locDiff.getZ());
			if (zMod == 0) zMod = -1;
			if (ench.equals(BasicEnchPlugin.getInstance().EXCAVATOR)) {
				for (int x = 0; xMod > 0 ? x <= xMod : x >= xMod; x+=xMod) {
					for (int y = 0; yMod > 0 ? y <= yMod : y >= yMod; y+=yMod) {
						for (int z = 0; zMod > 0 ? z <= zMod : z >= zMod; z+=zMod) {
							Block current = player.getWorld().getBlockAt(block.getLocation().add(x, y, z));
							Material currentMaterial = current.getType();
							if (player.getGameMode().equals(org.bukkit.GameMode.CREATIVE)) {
								current.setType(Material.AIR);
							} else {
								if (!disallowed.contains(current.getType())) {
									current.breakNaturally(items.get(0));
								} if (currentMaterial != current.getType()) {
								ItemMeta im = items.get(0).getItemMeta();
								if (im==null) return;
								Damageable is = (Damageable) im;
								if (im.getEnchantLevel(Enchantment.DURABILITY) != -1) {
									double random = Math.random()*(1+im.getEnchantLevel(Enchantment.DURABILITY));
									if ((int) random == 0) {
										is.setDamage(is.getDamage()+1);
									}
								} else {
									is.setDamage(is.getDamage()+1);
								}
								if (is.getDamage() < items.get(0).getType().getMaxDurability()) {
									items.get(0).setItemMeta((ItemMeta) is);
								} else {
									if (items.get(0).equals(player.getInventory().getItemInMainHand())) {
										player.getInventory().setItemInMainHand(null);
									} else if (items == player.getInventory().getItemInOffHand()) {
										player.getInventory().setItemInOffHand(null);
									}
								}
								}
							}
						}
					}
				}
			}
			return;
		default:
			return;
		}
	}

}

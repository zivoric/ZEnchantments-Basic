package com.hoodiecoder.enchantmentcore.basic;

import org.bukkit.plugin.java.JavaPlugin;

import com.hoodiecoder.enchantmentcore.CustomEnch;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.basic.enchantments.*;

public class BasicEnchPlugin extends JavaPlugin {
	private final EnchantmentHolder holder = new EnchantmentHolder();
	public final CustomEnch DECAY = new DecayEnch(holder);
	public final CustomEnch DOUBLE_AGENT = new DoubleAgentEnch(holder);
	public final CustomEnch ENERGIZER = new EnergizerEnch(holder);
	public final CustomEnch EXCAVATOR = new ExcavatorEnch(holder);
	public final CustomEnch HEALING = new HealingEnch(holder);
	public final CustomEnch LEVITATOR = new LevitatorEnch(holder);
	public final CustomEnch LIFE_STEAL = new LifestealEnch(holder);
	public final CustomEnch SNOWBALL_BOW = new SnowballEnch(holder);
	private static BasicEnchPlugin instance = null;
	
	@Override
	public void onEnable() {
		instance = this;
		holder.registerPendingEnchants();
	}
	public static BasicEnchPlugin getInstance() {
		return instance;
	}
}

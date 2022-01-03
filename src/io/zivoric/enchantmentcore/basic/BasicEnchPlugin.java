package io.zivoric.enchantmentcore.basic;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.basic.enchantments.*;
import io.zivoric.enchantmentcore.plugin.EnchantmentPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class BasicEnchPlugin extends JavaPlugin implements EnchantmentPlugin {
	@Override
	public List<CustomEnch> getEnchants() {
		return List.of(new InconsistencyCurse(this), new DecayEnch(this), new DoubleAgentEnch(this), new EnergizerEnch(this),
				new ExcavatorEnch(this), new HealingEnch(this), new LevitatorEnch(this),
				new LifestealEnch(this), new SnowballEnch(this));
	}
}

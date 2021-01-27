package com.hoodiecoder.enchantmentcore.basic;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.hoodiecoder.enchantmentcore.CoreEnchParent;
import com.hoodiecoder.enchantmentcore.CoreEnchWrapper;
import com.hoodiecoder.enchantmentcore.EnchantmentHolder;
import com.hoodiecoder.enchantmentcore.EnchantmentSlotEnum;
import com.hoodiecoder.enchantmentcore.ItemSlotEnum;
import com.hoodiecoder.enchantmentcore.RarityEnum;

public class BasicEnchPlugin extends JavaPlugin {
	private static final ItemSlotEnum[] armorSlot = {ItemSlotEnum.HEAD, ItemSlotEnum.CHEST, ItemSlotEnum.LEGS, ItemSlotEnum.FEET};
	private final EnchantmentHolder holder = new EnchantmentHolder();
	public final CoreEnchParent LEVITATOR = new CoreEnchWrapper(holder, RarityEnum.RARE, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[] {ItemSlotEnum.MAINHAND}, "levitator", "Levitator", 2).getCoreEnch();;
	public final CoreEnchParent HEALING = new CoreEnchWrapper(holder, RarityEnum.VERY_RARE, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "healing", "Healing", 4).getCoreEnch();
	public final CoreEnchParent LIFE_STEAL = new CoreEnchWrapper(holder, RarityEnum.COMMON, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "life_steal", "Lifesteal", 10).getCoreEnch();
	public final CoreEnchParent DECAY = new CoreEnchWrapper(holder, RarityEnum.RARE, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "decay", "Decay", 3).getCoreEnch();
	public final CoreEnchParent SNOWBALL = new CoreEnchWrapper(holder, RarityEnum.VERY_RARE, EnchantmentSlotEnum.BOW, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "snowball_bow", "Snowball", 1).getCoreEnch();
	public final CoreEnchParent ENERGIZER = new CoreEnchWrapper(holder, RarityEnum.VERY_RARE, EnchantmentSlotEnum.ARMOR, armorSlot, "energizer", "Energizer", 1).getCoreEnch();
	public final CoreEnchParent DOUBLE_AGENT = new CoreEnchWrapper(holder, RarityEnum.VERY_RARE, EnchantmentSlotEnum.ARMOR, armorSlot, "double_agent", "Double Agent", 2).getCoreEnch();
	public final CoreEnchParent EXCAVATOR = new CoreEnchWrapper(holder, RarityEnum.VERY_RARE, EnchantmentSlotEnum.DIGGER, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "excavator", "Excavator", 1).getCoreEnch();
	private static BasicEnchPlugin instance = null;
	
	@Override
	public void onEnable() {
		instance = this;
		holder.registerPendingEnchants();
		PluginManager pm = getServer().getPluginManager();
		BasicEnchListener dmg = new BasicEnchListener(this, new CoreEnchParent[]{LEVITATOR, HEALING, LIFE_STEAL, DECAY, ENERGIZER, SNOWBALL, DOUBLE_AGENT, EXCAVATOR});
		pm.registerEvents(dmg, this);
	}
	public static BasicEnchPlugin getInstance() {
		return instance;
	}
}

package com.hoodiecoder.enchantmentcore.basic;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.hoodiecoder.enchantmentcore.nms.CoreEnchParent;
import com.hoodiecoder.enchantmentcore.nms.CoreEnchWrapper;
import com.hoodiecoder.enchantmentcore.nms.EnchantmentSlotEnum;
import com.hoodiecoder.enchantmentcore.nms.ItemSlotEnum;
import com.hoodiecoder.enchantmentcore.nms.RarityEnum;

public class BasicEnchPlugin extends JavaPlugin {
	private static final ItemSlotEnum[] armorSlot = {ItemSlotEnum.HEAD, ItemSlotEnum.CHEST, ItemSlotEnum.LEGS, ItemSlotEnum.FEET};
	public static final CoreEnchParent LEVITATOR = new CoreEnchWrapper(RarityEnum.RARE, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[] {ItemSlotEnum.MAINHAND}, "levitator", "Levitator", 2).getCoreEnch();
	public static final CoreEnchParent HEALING = new CoreEnchWrapper(RarityEnum.VERY_RARE, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "healing", "Healing", 4).getCoreEnch();
	public static final CoreEnchParent LIFE_STEAL = new CoreEnchWrapper(RarityEnum.COMMON, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "life_steal", "Lifesteal", 10).getCoreEnch();
	public static final CoreEnchParent DECAY = new CoreEnchWrapper(RarityEnum.RARE, EnchantmentSlotEnum.WEAPON, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "decay", "Decay", 3).getCoreEnch();
	public static final CoreEnchParent SNOWBALL = new CoreEnchWrapper(RarityEnum.VERY_RARE, EnchantmentSlotEnum.BOW, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "snowball_bow", "Snowball", 1).getCoreEnch();
	public static final CoreEnchParent ENERGIZER = new CoreEnchWrapper(RarityEnum.VERY_RARE, EnchantmentSlotEnum.ARMOR, armorSlot, "energizer", "Energizer", 1).getCoreEnch();
	public static final CoreEnchParent DOUBLE_AGENT = new CoreEnchWrapper(RarityEnum.VERY_RARE, EnchantmentSlotEnum.ARMOR, armorSlot, "double_agent", "Double Agent", 2).getCoreEnch();
	public static final CoreEnchParent EXCAVATOR = new CoreEnchWrapper(RarityEnum.VERY_RARE, EnchantmentSlotEnum.DIGGER, new ItemSlotEnum[]{ItemSlotEnum.MAINHAND}, "excavator", "Excavator", 1).getCoreEnch();
	private static BasicEnchPlugin instance = null;
	
	@Override
	public void onEnable() {
		instance = this;
		PluginManager pm = getServer().getPluginManager();
		BasicEnchListener dmg = new BasicEnchListener(this, new CoreEnchParent[]{LEVITATOR, HEALING, LIFE_STEAL, DECAY, ENERGIZER, SNOWBALL, DOUBLE_AGENT, EXCAVATOR});
		pm.registerEvents(dmg, this);
	}
	public static BasicEnchPlugin getInstance() {
		return instance;
	}
}

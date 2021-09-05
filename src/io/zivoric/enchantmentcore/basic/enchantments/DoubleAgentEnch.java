package io.zivoric.enchantmentcore.basic.enchantments;

import java.util.List;

import io.zivoric.enchantmentcore.enchant.TargetHandler;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.inventory.ItemStack;

import io.zivoric.enchantmentcore.CustomEnch;
import io.zivoric.enchantmentcore.EnchantmentHolder;
import io.zivoric.enchantmentcore.utils.EnchEnums.Rarity;

public class DoubleAgentEnch extends CustomEnch implements TargetHandler {
	public DoubleAgentEnch(EnchantmentHolder holder) {
		super(holder, "double_agent");
	}

	@Override
	public String getDisplayName() {
		return "Double Agent";
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public Rarity getEnchantmentRarity() {
		return Rarity.VERY_RARE;
	}
	@Override
	public void onTargeted(LivingEntity entity, List<Integer> levels, List<ItemStack> items, EntityTargetEvent event) {
		TargetReason[] neutralReasons = new EntityTargetEvent.TargetReason[]{TargetReason.TARGET_ATTACKED_ENTITY, TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, TargetReason.TARGET_ATTACKED_OWNER, TargetReason.REINFORCEMENT_TARGET};
		TargetReason[] invalidReasons = new EntityTargetEvent.TargetReason[]{TargetReason.TEMPT, TargetReason.CUSTOM, TargetReason.UNKNOWN};
		boolean canBeCanceled = true;
		int scale = 0;
		for (int i = 0; i < levels.size(); i++) {
			scale += levels.get(i);
		}
		if (scale < 4) {
			canBeCanceled = false;
		} else if (scale >= 4 && scale < 8) {
			for (TargetReason t : neutralReasons) {
				if (t == event.getReason()) {
					canBeCanceled = false;
				}
			}
		}
		for (TargetReason t : invalidReasons) {
			if (t == event.getReason()) {
				canBeCanceled = false;
			}
		}
		if (canBeCanceled) {
			event.setCancelled(true);
		}
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
	}
}

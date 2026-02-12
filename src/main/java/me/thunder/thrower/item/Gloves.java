package me.thunder.thrower.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Gloves extends Item {
    public Gloves(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return pRepairCandidate.is(Items.LEATHER) || super.isValidRepairItem(pStack, pRepairCandidate);
    }
}

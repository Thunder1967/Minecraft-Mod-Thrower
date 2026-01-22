package me.thunder.thrower.util;

import me.thunder.thrower.Thrower;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


public class ModTags {
    public static class Items {
        public static final TagKey<Item> CanNotThrowByGloves = createTag("can_not_throw_by_gloves");

        private static TagKey<Item>  createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Thrower.MODID, name));
        }
    }
}

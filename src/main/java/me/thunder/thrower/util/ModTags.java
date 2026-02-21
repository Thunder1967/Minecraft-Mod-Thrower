package me.thunder.thrower.util;

import me.thunder.thrower.Thrower;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;


public class ModTags {
    public static class Items {
        public static final TagKey<Item> CanNotThrowByGloves = createTag("can_not_throw_by_gloves");
        public static final TagKey<Item> GLOVES_ENCHANTABLE = createTag("gloves_enchantable");
        public static final TagKey<Item> QUICKTHROW_ENCHANTABLE = createTag("quickthrow_enchantable");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Thrower.MODID, name));
        }
    }

    public static class Enchantments{
        public static final TagKey<Enchantment> GLOVE_HOVER_EXCLUSIVE = createTag("glove_hover_exclusive");

        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT,ResourceLocation.fromNamespaceAndPath(Thrower.MODID, "exclusive_set/"+name));
        }
    }
}

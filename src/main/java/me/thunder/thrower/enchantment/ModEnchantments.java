package me.thunder.thrower.enchantment;

import me.thunder.thrower.Thrower;
import me.thunder.thrower.util.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> MUSCLE = createKey("muscle");
    public static final ResourceKey<Enchantment> BOOMERANG = createKey("boomerang");
    public static final ResourceKey<Enchantment> LOWGRAVITY = createKey("lowgravity");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemRegistry = context.lookup(Registries.ITEM);
        register(context, MUSCLE,Enchantment.enchantment(Enchantment.definition(
            itemRegistry.getOrThrow(ModTags.Items.GLOVES_ENCHANTABLE),
            itemRegistry.getOrThrow(ModTags.Items.GLOVES_ENCHANTABLE),
            10,
            5,
            Enchantment.dynamicCost(1,11),
            Enchantment.dynamicCost(21,11),
            1,
            EquipmentSlotGroup.ANY))
        );

        register(context, BOOMERANG,Enchantment.enchantment(Enchantment.definition(
                itemRegistry.getOrThrow(ModTags.Items.GLOVES_ENCHANTABLE),
                itemRegistry.getOrThrow(ModTags.Items.GLOVES_ENCHANTABLE),
                5,
                3,
                Enchantment.dynamicCost(12,7),
                Enchantment.dynamicCost(50,0),
                2,
                EquipmentSlotGroup.ANY))
        );

        register(context, LOWGRAVITY,Enchantment.enchantment(Enchantment.definition(
                itemRegistry.getOrThrow(ModTags.Items.GLOVES_ENCHANTABLE),
                itemRegistry.getOrThrow(ModTags.Items.GLOVES_ENCHANTABLE),
                6,
                3,
                Enchantment.dynamicCost(12,7),
                Enchantment.dynamicCost(50,0),
                2,
                EquipmentSlotGroup.ANY))
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key,
                                                          Enchantment.Builder builder){
        context.register(key, builder.build(key.location()));
    }

    private static ResourceKey<Enchantment> createKey(String name){
        return ResourceKey.create(
                Registries.ENCHANTMENT,
                ResourceLocation.fromNamespaceAndPath(Thrower.MODID, name)
        );
    }
}

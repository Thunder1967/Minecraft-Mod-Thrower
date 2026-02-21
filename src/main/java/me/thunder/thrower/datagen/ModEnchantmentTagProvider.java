package me.thunder.thrower.datagen;

import me.thunder.thrower.Thrower;
import me.thunder.thrower.enchantment.ModEnchantments;
import me.thunder.thrower.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Thrower.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // exclusive enchantment
        this.tag(ModTags.Enchantments.GLOVE_HOVER_EXCLUSIVE)
                .addOptional(ModEnchantments.THROWSELF.location())
                .addOptional(ModEnchantments.HOVER.location());
        // appear in enchant table
        this.tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .addOptional(ModEnchantments.MUSCLE.location())
                .addOptional(ModEnchantments.BOOMERANG.location())
                .addOptional(ModEnchantments.QUICKTHROW.location())
                .addOptional(ModEnchantments.LOWGRAVITY.location());
        // appear in wild chest
        this.tag(EnchantmentTags.ON_RANDOM_LOOT)
                .addOptional(ModEnchantments.MUSCLE.location())
                .addOptional(ModEnchantments.BOOMERANG.location())
                .addOptional(ModEnchantments.QUICKTHROW.location())
                .addOptional(ModEnchantments.LOWGRAVITY.location())
                .addOptional(ModEnchantments.HOVER.location());
        // appear in villager trade
        this.tag(EnchantmentTags.TRADEABLE)
                .addOptional(ModEnchantments.MUSCLE.location())
                .addOptional(ModEnchantments.BOOMERANG.location())
                .addOptional(ModEnchantments.QUICKTHROW.location())
                .addOptional(ModEnchantments.LOWGRAVITY.location())
                .addOptional(ModEnchantments.THROWSELF.location());
        this.tag(EnchantmentTags.DOUBLE_TRADE_PRICE)
                .addOptional(ModEnchantments.THROWSELF.location());

        // trade rebalance
        this.tag(EnchantmentTags.TRADES_DESERT_COMMON)
                .addOptional(ModEnchantments.MUSCLE.location());
        this.tag(EnchantmentTags.TRADES_DESERT_SPECIAL)
                .addOptional(ModEnchantments.THROWSELF.location());

        this.tag(EnchantmentTags.TRADES_PLAINS_COMMON)
                .addOptional(ModEnchantments.QUICKTHROW.location());
        this.tag(EnchantmentTags.TRADES_PLAINS_SPECIAL)
                .addOptional(ModEnchantments.THROWSELF.location());

        this.tag(EnchantmentTags.TRADES_SWAMP_COMMON)
                .addOptional(ModEnchantments.BOOMERANG.location());
        this.tag(EnchantmentTags.TRADES_SWAMP_SPECIAL)
                .addOptional(ModEnchantments.THROWSELF.location());

        this.tag(EnchantmentTags.TRADES_JUNGLE_COMMON)
                .addOptional(ModEnchantments.LOWGRAVITY.location());
        this.tag(EnchantmentTags.TRADES_JUNGLE_SPECIAL);

        this.tag(EnchantmentTags.TRADES_SAVANNA_COMMON)
                .addOptional(ModEnchantments.MUSCLE.location());
        this.tag(EnchantmentTags.TRADES_SAVANNA_SPECIAL);

        this.tag(EnchantmentTags.TRADES_SNOW_COMMON)
                .addOptional(ModEnchantments.QUICKTHROW.location());
        this.tag(EnchantmentTags.TRADES_SNOW_SPECIAL);

        this.tag(EnchantmentTags.TRADES_TAIGA_COMMON)
                .addOptional(ModEnchantments.BOOMERANG.location());
        this.tag(EnchantmentTags.TRADES_TAIGA_SPECIAL);

        // enchantment type
        this.tag(EnchantmentTags.TREASURE)
                .addOptional(ModEnchantments.HOVER.location())
                .addOptional(ModEnchantments.THROWSELF.location());
        this.tag(EnchantmentTags.NON_TREASURE)
                .addOptional(ModEnchantments.MUSCLE.location())
                .addOptional(ModEnchantments.BOOMERANG.location())
                .addOptional(ModEnchantments.QUICKTHROW.location())
                .addOptional(ModEnchantments.LOWGRAVITY.location());
    }
}

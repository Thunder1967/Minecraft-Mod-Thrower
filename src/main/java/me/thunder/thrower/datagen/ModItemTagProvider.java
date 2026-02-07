package me.thunder.thrower.datagen;

import me.thunder.thrower.Thrower;
import me.thunder.thrower.item.ModItems;
import me.thunder.thrower.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Thrower.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.CanNotThrowByGloves)
                .add(Items.EGG)
                .add(Items.SNOWBALL)
                .add(Items.EXPERIENCE_BOTTLE)
                .add(Items.FIREWORK_ROCKET)
                .add(Items.LINGERING_POTION)
                .add(Items.SPLASH_POTION)
                .add(Items.WIND_CHARGE)
                .add(Items.TRIDENT);

        this.tag(ModTags.Items.GLOVES_ENCHANTABLE)
                .add(ModItems.GLOVES.get());
    }
}

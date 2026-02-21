package me.thunder.thrower.datagen;

import me.thunder.thrower.Thrower;
import me.thunder.thrower.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLOVES.get())
                .pattern("L L")
                .pattern("LIL")
                .pattern(" L ")
                .define('L', Items.LEATHER)
                .define('I',Items.IRON_NUGGET)
                .unlockedBy("has_leather", has(Items.LEATHER))
                .save(recipeOutput);
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.GLOVES.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        ModItems.NETHERITE_GLOVES.get()
                )
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Thrower.MODID, "netherite_glove_smithing"));
    }
}

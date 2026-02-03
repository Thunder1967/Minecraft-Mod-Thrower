package me.thunder.thrower.datagen;

import com.google.gson.JsonObject;
import me.thunder.thrower.Thrower;
import me.thunder.thrower.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Thrower.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("mob_net")
                .parent(new ModelFile.UncheckedModelFile("minecraft:builtin/entity"))
                .texture("particle", modLoc("entity/mob_net"));

        basicItem(ModItems.GLOVES.get());
    }
}

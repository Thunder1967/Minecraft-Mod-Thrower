package me.thunder.thrower.item;

import me.thunder.thrower.Thrower;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Thrower.MODID);

    public static final Supplier<CreativeModeTab> THROWER_TAB = CREATIVE_MODE_TAB.register("thrower_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GLOVES.get()))
                    .title(Component.translatable("creativetab.thrower.thrower"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MOB_NET);
                        output.accept(ModItems.GLOVES);
                    }).build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

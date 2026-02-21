package me.thunder.thrower.item;

import me.thunder.thrower.Thrower;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Thrower.MODID);

    public static final DeferredItem<Item> MOB_NET = ITEMS.register("mob_net",
            ()->new MobNetItem(new Item.Properties()
                    .stacksTo(1)
            ));

    public static final DeferredItem<Gloves> GLOVES = ITEMS.register("gloves",
            ()->new Gloves(10, new Gloves.Properties()
                    .stacksTo(1)
                    .durability(512)
            ));

    public static final DeferredItem<Gloves> NETHERITE_GLOVES = ITEMS.register("netherite_gloves",
            ()->new Gloves(15, new Gloves.Properties()
                    .stacksTo(1)
                    .durability(4096)
            ));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

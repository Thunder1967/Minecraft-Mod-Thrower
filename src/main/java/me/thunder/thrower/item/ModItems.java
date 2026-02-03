package me.thunder.thrower.item;

import me.thunder.thrower.Thrower;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Thrower.MODID);

    public static final DeferredItem<Item> MOB_NET = ITEMS.register("mob_net",
            ()->new MobNetItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> GLOVES = ITEMS.register("gloves",
            ()->new Item(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

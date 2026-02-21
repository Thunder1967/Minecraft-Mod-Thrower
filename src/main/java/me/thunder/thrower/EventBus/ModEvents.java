package me.thunder.thrower.EventBus;

import me.thunder.thrower.Thrower;
import me.thunder.thrower.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

@EventBusSubscriber(modid = Thrower.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void quickThrow(LivingEntityUseItemEvent.Tick event) {
        if(event.getEntity() instanceof Player player){
            var lookup = player.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            event.setDuration(event.getDuration()-
                    Mth.clamp(event.getItem().getEnchantmentLevel(lookup.getOrThrow(ModEnchantments.QUICKTHROW)),0,20));
        }
    }
}

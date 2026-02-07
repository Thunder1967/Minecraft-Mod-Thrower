package me.thunder.thrower.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.function.BiFunction;

public abstract class ModUtil {
    public static class EntityDataContainer<T>{
        private final EntityDataAccessor<T> accessor;
        private final String key;
        private final TriConsumer<CompoundTag, String, T> nbtWriter;
        private final BiFunction<CompoundTag, String, T> nbtReader;

        public EntityDataContainer(Class<? extends Entity> entityClass,
                                   EntityDataSerializer<T> serializer,
                                   String key,
                                   TriConsumer<CompoundTag, String, T> nbtWriter,
                                   BiFunction<CompoundTag, String, T> nbtReader){
            this.accessor = SynchedEntityData.defineId(entityClass,serializer);
            this.key = key;
            this.nbtWriter = nbtWriter;
            this.nbtReader = nbtReader;
        }

        public EntityDataAccessor<T> getAccessor(){
            return this.accessor;
        }
        public void set(Entity entity,T x) {
            entity.getEntityData().set(this.accessor, x);
        }

        public T get(Entity entity) {
            return entity.getEntityData().get(this.accessor);
        }

        public String getKey(){
            return key;
        }

        public void saveNBT(Entity entity,CompoundTag nbt){
            nbtWriter.accept(nbt, key, get(entity));
        }

        public void loadNBT(Entity entity,CompoundTag nbt){
            if(nbt.contains(key)){
                set(entity, nbtReader.apply(nbt, key));
            }
        }
    }
}

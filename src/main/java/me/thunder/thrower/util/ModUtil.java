package me.thunder.thrower.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;

public abstract class ModUtil {
    public static class SynchedEntityDataContainer<T> extends nbtRegister<T> {
        private final EntityDataAccessor<T> accessor;

        public SynchedEntityDataContainer(Class<? extends Entity> entityClass,
                                          EntityDataSerializer<T> serializer,
                                          String key,
                                          TriConsumer<CompoundTag, String, T> nbtWriter,
                                          BiFunction<CompoundTag, String, T> nbtReader){
            super(key, nbtWriter, nbtReader);
            this.accessor = SynchedEntityData.defineId(entityClass,serializer);
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

        public void saveNBT(Entity entity, CompoundTag nbt) {
            super.saveNBT(nbt, get(entity));
        }

        public void loadNBT(Entity entity, CompoundTag nbt) {
            super.loadNBT(nbt).ifPresent((x)-> set(entity, x));
        }
    }

    public static class nbtContainer<T> extends nbtRegister<T> {
        private T val;
        public nbtContainer(String key,
                            TriConsumer<CompoundTag, String, T> nbtWriter,
                            BiFunction<CompoundTag, String, T> nbtReader,
                            T defaultValue){
            super(key, nbtWriter, nbtReader);
            val = defaultValue;
        }

        public void saveNBT(CompoundTag nbt) {
            super.saveNBT(nbt, val);
        }

        @Override
        public Optional<T> loadNBT(CompoundTag nbt) {
            return super.loadNBT(nbt).map((x)->{
                val=x;
                return x;
            });
        }
    }

    private static abstract class nbtRegister<T>{
        private final String key;
        private final TriConsumer<CompoundTag, String, T> nbtWriter;
        private final BiFunction<CompoundTag, String, T> nbtReader;
        public nbtRegister(String key,
                           TriConsumer<CompoundTag, String, T> nbtWriter,
                           BiFunction<CompoundTag, String, T> nbtReader){
            this.key = key;
            this.nbtWriter = nbtWriter;
            this.nbtReader = nbtReader;
        }
        public String getKey(){
            return key;
        }

        public void saveNBT(CompoundTag nbt, T data){
            nbtWriter.accept(nbt, key, data);
        }

        public Optional<T> loadNBT(CompoundTag nbt){
            if(nbt.contains(key)){
                return Optional.of(nbtReader.apply(nbt, key));
            }
            return Optional.empty();
        }
    }
}

package me.thunder.thrower.util;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

public abstract class ModUtil {
    public static class EntityDataContainer<T>{
        private final EntityDataAccessor<T> accessor;
        public EntityDataContainer(Class<? extends Entity> entityClass, EntityDataSerializer<T> serializer){
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
    }
}

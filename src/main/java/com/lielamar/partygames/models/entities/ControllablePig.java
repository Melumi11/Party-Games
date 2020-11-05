package com.lielamar.partygames.models.entities;

import com.lielamar.partygames.models.entities.pathfindergoals.PathfinderGoalWrapper;
import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;

public class ControllablePig extends EntityPig {

    private ControllableEntityHandler controllableEntityHandler;
    private PathfinderGoalWrapper pathfinderGoalWrapper;

    private boolean isCustomEntity;

    public ControllablePig(Plugin plugin, World world) {
        super(((CraftWorld)world).getHandle());
        this.isCustomEntity = true;

        this.controllableEntityHandler = new ControllableEntityHandler(plugin, this);
        this.pathfinderGoalWrapper = new PathfinderGoalWrapper(this);

        this.pathfinderGoalWrapper.setupPathfinderGoals(true, null, null);
    }

    /**
     * Entity move
     *
     * @param sideMot   Side Motion
     * @param forMot    Forward Motion
     */
    @Override
    public void g(float sideMot, float forMot) {
        if(!isCustomEntity) {
            super.g(sideMot, forMot);
            return;
        }

        if(this.passenger == null || !(this.passenger instanceof EntityHuman)) return;
        if(!this.controllableEntityHandler.isCanMove())                        return;

        EntityHuman human = (EntityHuman) this.passenger;
        this.lastYaw = this.yaw = human.yaw;
        this.pitch = human.pitch * 0.5F;

        this.setYawPitch(this.yaw, this.pitch);

        if(Math.abs(motY) != 0.2) this.motY = 0; // Allow changes of Y level only when we send it through {@link ControllableEntitiesPacketReader [readChickenPacket] }
        super.g(sideMot, forMot);
    }

    @Override
    public void collide(Entity entity) {
        if(!isCustomEntity)
            super.collide(entity);
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        if(!isCustomEntity)
            return super.damageEntity(damagesource, f);
        return false;
    }


    /**
     * Destroys the custom entity (deletes it & everything related)
     */
    public void destroy() {
        getBukkitEntity().setPassenger(null);
        getBukkitEntity().remove();

        this.controllableEntityHandler.destroy();
    }

    /**
     * Spawns a controllable pig attached to player at location
     *
     * @param location   Location to teleport the pig to
     * @return           Created controllable pig
     */
    public ControllablePig spawnCustomEntity(Location location) {
        setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftLivingEntity) getBukkitEntity()).setRemoveWhenFarAway(false);

        ((CraftWorld)location.getWorld()).getHandle().addEntity(ControllablePig.this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return this;
    }


    public ControllableEntityHandler getControllableEntityHandler() {
        return this.controllableEntityHandler;
    }
}
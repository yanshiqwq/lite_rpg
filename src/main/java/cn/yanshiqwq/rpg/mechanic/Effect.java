package cn.yanshiqwq.rpg.mechanic;

import cn.yanshiqwq.rpg.Main;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

@SuppressWarnings("unused")
public class Effect {

    public static void applyFreeze(Entity attacker, LivingEntity entity, double baseDamage, int duration) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, 255));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, duration, 128));
        entity.getWorld().strikeLightningEffect(entity.getLocation());
        entity.getWorld().spawnParticle(Particle.END_ROD, entity.getLocation(), 128, 0.2, 0.8, 0.2);
        BukkitRunnable frostTimer = new BukkitRunnable() {
            @Override
            public void run() {
                entity.damage(2.0, attacker);
            }
        };
        frostTimer.runTaskTimer(Main.getInstance(), 0L, 40L);
        new BukkitRunnable() {
            @Override
            public void run() {
                frostTimer.cancel();
                entity.damage(baseDamage * 1.5, attacker);
            }
        }.runTaskLater(Main.getInstance(), duration);
    }

    public static void applyFrost(Entity attacker, LivingEntity entity) {
        int stack = Objects.requireNonNull(entity.getPotionEffect(PotionEffectType.SLOW)).getAmplifier();
        if (stack < 5) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, stack + 1));
            entity.damage(1.0);
        }
    }

    public static void applyEvaporation(Entity attacker, LivingEntity entity, double baseDamage) {
        entity.damage(baseDamage * 1.6, attacker);
    }
}

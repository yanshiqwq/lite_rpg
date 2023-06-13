package cn.yanshiqwq.rpg.listener;

import cn.yanshiqwq.rpg.mechanic.ShieldedMob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ShieldListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if (!(event.getEntity() instanceof ShieldedMob entity)) {
            return;
        }
        if (entity.getShield() <= 0){
            return;
        }
        entity.onDamage(event);
    }
    public void onAttack(EntityDamageByEntityEvent event){
        if (!(event.getDamager() instanceof ShieldedMob entity)) {
            return;
        }
        if (entity.getShield() <= 0){
            return;
        }
        entity.onAttack();
    }
}
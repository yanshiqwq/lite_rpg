package cn.yanshiqwq.rpg.listener;

import cn.yanshiqwq.rpg.manager.ElementManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ElementAttackListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (ElementManager.getType(attacker) == null && ElementManager.getType(entity) == null){
            return;
        }
        // TODO
    }
}

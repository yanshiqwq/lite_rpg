package cn.yanshiqwq.rpg.listener;

import cn.yanshiqwq.rpg.mechanic.ShieldedMob;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerAttackListener implements Listener {
    @EventHandler
    public void onAttack(PrePlayerAttackEntityEvent event){
        if(!(event.getAttacked() instanceof ShieldedMob entity)){
            return;
        }
        Player player = event.getPlayer();
        String entityName;
        try {
            entityName = entity.getName();
        } catch (Exception ex) {
            entityName = entity.getType().getName();
        }
        player.sendActionBar("§8%s 护甲值: §r%s §7/ §r%s".formatted(entityName, entity.getShield(), entity.getMaxShield()));
    }
}

package cn.yanshiqwq.rpg.listener;

import cn.yanshiqwq.rpg.mechanic.ShieldedMob;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerAttackListener implements Listener {
    @EventHandler
    public void onAttack(PrePlayerAttackEntityEvent event){
        Player player = event.getPlayer();
        if (player.getEquipment().getItemInMainHand().getType() == Material.AIR) {
            player.damage(1.0d, player);
        }
        if (event.getAttacked() instanceof ShieldedMob entity) {
            showShieldInfo(player, entity, event);
        }

    }
    public void showShieldInfo(Player player, ShieldedMob entity, PrePlayerAttackEntityEvent event){
        String entityName;
        try {
            entityName = entity.getName();
        } catch (Exception ex) {
            entityName = entity.getType().getName();
        }
        double percent = entity.getShield() / entity.getMaxShield();
        int progress = (int) (percent * 16);
        String progressBar1 = "|".repeat(progress);
        String progressBar2 = "|".repeat(16 - progress);
        player.sendActionBar("§8%s 护甲值: §f%s§1%s §7%.2f%%".formatted(entityName, progressBar1, progressBar2, percent));
    }
}

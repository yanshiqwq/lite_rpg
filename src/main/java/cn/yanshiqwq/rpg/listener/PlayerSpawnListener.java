package cn.yanshiqwq.rpg.listener;

import cn.yanshiqwq.rpg.mechanic.ElementMob;
import cn.yanshiqwq.rpg.manager.ElementManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerSpawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        ElementMob.ElementType elementType = ElementManager.getRandomElementType();
        ElementManager.setType(player, elementType);
    }
}
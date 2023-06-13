package cn.yanshiqwq.rpg.listener;

import cn.yanshiqwq.rpg.Element;
import cn.yanshiqwq.rpg.manager.ElementManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Random;

public class PlayerSpawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Element.Type type = getRandomElementType();
        ElementManager.setPlayerType(player.getUniqueId(), type);
    }

    private Element.Type getRandomElementType() {
        Element.Type[] types = Element.Type.values();
        int index = new Random().nextInt(types.length);
        return types[index];
    }
}
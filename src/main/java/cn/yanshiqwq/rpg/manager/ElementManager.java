package cn.yanshiqwq.rpg.manager;

import cn.yanshiqwq.rpg.Element;
import cn.yanshiqwq.rpg.Main;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

public class ElementManager {
    private static final Path MapFilePath;
    private static final HashMap<UUID, Element.Type> playerMap = new HashMap<>();
    private static final Gson gson = new Gson();
    static {
        MapFilePath = Main.getInstance().getDataFolder().toPath().resolve("data").resolve("element").resolve("player.json");
        try {
            loadPlayerMap();
        } catch (Exception ex) {
            Main.getInstance().getLogger().severe("Failed to load user map, using an empty map: " + ex);
        }
    }

    public static void setPlayerType(UUID uuid, Element.Type type) {
        playerMap.put(uuid, type);
        savePlayerMap();
    }

    public static Element.Type getPlayerType(Player player) {
        return playerMap.get(player.getUniqueId());
    }

    private static void loadPlayerMap() throws IOException {
        if (!Files.exists(MapFilePath)) return;

        String json = new String(Files.readAllBytes(MapFilePath));
        playerMap.putAll(gson.fromJson(json, new TypeToken<HashMap<UUID, Element.Type>>(){}.getType()));
    }

    private static void savePlayerMap() {
        String json = gson.toJson(playerMap);
        try {
            Files.createDirectories(MapFilePath.getParent());
            Files.write(MapFilePath, json.getBytes());
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Failed to save user map: " + e);
        }
    }
}

package cn.yanshiqwq.rpg.manager;

import cn.yanshiqwq.rpg.mechanic.ElementMob;
import cn.yanshiqwq.rpg.Main;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("unused")
public class ElementManager {
    private static final Path MapFilePath;
    private static final HashMap<UUID, ElementMob.ElementType> mobMap = new HashMap<>();
    private static final Gson gson = new Gson();
    static {
        MapFilePath = Main.getInstance().getDataFolder().toPath().resolve("data").resolve("element.json");
        try {
            loadMap();
        } catch (Exception ex) {
            Main.getInstance().getLogger().severe("Failed to load element map, using an empty map: " + ex);
        }
    }

    public static void setType(LivingEntity entity, ElementMob.ElementType elementType) {
        mobMap.put(entity.getUniqueId(), elementType);
        saveMap();
    }

    public static ElementMob.ElementType getType(Entity entity) {
        return mobMap.get(entity.getUniqueId());
    }

    private static void loadMap() throws IOException {
        if (!Files.exists(MapFilePath)) return;

        String json = new String(Files.readAllBytes(MapFilePath));
        mobMap.putAll(gson.fromJson(json, new TypeToken<HashMap<UUID, ElementMob.ElementType>>(){}.getType()));
    }

    private static void saveMap() {
        String json = gson.toJson(mobMap);
        try {
            Files.createDirectories(MapFilePath.getParent());
            Files.write(MapFilePath, json.getBytes());
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Failed to save element map: " + e);
        }
    }

    public static ElementMob.ElementType getRandomElementType() {
        ElementMob.ElementType[] types = ElementMob.ElementType.values();
        int index = new Random().nextInt(types.length);
        return types[index];
    }

    public static void setRandomElement(LivingEntity entity){
        setType(entity, getRandomElementType());
    }
}

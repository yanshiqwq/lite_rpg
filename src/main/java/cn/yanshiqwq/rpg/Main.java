package cn.yanshiqwq.rpg;

import cn.yanshiqwq.rpg.listener.PlayerAttackListener;
import cn.yanshiqwq.rpg.listener.PlayerSpawnListener;
import cn.yanshiqwq.rpg.listener.ShieldListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public final static String PREFIX = "§b|§r ";
    private static boolean disabled = false;
    private static Main INSTANCE = null;

    public static Main getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading LiteRPG...");
        INSTANCE = this;

        getServer().getPluginManager().registerEvents(new PlayerAttackListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldListener(), this);

        if (disabled) {
            getLogger().warning("A reload is detected!");
            getLogger().warning("Reloading may cause many problems, like incorrect data and memory leaks.");
            getLogger().warning("If you meet problems, please restart your server.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Goodbye!");
        disabled = true;
    }
}

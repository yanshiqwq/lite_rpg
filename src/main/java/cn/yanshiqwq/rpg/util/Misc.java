package cn.yanshiqwq.rpg.util;

import org.bukkit.Material;

public class Misc {
    public static boolean isPickaxe(Material material) {
        return material == Material.WOODEN_PICKAXE ||
                material == Material.STONE_PICKAXE ||
                material == Material.IRON_PICKAXE ||
                material == Material.GOLDEN_PICKAXE ||
                material == Material.DIAMOND_PICKAXE;
    }
}

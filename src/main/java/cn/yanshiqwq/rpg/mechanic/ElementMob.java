package cn.yanshiqwq.rpg.mechanic;

import cn.yanshiqwq.rpg.manager.ElementManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class ElementMob {
    public enum ElementType {
        FROST, FIRE, WATER, ENDER, LIGHT, DARKNESS
    }
    public static double getDamageMultiplier(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof LivingEntity entity)){
            return 1.0;
        }
        LivingEntity attacker = (LivingEntity) event.getDamager();
        ElementType attackerType = ElementManager.getType(attacker);
        ElementType entityType = ElementManager.getType(entity);
        switch (attackerType) {
            case FROST -> {
                return switch (entityType) {
                    case FIRE -> 1.2;
                    case WATER -> 0.3;
                    case ENDER -> 1.0;
                    default -> 0.9;
                };
            }
            case FIRE -> {
                switch (entityType) {
                    case WATER -> {
                        Effect.applyEvaporation(attacker, entity, Objects.requireNonNull(attacker.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue());
                        return 1.6;
                    }
                    case FROST -> {
                        Effect.applyFrost(attacker, entity);
                        return 0.9;
                    }
                    case LIGHT -> {
                        return 0.8;
                    }
                    default -> {
                        return 1.0;
                    }
                }
            }
            case WATER -> {
                switch (entityType) {
                    case FROST -> {
                        Effect.applyFrost(attacker, entity);
                        return 1.5;
                    }
                    case FIRE -> {
                        return 0.3;
                    }
                    case ENDER -> {
                        return 0.5;
                    }
                    default -> {
                        return 1.0;
                    }
                }
            }
            case ENDER -> {
                return switch (entityType) {
                    case WATER -> 1.5;
                    case LIGHT -> 1.1;
                    case DARKNESS -> 0.8;
                    default -> 1.0;
                };
            }
            case LIGHT -> {
                return switch (entityType) {
                    case FIRE -> 0.8;
                    case DARKNESS -> 1.2;
                    default -> 1.0;
                };
            }
            case DARKNESS -> {
                return switch (entityType) {
                    case ENDER -> 0.8;
                    case LIGHT -> 1.2;
                    default -> 1.0;
                };
            }
            default -> {
                return 1.0;
            }
        }
    }
}

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
        if(entityType == ElementType.FIRE){
            if (attackerType == ElementType.WATER) {
                Effect.applyEvaporation(attacker, entity, Objects.requireNonNull(attacker.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue());
            } else if (attackerType == ElementType.FROST) {
                Effect.applyFrost(attacker, entity);
            }
        } else if (entityType == ElementType.WATER) {
            if (attackerType == ElementType.FROST) {
                Effect.applyFrost(attacker, entity);
            }
        }
        switch (entityType) {
            case FROST -> {
                return switch (attackerType) {
                    case FIRE -> 1.2;
                    case WATER -> 0.5;
                    default -> 1.0;
                };
            }
            case FIRE -> {
                return switch (attackerType) {
                    case WATER -> 1.6;
                    case FROST -> 0.5;
                    case LIGHT -> 0.8;
                    default -> 1.0;
                };
            }
            case WATER -> {
                return switch (attackerType) {
                    case FROST -> 1.5;
                    case FIRE -> 0.3;
                    case ENDER -> 0.8;
                    default -> 1.0;
                };
            }
            case ENDER -> {
                return switch (attackerType) {
                    case WATER -> 1.5;
                    case LIGHT -> 1.2;
                    case DARKNESS -> 0.8;
                    default -> 1.0;
                };
            }
            case LIGHT -> {
                return switch (attackerType) {
                    case FIRE -> 0.8;
                    case DARKNESS -> 1.2;
                    default -> 1.0;
                };
            }
            case DARKNESS -> {
                return switch (attackerType) {
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

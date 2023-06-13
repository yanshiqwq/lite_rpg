package cn.yanshiqwq.rpg.mechanic;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static cn.yanshiqwq.rpg.util.Misc.isPickaxe;

public abstract class ShieldedMob implements LivingEntity {
    private double maxShieldRatio = 0.4;
    private double shield = getMaxShield();
    private double shieldDamageMultiplier = 1.0;
    private double shieldDamageReductionMultiplier = 0.7;
    private double shieldBreakDamageMultiplier = 2.4;
    private boolean allowShieldRegeneration = false;

    public void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof LivingEntity attacker)){
            return;
        }
        ItemStack item = Objects.requireNonNull(attacker.getEquipment()).getItemInMainHand();
        if (!isPickaxe(item.getType())) {
            return;
        }
        double damage = event.getDamage();
        if (shield - damage * shieldDamageMultiplier <= 0) {
            event.setDamage(damage * shieldBreakDamageMultiplier);
            shield = 0;
        } else {
            event.setDamage(damage * shieldDamageReductionMultiplier);
        }
    }

    public void onAttack() {
        shield += shield * (Math.random() * 0.1 + 1);
    }

    public void recoverShieldByRatio(double ratio) {
        setShield(getMaxShield() * ratio);
    }
    public double getShield() {
        return shield;
    }

    public void setShield(double shield) {
        this.shield = shield;
    }

    public double getMaxShield(){
        return getHealth() * maxShieldRatio;
    }

    public double getShieldDamageMultiplier() {
        return shieldDamageMultiplier;
    }

    public void setShieldDamageMultiplier(int multiplier) {
        this.shieldDamageMultiplier = multiplier;
    }

    public double getShieldDamageReductionMultiplier() {
        return shieldDamageReductionMultiplier;
    }

    public void setShieldDamageReductionMultiplier(int multiplier){
        this.shieldDamageReductionMultiplier = multiplier;
    }

    public double getShieldBreakDamageMultiplier() {
        return shieldBreakDamageMultiplier;
    }

    public void setShieldBreakDamageMultiplier(int multiplier){
        this.shieldBreakDamageMultiplier = multiplier;
    }

    public boolean getShieldRegeneration(){
        return allowShieldRegeneration;
    }

    public void setShieldRegeneration(boolean bool){
        this.allowShieldRegeneration = bool;
    }

    public double getMaxShieldRatio(){
        return maxShieldRatio;
    }

    public void setMaxShieldRatio(double ratio){
        this.maxShieldRatio = ratio;
    }
}

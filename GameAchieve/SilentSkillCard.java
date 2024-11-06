package GameAchieve;

public class SilentSkillCard extends Card{
    private int effectValue;
    private String buffEffect;
    private String debuffEffect;
    private boolean isExhaust;
    public SilentSkillCard(String name, String description, int cost, int effectValue, String buffEffect, String debuffEffect,boolean isExhaust){
        super(name, description, cost,effectValue,buffEffect,debuffEffect,isExhaust);
        this.effectValue = effectValue;
        this.buffEffect = buffEffect;
        this.debuffEffect = debuffEffect;
    }



    public int getEffectValue() {
        return effectValue;
    }

    @Override
    public void play(Character player, Monster enemy) {
        // 应用技能效果
        if (player.getEnergy() >= getCost()) {
            player.useEnergy(getCost());
            if (buffEffect != null) {
                player.applyBuff(buffEffect);
            }

            if (debuffEffect != null) {
                enemy.applyDebuff(debuffEffect);
            }
            System.out.println(player.getName() + " plays " + getName() + " on " + enemy.getName());
        } else {
            System.out.println(player.getName() + " does not have enough energy to use " + getName() + ".");
        }

    }

    @Override
    public boolean isExhaust() {
        return isExhaust;
    }
}
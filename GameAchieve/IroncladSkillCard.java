package GameAchieve;

public class IroncladSkillCard extends Card{
    private int effectValue;
    private String buffEffect;
    private String debuffEffect;
    private boolean isExhaust;
    public IroncladSkillCard(String name, String description, int cost, int effectValue, String buffEffect, String debuffEffect,boolean isExhaust){
        super(name, description, cost,effectValue,buffEffect,debuffEffect,isExhaust);
        this.effectValue = effectValue;
        this.buffEffect = buffEffect;
        this.debuffEffect = debuffEffect;
        this.isExhaust=isExhaust;
    }



    public int getDefendValue() {
        return effectValue;
    }

    public void setDefendValue(int defendValue) {
        this.effectValue = defendValue;
    }

    @Override
    public void play(Character player, Monster enemy) {
        // 应用技能效果
        if (player.getEnergy() >= getCost()) {
            player.useEnergy(getCost());
            if(getName().equals("Consolidate")){                //当打出一张名为“consolidate”的手牌时，让当前格挡值翻倍。
                System.out.println("Playing card: " + getName());
                player.doubleEffectValue(); // 翻倍格挡值
                System.out.println("Effect Value after playing Consolidate: " + player.getEffectValue());
            }else{
                player.updateEffectValue(effectValue);
            }


            if (buffEffect != null) {
                player.applyBuff(buffEffect);
            }

            if (debuffEffect != null) {
                enemy.applyDebuff(debuffEffect);
            }


            System.out.println(player.getName() + " plays " + getName() + " on " + player.getName());
        } else {
            System.out.println(player.getName() + " does not have enough energy to use " + getName() + ".");
        }
    }

    @Override
    public boolean isExhaust() {
        return isExhaust;
    }
}

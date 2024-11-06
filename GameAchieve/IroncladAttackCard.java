package GameAchieve;

public class IroncladAttackCard extends Card {
    private int baseDamage; // 基础伤害属性
    private String buffEffect;
    private String debuffEffect;
    private int cost;
    private boolean isExhaust;


    // 默认构造函数
    public IroncladAttackCard() {
        this("Ironclad Attack Card", "An attack card for Ironclad.", 2,5,null,null,false);
    }

    // 支持不同名字、描述和伤害的构造函数
    public IroncladAttackCard(String name, String description, int cost, int baseDamage, String buffEffect, String debuffEffect,boolean isExhaust) {
        super(name, description, cost,baseDamage,buffEffect,debuffEffect,isExhaust);
        this.baseDamage = baseDamage;
        this.buffEffect = buffEffect;
        this.debuffEffect = debuffEffect;
        this.isExhaust=isExhaust;
    }



    @Override
    public void play(Character player, Monster enemy) {

        int totalDamage = baseDamage + player.getStrength() ; // 计算总伤害
        if(player.getEnergy()>=getCost()){
            player.useEnergy(getCost());
            enemy.takeDamage(totalDamage);
            System.out.println(player.getName() + " uses " + getName() + " on " + enemy.getName() + " and deals " + totalDamage + " damage!\n");
            if (buffEffect != null) {
                player.applyBuff(buffEffect); // 应用增益效果到玩家自己
            }
            String prefixde1 = "Weakness +";
            if (debuffEffect != null) {
                if(debuffEffect.startsWith(prefixde1)){
                    String remainigPartDebuff = debuffEffect.substring(prefixde1.length());
                    int numWeakness = Integer.parseInt(remainigPartDebuff);
                    System.out.println("-----------------" + numWeakness + "---------------------");
                    player.updateWeakness(numWeakness);
                }
                enemy.applyDebuff(debuffEffect); // 应用减益效果到敌人
            }
        }else{
            System.out.println(player.getName() + " does not have enough energy to use " + getName() + ".");
        }
    }

    @Override
    public boolean isExhaust() {
        return isExhaust;
    }
}
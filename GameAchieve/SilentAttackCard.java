package GameAchieve;

public class SilentAttackCard extends Card {
    private int baseDamage; // 基础伤害属性
    private String buffEffect;
    private String debuffEffect;
    private boolean isExhaust;

    // 默认构造函数
    public SilentAttackCard() {
        this("Slient Attack Card", "An attack card for Ironclad.", 2,5,null,null,false);
    }

    // 支持不同名字、描述和伤害的构造函数
    public SilentAttackCard(String name, String description, int cost, int baseDamage, String buffEffect, String debuffEffect,boolean isExhaust) {
        super(name, description, cost,baseDamage,buffEffect,debuffEffect,isExhaust);
        this.baseDamage = baseDamage;
        this.buffEffect = buffEffect;
        this.debuffEffect = debuffEffect;
        this.isExhaust=isExhaust;
    }



    @Override
    public void play(Character player, Monster enemy) {
        int totalDamage = baseDamage + player.getStrength(); // 计算总伤害
        if(player.getEnergy()>=getCost()){
            player.useEnergy(getCost());
            enemy.takeDamage(totalDamage);
            System.out.println(player.getName() + " uses " + getName() + " on " + enemy.getName() + " and deals " + totalDamage + " damage!\n");
        }else{
            System.out.println(player.getName() + " does not have enough energy to use " + getName() + ".");
        }
    }

    @Override
    public boolean isExhaust() {
        return isExhaust;
    }
}
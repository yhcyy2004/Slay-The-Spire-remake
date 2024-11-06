package GameAchieve;

import java.util.List;

public abstract class Card {
    private String name;
    private String description;
    private int cost;
    private int baseDamage;
    private String buffEffect;
    private String debuffEffect;
    private int block;
    private int strength;
    private boolean isExhaust; // 标记是否是消耗牌
    private boolean isUsed;
    protected List<String> RelicCage;//存放遗物
    protected boolean isRelic;      //判断遗物是否存在

    public Card(String name, String description, int cost, int baseDamage, String buffEffect, String debuffEffect,boolean isExhaust) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.baseDamage = baseDamage;
        this.buffEffect = buffEffect;
        this.debuffEffect = debuffEffect;
        this.isExhaust=isExhaust;
    }

    public abstract void play(Character player, Monster enemy);

    public boolean isRelic() {
        if(!RelicCage.isEmpty()){
            return true;
        }
        return false;
    }

    public List<String> getRelicCage() {
        return RelicCage;
    }
    public void addRelicToCage(Relic relic) {
        RelicCage.add(relic.getName());
    }

    public int getBlock() {
        return block;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return baseDamage;
    }

    public String getBuffEffect() {
        return buffEffect;
    }

    public String getDebuffEffect() {
        return debuffEffect;
    }

    public boolean isExhaust() {
        return isExhaust;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }



    public void play(Character player, Character enemy) {
        // 应用攻击效果
        if (isRelic()) {
            for (String relic : RelicCage) {
                if (relic.equals("Shield of Protection")) {
                    this.baseDamage = 3 * (baseDamage) / 2; // 四舍五入计算总伤害
                    enemy.takeDamage(this.baseDamage);
                } else {
                    enemy.takeDamage(baseDamage);
                }
            }
        } else {
            enemy.takeDamage(baseDamage);
        }
        // 应用技能效果
        if (player.getEnergy() >= getCost()) {
            player.useEnergy(getCost());

            if (buffEffect != null) {
                if (name.equals("Consolidate")) {
                    player.updateEffectValue(block * 2);
                }
                String prefixbu1 = "Strength +";
                if (buffEffect.startsWith(prefixbu1)) {
                    String remainingPartBuff = buffEffect.substring(prefixbu1.length());
                    int numStrength = Integer.parseInt(remainingPartBuff);
                    System.out.println("-----------------" + numStrength + "---------------------");
                    player.updateStrength(numStrength); // 将 num 添加到 strength 里
                }
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

            System.out.println(player.getName() + " plays " + getName() + " on " + enemy.getName());
            // 根据卡牌属性决定是弃牌还是消耗牌
            if (isExhaust) {
                player.exhaustCard(this);
            } else {
                player.discardCard(this);
            }
            setUsed(true);
        } else {
            System.out.println(player.getName() + " does not have enough energy to use " + getName() + ".");
        }
    }
}
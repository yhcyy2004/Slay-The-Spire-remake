package GameAchieve;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Character {
    private int strength;

    public Monster(String name, int health, int strength) {
        super(name, health, strength);
        this.strength = strength;
    }

    // 记录每次的扣血
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }

    //判断怪物是否死亡
    public boolean isDead(){
        return getHealth() <=0;
    }
    //结束战斗并显示奖励选择
    private void endBattle(){
        System.out.println("----------------Battle End!----------------");
        System.out.println("You have defeated the monster!Well done!");
        displayRewards();
    }
    //显示奖励选择
    private void displayRewards(){
        System.out.println("----------------Rewards----------------");
    }

    // 添加Monsters的行为和属性
    // 怪物攻击方法
    public void attack(Character target) {
        int damage = this.strength; // 可以根据需要添加更复杂的伤害计算逻辑
        System.out.println(this.getName() + " attacks " + target.getName() + " for " + damage + " damage!");
        target.takeDamage(damage);
    }

    // 生成随机怪物的方法
    public static Monster generateRandomMonster() {
        Random random = new Random();
        int monsterType = random.nextInt(12); // 生成0到11之间的随机数

        String name;
        int health;
        int strength;

        switch (monsterType) {
            case 0,1,2,3,4:
                name = "Goblin";
                health = 10+ random.nextInt(21); // 生命值范围：10-30
                strength = 10;
                break;
            case 5,6,7,8,9:
                name = "Orc";
                health = 30 + random.nextInt(21); // 生命值范围：30-50
                strength = 10;
                break;
            case 10:
                name = "The Evil Dragon";
                health = 80 + random.nextInt(41); // 生命值范围：80-120
                strength = 10;
                break;
            default:
                name = "Slime";
                health = 150;                             //生命值：150
                strength = 10;
                break;
        }

        return new Monster(name, health, strength);
    }
}
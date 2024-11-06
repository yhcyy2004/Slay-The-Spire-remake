package GameAchieve;

public class IroncladAbilityCard extends Card {
    private int effect;
    private String buffEffect;
    private String debuffEffect;
    private String prefix = "Strength +";
    private boolean isExhaust;

    public IroncladAbilityCard() {
        this("Ironclad ability Card", "An ability card for Ironclad.", 2,0,null,null,true);
    }

    public IroncladAbilityCard(String name, String description, int cost, int effect, String buffEffect, String debuffEffect,boolean isExhaust) {
        super(name, description,cost,effect,buffEffect,debuffEffect,isExhaust);
        this.effect=effect;
        this.buffEffect = buffEffect;
        this.debuffEffect = debuffEffect;
        this.isExhaust=isExhaust;
    }

    @Override
    public void play(Character player, Monster enemy) {
        // 应用技能效果
        if (player.getEnergy() >= getCost()) {
            player.useEnergy(getCost());

            if (buffEffect != null) {
                String prefix = "Strength +";
                if (buffEffect.startsWith(prefix)) {                    //???
                    String remainingPart = buffEffect.substring(prefix.length());
                    System.out.println(remainingPart);
                    int num = Integer.parseInt(remainingPart);
                    System.out.println("-----------------" + num + "---------------------");
                    player.updateStrength(num); // 将 num 添加到 strength 里
                    System.out.println(player.getName() + " plays " + getName() + " on " + player.getName());
                }
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
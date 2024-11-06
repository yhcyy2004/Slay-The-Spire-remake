package GameAchieve;

import java.util.*;

public class Character {
    protected int health;
    protected int effectValue;
    protected int energy;
    protected int strength;
    protected int baseDamage;
    protected String name;
    protected String buffEffect;
    protected String debuffEffect;
    protected CardFactory cardFactory;
    protected boolean isRelic;      //判断遗物是否存在
    protected List<Card> deck;
    protected List<Card> drawPile; //抽牌堆
    protected List<Card> discardPile;//弃牌堆
    protected List<Card> exhaustPile;//消耗牌堆
    protected List<String> RelicCage;//存放遗物
    private static List<Relic> relics = new ArrayList<>();
    public Character(String name, int health, int strength) {
        this.name = name;
        this.drawPile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.exhaustPile = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.RelicCage=new ArrayList<>();
        this.energy = 4;
        this.cardFactory = cardFactory;
        this.health = health;
        this.strength = strength;
        this.buffEffect = null;
        this.debuffEffect = null;
        this.effectValue = 0;
        this.isRelic=false;

        //初始化抽牌堆
        initializeDrawPile();
    }
    //将所有牌先放入抽牌堆
    public void initializeDrawPile(){
        drawPile.clear();
        drawPile.addAll(deck);
    }
    //抽牌
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            if (!discardPile.isEmpty()) {
                drawPile.addAll(discardPile);
                discardPile.clear();
                System.out.println("Shuffling discard pile into draw pile.");
            } else {
                System.out.println("No cards left to draw.");
                return null;
            }
        }
        Card card = drawPile.remove(0);
        System.out.println("Draw card: " + card.getName());
        return card;
    }
    // 弃牌
    public void discardCard(Card card) {
        discardPile.add(card);
        System.out.println("Discarded card: " + card.getName());
    }

    // 消耗牌
    public void exhaustCard(Card card) {
        exhaustPile.add(card);
        System.out.println("Exhausted card: " + card.getName());
    }

    // 查看抽牌堆
    public void viewDrawPile() {
        System.out.println("Draw Pile:");
        for (Card card : drawPile) {
            System.out.println("- " + card.getName());
        }
    }

    // 查看弃牌堆
    public void viewDiscardPile() {
        System.out.println("Discard Pile:");
        for (Card card : discardPile) {
            System.out.println("- " + card.getName());
        }
    }

    // 查看消耗牌堆
    public void viewExhaustPile() {
        System.out.println("Exhaust Pile:");
        for (Card card : exhaustPile) {
            System.out.println("- " + card.getName());
        }
    }

    // 随机抽取指定数量的牌，确保不重复
    public static List<Card> drawRandomCards(int count) {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(AttackCardManager.getAttackCards());
        allCards.addAll(SkillCardManager.getSkillCards());
        allCards.addAll(AbilityCardManager.getAbilityCards());

        List<Card> drawnCards = new ArrayList<>();
        Set<Card> drawnSet = new HashSet<>();

        if (allCards.isEmpty() || count > allCards.size()) {
            return drawnCards;
        }

        Random random = new Random();
        while (drawnCards.size() < count) {
            int index = random.nextInt(allCards.size());
            Card card = allCards.get(index);
            if (!drawnSet.contains(card)) {
                drawnCards.add(card);
                drawnSet.add(card);
            }
        }

        return drawnCards;
    }

    public void addSkillCard() {
        Card skillCard = cardFactory.createSkillCard();
        deck.add(skillCard);
    }

    public void addAttackCard() {
        Card attackCard = cardFactory.createAttackCard();
        deck.add(attackCard);
    }

    public void addAbilityCard() {
        Card abilityCard = cardFactory.createAbilityCard();
        deck.add(abilityCard);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void useEnergy(int amount) {
        if (energy >= amount) {
            energy -= amount;
            System.out.println(name + " uses " + amount + " energy. Remaining energy: " + energy);
        } else {
            System.out.println(name + " does not have enough energy to use this card.");
        }
    }


    // 判断怪物是否死亡
    public boolean isDead() {
        return getHealth() <= 0;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(int effectValue) {
        this.effectValue = effectValue;
    }

    public String getBuffEffect() {
        return buffEffect;
    }

    public void setBuffEffect(String buffEffect) {
        this.buffEffect = buffEffect;
    }

    public String getDebuffEffect() {
        return debuffEffect;
    }

    public void setDebuffEffect(String debuffEffect) {
        this.debuffEffect = debuffEffect;
    }

    public void applyBuff(String buff) {
        buffEffect = buff;
        System.out.println(name + " gains " + buff + ".");
    }

    public void applyDebuff(String debuff) {
        debuffEffect = debuff;
        System.out.println(name + " suffers " + debuff + ".");
    }

    public void resetEnergy() {
        if(isRelic()==true){
            for (int i = 0; i < RelicCage.size(); i++) {
                this.energy=5;
            }
        }else{
            this.energy=4;
        }
    }

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



    public void resetStrength(){    //重置力量
        if(isRelic()){
            for (int i = 0; i < RelicCage.size(); i++) {
                this.strength=3;
            }
        }else{
            this.strength=0;
        }
    }

    public void recoverEnergy() {
        energy += 2;
    }

    public void enStrength() {
        strength += 5;
    }

    public void doubleEffectValue() {
        this.effectValue *= 2;
    }

    public void takeDamage(int damage) {
        int effectiveDamage = damage - effectValue;
            if (effectiveDamage > 0) {
                health -= effectiveDamage;
                effectValue = 0;
                System.out.println(name + " takes " + effectiveDamage + " damage. Remaining health: " + health);
            } else {
                effectValue = effectValue - damage;
                System.out.println(name + " blocks " + damage + " damage. Remaining block: " + effectValue);
            }
        }

    public void updateEffectValue(int value) {
        this.effectValue += value;
    }
    public void updateWeakness(int value){
        strength -=value;
        System.out.println(name + "'s strength decreased by " + value + ". New strength is: " +strength);
    }
    public int getStrength() {
        return strength;
    }

    public void updateStrength(int num) {
            this.strength += num;
        System.out.println(name + "'s strength increased by " + num + ". New strength: " + strength);
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void heal() {
        health += 5;
    }
}
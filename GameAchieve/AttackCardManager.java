package GameAchieve;

import java.util.*;

public class AttackCardManager  {
    private static List<Card> attackCards = new ArrayList<>();
    private static Random random = new Random();

       // 随机抽取指定数量的攻击牌，确保不重复
    public static List<Card> drawRandomAttackCards(int count) {
        List<Card> drawnCards = new ArrayList<>();
        Set<Card> drawnSet = new HashSet<>();

        if (attackCards.isEmpty() || count > attackCards.size()) {
            return drawnCards;
        }

        while (drawnCards.size() < count) {
            int index = random.nextInt(attackCards.size());
            Card card = attackCards.get(index);
            if (!drawnSet.contains(card)) {
                drawnCards.add(card);
                drawnSet.add(card);
            }
        }

        return drawnCards;
    }

    // 添加攻击牌
    public static void addAttackCard(Card card) {
        attackCards.add(card);
    }

    // 获取所有的攻击牌
    public static List<Card> getAttackCards() {
        return new ArrayList<>(attackCards);
    }
    

    // 添加默认的“打击”牌
    public static void addDefaultStrikeCards() {
        for (int i = 0; i < 5; i++) {
            attackCards.add(new IroncladAttackCard("Strike", "A basic attack card.(cost : 1 baseDamage : 6)\n", 1, 6,null,null,false));
        }
    }


}
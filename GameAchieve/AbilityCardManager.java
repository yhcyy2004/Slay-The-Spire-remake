package GameAchieve;

import java.util.*;

public class AbilityCardManager {
    private static List<Card> abilityCards = new ArrayList<>();
    private static Random random = new Random();



    // 添加能力牌
    public static void addAbilityCard(Card card) {
        abilityCards.add(card);
    }

    // 随机抽取指定数量的能力牌，确保不重复
    public static List<Card> drawRandomSkillCards(int count) {
        List<Card> drawnCards = new ArrayList<>();
        Set<Card> drawnSet = new HashSet<>();

        if (abilityCards.isEmpty() || count > abilityCards.size()) {
            return drawnCards;
        }

        while (drawnCards.size() < count) {
            int index = random.nextInt(abilityCards.size());
            Card card = abilityCards.get(index);
            if (!drawnSet.contains(card)) {
                drawnCards.add(card);
                drawnSet.add(card);
            }
        }

        return drawnCards;
    }
    // 获取所有的能力牌
    public static List<Card> getAbilityCards() {
        return new ArrayList<>(abilityCards);
    }
}
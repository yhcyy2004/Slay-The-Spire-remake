package GameAchieve;

import java.util.*;

public class SkillCardManager  {
    private static List<Card> skillCards = new ArrayList<>();
    private static Random random = new Random();


    // 添加技能牌
    public static void addSkillCard(Card card) {
        skillCards.add(card);
    }

    // 随机抽取指定数量的技能牌，确保不重复
    public static List<Card> drawRandomSkillCards(int count) {
        List<Card> drawnCards = new ArrayList<>();
        Set<Card> drawnSet = new HashSet<>();

        if (skillCards.isEmpty() || count > skillCards.size()) {
            return drawnCards;
        }

        while (drawnCards.size() < count) {
            int index = random.nextInt(skillCards.size());
            Card card = skillCards.get(index);
            if (!drawnSet.contains(card)) {
                drawnCards.add(card);
                drawnSet.add(card);
            }
        }

        return drawnCards;
    }
    // 获取所有的技能牌
    public static List<Card> getSkillCards() {
        return new ArrayList<>(skillCards);
    }

    // 添加默认的技能牌
    public static void addDefaultSkillCards() {
        for (int i = 0; i < 5; i++) {
            skillCards.add(new IroncladSkillCard("Defend", "A basic defending card.(cost : 1 Defend : 6)\n", 1, 6, null, null,false));
        }
    }

}
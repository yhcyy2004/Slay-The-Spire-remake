package GameAchieve;

import java.util.ArrayList;
import java.util.List;

public class SwordRelic implements Relic {
    private static List<Relic> relics = new ArrayList<>();

    @Override
    public void applyEffect(Character character) {
        character.setStrength(character.getStrength() + 3);
        character.applyBuff("Strength +3"); // 调用 applyBuff 方法，将 buff 添加到 buff 列表中
    }

    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public String getBuffEffect() {
        return "Strength +3";
    }

    @Override
    public String getDebuffEffect() {
        return null;
    }
}
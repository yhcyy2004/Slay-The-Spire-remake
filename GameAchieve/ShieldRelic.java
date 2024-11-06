package GameAchieve;

import java.util.ArrayList;
import java.util.List;

public class ShieldRelic implements Relic {
    private static List<Relic> relics = new ArrayList<>();

    @Override
    public void applyEffect(Character character) {
        character.setEffectValue(character.getEffectValue() +5);
        character.applyBuff("Defense +5"); // 调用 applyBuff 方法，将 buff 添加到 buff 列表中
    }

    @Override
    public String getName() {
        return "Shield";
    }

    @Override
    public String getBuffEffect() {
        return "Defense +5";
    }

    @Override
    public String getDebuffEffect() {
        return null;
    }
}
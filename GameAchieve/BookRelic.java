package GameAchieve;

import java.util.ArrayList;
import java.util.List;

public class BookRelic implements Relic{
    private static List<Relic> relics = new ArrayList<>();

    @Override
    public void applyEffect(Character character) {
        character.setEnergy(character.getEnergy() + 1);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getBuffEffect() {
        return null;
    }

    @Override
    public String getDebuffEffect() {
        return null;
    }
}

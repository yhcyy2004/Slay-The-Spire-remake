package GameAchieve;

import java.util.*;

public class RelicManager {
    private static List<Relic> relics = new ArrayList<>();

    static {

        relics.add(new ExampleRelic("Sword","A beautifully crafted weapon.\n With it, you can enhance your strength." ,new ConcreteEffect("Strength +3", null)));
        relics.add(new ExampleRelic("Book", "A book that seems to record some unknown skills.\n With it, you can boost your energy.",new ConcreteEffect(null, null)));
        relics.add(new ExampleRelic("Shield", "A sturdy shield that provides additional defense.\n With it, you can reduce incoming damage.", new ConcreteEffect("Defense +5", null)));
        relics.add(new ExampleRelic("Ring", "A mystical ring that enhances your intellect.\n With it, you can gain increased critical hit chance.", new ConcreteEffect("Critical Hit Chance +50%", null)));
        // Add more relics here
    }

    public static List<Relic> getRandomRelics(int count) {
        List<Relic> selectedRelics = new ArrayList<>();
        Random random = new Random();
        Set<Relic> usedRelics = new HashSet<>();

        while (selectedRelics.size() < count) {
            Relic relic = relics.get(random.nextInt(relics.size()));
            if (!usedRelics.contains(relic)) {
                selectedRelics.add(relic);
                usedRelics.add(relic);
            }
        }

        // Remove selected relics from the pool
        relics.removeAll(selectedRelics);

        return selectedRelics;
    }

    public static Relic getRandomDrop() {
        if (relics.isEmpty()) {
            return null;
        }

        Random random = new Random();
        Relic relic = relics.get(random.nextInt(relics.size()));
        relics.remove(relic);

        return relic;
    }
}

package GameAchieve;

public interface Relic {
    void applyEffect(Character character);
    String getName();
    String getBuffEffect();
    String getDebuffEffect();
}

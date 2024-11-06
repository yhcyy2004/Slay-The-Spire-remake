package GameAchieve;

public class ConcreteEffect implements Effect{
    private String buffEffect;
    private String debuffEffect;

    public ConcreteEffect(String buffEffect, String debuffEffect) {
        this.buffEffect = buffEffect;
        this.debuffEffect = debuffEffect;
    }

    @Override
    public void applyTo(Character character) {
        if (character instanceof Character) {
            Character player = (Character) character;
        }
    }
}

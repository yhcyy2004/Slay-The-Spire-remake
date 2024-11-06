package GameAchieve;

public class ExampleRelic implements Relic{
    private String name;
    private String description;
    private Effect effect;

    public ExampleRelic(String name, String description,Effect effect) {
        this.name = name;
        this.description=description;
        this.effect = effect;
    }

    @Override
    public void applyEffect(Character character) {
        effect.applyTo(character);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBuffEffect() {
        return getBuffEffect();
    }

    @Override
    public String getDebuffEffect() {
        return getDebuffEffect();
    }
}

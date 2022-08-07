public abstract class State {
    protected Character character;

    public State(Character character) {
        this.character = character;
    }

    public abstract String onDead();
    public abstract String onPoison();
    public abstract String onNormal();
    public abstract String onAttack(String enemy);
}

public class Character {
    private String name;
    private State state;

    public Character(String name) {
        this.name = name;
        state = new NormalState(this);
    }

    public String attack(String enemy) {
        return name + " attacks " + enemy + ".";
    }

    public void changeState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }
}

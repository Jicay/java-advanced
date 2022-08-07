public class DeadState extends State {

    public DeadState(Character character) {
        super(character);
    }

    @Override
    public String onDead() {
        return "The character is already dead.";
    }

    @Override
    public String onPoison() {
        return "The character can't be poisoned.";
    }

    @Override
    public String onNormal() {
        return "The character can't be resurrected.";
    }

    @Override
    public String onAttack(String enemy) {
        return "Impossible, character is dead.";
    }
}

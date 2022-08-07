public class PoisonState extends State {

    public PoisonState(Character character) {
        super(character);
    }

    @Override
    public String onDead() {
        character.changeState(new DeadState(character));
        return "Character is now dead.";
    }

    @Override
    public String onPoison() {
        return "Character is already poisoned.";
    }

    @Override
    public String onNormal() {
        character.changeState(new NormalState(character));
        return "Character is no more poisoned.";
    }

    @Override
    public String onAttack(String enemy) {
        return character.attack(enemy) + " " + character.getName() + " lost 10HP because he's poisoned.";
    }
}

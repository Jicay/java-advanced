public class NormalState extends State {

    public NormalState(Character character) {
        super(character);
    }

    @Override
    public String onDead() {
        character.changeState(new DeadState(character));
        return "Character is now dead.";
    }

    @Override
    public String onPoison() {
        character.changeState(new PoisonState(character));
        return "Character is now poisoned.";
    }

    @Override
    public String onNormal() {
        return "Character is already alive.";
    }

    @Override
    public String onAttack(String enemy) {
        return character.attack(enemy);
    }
}

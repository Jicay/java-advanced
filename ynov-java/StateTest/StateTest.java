import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StateTest {

    @Test
    void checkClass() {
        var stateClass = State.class;
        assertThat(stateClass)
                .withFailMessage("State should be abstract")
                .isAbstract();
        var deadStateClass = DeadState.class;
        assertThat(deadStateClass.getSuperclass())
                .withFailMessage("DeadState should inherit State")
                .isEqualTo(State.class);
        var normalStateClass = NormalState.class;
        assertThat(normalStateClass.getSuperclass())
                .withFailMessage("NormalState should inherit State")
                .isEqualTo(State.class);
        var poisonStateClass = PoisonState.class;
        assertThat(poisonStateClass.getSuperclass())
                .withFailMessage("PoisonState should inherit State")
                .isEqualTo(State.class);
    }

    @Test
    void state() {
        Character c = new Character("Test name");
        String res = c.getState().onAttack("Enemy name");
        assertThat(res)
                .withFailMessage("Result for attack should be \"Test name attacks Enemy name.\", but was %s", res)
                .isEqualTo("Test name attacks Enemy name.");
        res = c.getState().onNormal();
        assertThat(res)
                .withFailMessage("Result for attack should be \"Character is already alive.\", but was %s", res)
                .isEqualTo("Character is already alive.");
        res = c.getState().onPoison();
        assertThat(res)
                .withFailMessage("Result for attack should be \"Character is now poisoned.\", but was %s", res)
                .isEqualTo("Character is now poisoned.");
        res = c.getState().onAttack("New enemy name");
        assertThat(res)
                .withFailMessage("Result for attack should be \"Test name attacks New enemy name. Test name lost 10HP because he's poisoned.\", but was %s", res)
                .isEqualTo("Test name attacks New enemy name. Test name lost 10HP because he's poisoned.");
        res = c.getState().onPoison();
        assertThat(res)
                .withFailMessage("Result for attack should be \"Character is already poisoned.\", but was %s", res)
                .isEqualTo("Character is already poisoned.");
        res = c.getState().onNormal();
        assertThat(res)
                .withFailMessage("Result for attack should be \"Character is no more poisoned.\", but was %s", res)
                .isEqualTo("Character is no more poisoned.");
        res = c.getState().onDead();
        assertThat(res)
                .withFailMessage("Result for attack should be \"Character is now dead.\", but was %s", res)
                .isEqualTo("Character is now dead.");
        res = c.getState().onAttack("Again new enemy");
        assertThat(res)
                .withFailMessage("Result for attack should be \"Impossible, character is dead.\", but was %s", res)
                .isEqualTo("Impossible, character is dead.");
        res = c.getState().onNormal();
        assertThat(res)
                .withFailMessage("Result for attack should be \"The character can't be resurrected.\", but was %s", res)
                .isEqualTo("The character can't be resurrected.");
        res = c.getState().onPoison();
        assertThat(res)
                .withFailMessage("Result for attack should be \"The character can't be poisoned.\", but was %s", res)
                .isEqualTo("The character can't be poisoned.");
        res = c.getState().onDead();
        assertThat(res)
                .withFailMessage("Result for attack should be \"The character is already dead.\", but was %s", res)
                .isEqualTo("The character is already dead.");
    }

}
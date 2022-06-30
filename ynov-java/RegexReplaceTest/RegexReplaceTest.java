import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class RegexReplaceTest {

    @Test
    void removeUnits() {
        String res1 = RegexReplace.removeUnits("15323cm");
        assertThat(res1)
                .withFailMessage("The \"15323cm\" should be replace by \"15323\", but was %s", res1)
                .isEqualTo("15323");
        String res9 = RegexReplace.removeUnits("15323scm");
        assertThat(res9)
                .withFailMessage("The \"15323scm\" should be replace by \"15323scm\", but was %s", res9)
                .isEqualTo("15323scm");
        String res2 = RegexReplace.removeUnits("15323 cm");
        assertThat(res2)
                .withFailMessage("The \"15323 cm\" should be replace by \"15323 cm\", but was %s", res2)
                .isEqualTo("15323 cm");
        String res3 = RegexReplace.removeUnits("15323cm²");
        assertThat(res3)
                .withFailMessage("The \"15323cm²\" should be replace by \"15323cm²\", but was %s", res3)
                .isEqualTo("15323cm²");
        String res4 = RegexReplace.removeUnits("15323cm et demi");
        assertThat(res4)
                .withFailMessage("The \"15323cm\" should be replace by \"15323 et demi\", but was %s", res4)
                .isEqualTo("15323 et demi");
        String res5 = RegexReplace.removeUnits("15323€");
        assertThat(res5)
                .withFailMessage("The \"15323€\" should be replace by \"15323\", but was %s", res5)
                .isEqualTo("15323");
        String res6 = RegexReplace.removeUnits("15323 €");
        assertThat(res6)
                .withFailMessage("The \"15323 €\" should be replace by \"15323 €\", but was %s", res6)
                .isEqualTo("15323 €");
        String res7 = RegexReplace.removeUnits("15323€²");
        assertThat(res7)
                .withFailMessage("The \"15323€²\" should be replace by \"15323€²\", but was %s", res7)
                .isEqualTo("15323€²");
        String res8 = RegexReplace.removeUnits("15323€ et demi");
        assertThat(res8)
                .withFailMessage("The \"15323€ et demi\" should be replace by \"15323 et demi\", but was %s", res8)
                .isEqualTo("15323 et demi");
        String res10 = RegexReplace.removeUnits("15323m€");
        assertThat(res10)
                .withFailMessage("The \"15323m€\" should be replace by \"15323m€\", but was %s", res10)
                .isEqualTo("15323m€");
    }

    @Test
    void removeFeminineAndPlural() {
        String res1 = RegexReplace.removeFeminineAndPlural("maison");
        assertThat(res1)
                .withFailMessage("The \"maison\" should be replace by \"maison\", but was %s", res1)
                .isEqualTo("maison");
        String res3 = RegexReplace.removeFeminineAndPlural("maisons");
        assertThat(res3)
                .withFailMessage("The \"maisons\" should be replace by \"maison\", but was %s", res3)
                .isEqualTo("maison");
        String res2 = RegexReplace.removeFeminineAndPlural("elle");
        assertThat(res2)
                .withFailMessage("The \"elle\" should be replace by \"el\", but was %s", res2)
                .isEqualTo("el");
        String res4 = RegexReplace.removeFeminineAndPlural("elles");
        assertThat(res4)
                .withFailMessage("The \"elles\" should be replace by \"el\", but was %s", res4)
                .isEqualTo("el");
        String res5 = RegexReplace.removeFeminineAndPlural("chevaux");
        assertThat(res5)
                .withFailMessage("The \"chevaux\" should be replace by \"chevau\", but was %s", res5)
                .isEqualTo("chevau");
        String res6 = RegexReplace.removeFeminineAndPlural("bille");
        assertThat(res6)
                .withFailMessage("The \"bille\" should be replace by \"bill\", but was %s", res6)
                .isEqualTo("bill");
        String res7 = RegexReplace.removeFeminineAndPlural("billes");
        assertThat(res7)
                .withFailMessage("The \"billes\" should be replace by \"bill\", but was %s", res7)
                .isEqualTo("bill");
        String res8 = RegexReplace.removeFeminineAndPlural("belles maisonettes");
        assertThat(res8)
                .withFailMessage("The \"belles maisonettes\" should be replace by \"bel maisonett\", but was %s", res8)
                .isEqualTo("bel maisonett");
    }
}
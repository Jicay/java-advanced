public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(RegexReplace.removeUnits("32cm et 50€"));
        System.out.println(RegexReplace.removeUnits("32 cm et 50 €"));
        System.out.println(RegexReplace.removeUnits("32cms et 50€!"));
        System.out.println(RegexReplace.removeFeminineAndPlural("le lapin joue à la belle balle avec des animaux rigolos pour gagner les billes bleues"));
    }
}
import java.util.regex.Pattern;

public class RegexReplace {
    public static String removeUnits(String s) {
        Pattern pattern = Pattern.compile("(?<=\\d)(cm|€)(?= |$)");
        return pattern.matcher(s).replaceAll("");
    }

    public static String removeFeminineAndPlural(String s) {
        Pattern pattern = Pattern.compile("(?<=[a-z])(((?<=el)(le)?|e*)[sx]?)(?= |$)");
        return pattern.matcher(s).replaceAll("");
    }
}
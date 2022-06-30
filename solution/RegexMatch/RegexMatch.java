import java.util.regex.Pattern;

public class RegexMatch {
    public static boolean containsOnlyAlpha(String s) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        return pattern.matcher(s).matches();
    }

    public static boolean startWithLetterAndEndWithNumber(String s) {
        Pattern pattern = Pattern.compile("^[A-Za-z].*\\d$");
        return pattern.matcher(s).matches();
    }

    public static boolean containsAtLeast3SuccessiveA(String s) {
        Pattern pattern = Pattern.compile("^.*A{3,}.*$");
        return pattern.matcher(s).matches();
    }
}
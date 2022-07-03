import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollect {
    public static Map<Character, List<String>> mapByFirstLetter(Stream<String> s) {
        return s.collect(groupingBy(s1 -> s1.toUpperCase().charAt(0)));
    }

    public static Map<Integer, Optional<Integer>> getMaxByModulo4(Stream<Integer> s) {
        return s.collect(groupingBy(i -> i % 4, maxBy(comparingInt(i -> i))));
    }

    public static String orderAndConcatWithSharp(Stream<String> s) {
        return s.sorted().collect(Collectors.joining(" # ", "{", "}"));
    }
}
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;

/**
 * Observed Pin.
 *
 * @author David Sapozhnik
 */
public class ObservedPin {

    private static final Map<String, List<String>> variations = Map.of(
            "1", List.of("1", "2", "4"),
            "2", List.of("2", "1", "3", "5"),
            "3", List.of("3", "2", "6"),
            "4", List.of("4", "1", "5", "7"),
            "5", List.of("5", "2", "4", "6", "8"),
            "6", List.of("6", "3", "5", "9"),
            "7", List.of("7", "4", "8"),
            "8", List.of("8", "5", "7", "9", "0"),
            "9", List.of("9", "6", "8"),
            "0", List.of("0", "8")
    );

    public static List<String> getPINs(String observed) {
        var result = new ArrayList<String>();
        var source = stream(observed.split("")).map(variations::get).toList();
        var indexes = new int[source.size()];
        var lastIndex = source.size() - 1;
        var currentPosition = lastIndex;
        while (currentPosition >= 0) {
            var inProgress = lastIndex;
            while (inProgress >= currentPosition) {
                var pass = constructPassword(source, indexes);
                addIfNotContains(result, pass);
                var maxIndexAllowed = source.get(inProgress).size() - 1;
                if (inProgress != lastIndex && indexes[inProgress] < maxIndexAllowed) {
                    indexes[inProgress]++;
                    inProgress = lastIndex;
                } else {
                    indexes[inProgress]++;
                    if (indexes[inProgress] > maxIndexAllowed) {
                        for (var i = inProgress; i < indexes.length; i++) {
                            indexes[i] = 0;
                        }
                        inProgress--;
                    }
                }
            }
            currentPosition--;
        }
        return result;
    }

    /* Private methods */

    private static String constructPassword(List<List<String>> source, int[] indexes) {
        var res = new StringBuilder();
        for (var i = 0; i < indexes.length; i++) {
            res.append(source.get(i).get(indexes[i]));
        }
        return res.toString();
    }

    private static <T> void addIfNotContains(Collection<T> collection, T element) {
        if (!collection.contains(element)) {
            collection.add(element);
        }
    }
}

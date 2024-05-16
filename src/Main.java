import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author David Sapozhnik
 */
public class Main {

    public static void main(String[] args) {
        var actual = ObservedPin.getPINs("369");
        var expected = new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"};
        System.out.println(Arrays.toString(expected));
        actual.sort(String::compareTo);
        System.out.println(actual);

    }

    private static final Map<Character, String> ADJACENTS = new HashMap<>() {{
        put('1', "124");
        put('2', "2135");
        put('3', "326");
        put('4', "4157");
        put('5', "54268");
        put('6', "6953");
        put('7', "748");
        put('8', "87590");
        put('9', "986");
        put('0', "08");
    }};

    public static List<String> getPINs(String observed) {
        List<String> ans = List.of("");
        for (char c : observed.toCharArray()) {
            var tmp = new ArrayList<String>();
            for (char cc : ADJACENTS.get(c).toCharArray()) {
                for (var s : ans) {
                    tmp.add(s + cc);
                }
            }
            ans = tmp;
        }
        return ans;
    }
}
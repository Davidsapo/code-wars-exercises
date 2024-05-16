/**
 * @author David Sapozhnik
 */
public class AddingBigNumbers {

    public static String add(String a, String b) {
        if (a.length() <= 19 && b.length() <= 19) {
            return String.valueOf(Long.parseLong(a) + Long.parseLong(b));
        } else {
            return "Implement";
        }
    }
}

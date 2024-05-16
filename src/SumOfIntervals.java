import java.util.Arrays;

/**
 * @author David Sapozhnik
 */
public class SumOfIntervals {

    public static int sumIntervals(int[][] intervals) {
        var result = 0;
        var lastCloseValue = 0;
        var toSort = Arrays.asList(intervals);
        toSort.sort((a1, a2) -> {
            if (a1[0] == a2[0]) {
                return a1[1] - a2[1];
            } else {
                return a1[0] - a2[0];
            }
        });
        for (int[] arr : toSort) {
            if (lastCloseValue != 0) {
                if (arr[0] < lastCloseValue) {
                    if (arr[1] > lastCloseValue) {
                        result = result + (arr[1] - lastCloseValue);
                        lastCloseValue = arr[1];
                    }
                } else {
                    result = result + (arr[1] - arr[0]);
                    lastCloseValue = arr[1];
                }
            } else {
                result = result + (arr[1] - arr[0]);
                lastCloseValue = arr[1];
            }
        }
        return result;
    }
}

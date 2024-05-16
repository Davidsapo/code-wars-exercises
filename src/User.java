/**
 * CodeWars User.
 *
 * @author David Sapozhnik
 */
public class User {

    private static final int MAX_PROGRESS = 100;
    private static final int MIN_RANK = -8;
    private static final int MAX_RANK = 8;
    private static final int EXCLUDED_RANK = 0;
    private static final int SAME_RANK_POINTS = 3;
    private static final int ONE_RANK_LOWER_POINTS = 1;

    public int rank = -8;
    public int progress = 0;

    public void incProgress(int completedRank) {
        validateRan(completedRank);
        if (this.rank == MAX_RANK) return;
        int progressToAdd = calculateProgressToAdd(completedRank);
        if (progressToAdd != 0) {
            var totalProgress = progress + progressToAdd;
            rank = rank + totalProgress / MAX_PROGRESS;
            progress = totalProgress % MAX_PROGRESS;
            if (rank == 0) {
                rank++;
            } else if (rank >= MAX_RANK) {
                this.rank = MAX_RANK;
                progress = 0;
            }
        }
    }

    private int calculatePoints(int rank) {
        var difference = calculateDifference(rank);
        return 10 * difference * difference;
    }

    private int calculateDifference(int rank) {
        var max = Math.max(rank, this.rank);
        var min = Math.min(rank, this.rank);
        var difference = max - min;
        if (max > 0 && min < 0) difference--;
        return difference;
    }

    private int calculateProgressToAdd(int completedRank) {
        int progressToAdd;
        if (completedRank == rank) progressToAdd = SAME_RANK_POINTS;
        else if (completedRank > rank) progressToAdd = calculatePoints(completedRank);
        else if (calculateDifference(completedRank) == 1) progressToAdd = ONE_RANK_LOWER_POINTS;
        else progressToAdd = 0;
        return progressToAdd;
    }

    private void validateRan(int completedRank) {
        if (completedRank < MIN_RANK || completedRank > MAX_RANK || completedRank == EXCLUDED_RANK) {
            throw new RuntimeException("Invalid rank");
        }
    }
}

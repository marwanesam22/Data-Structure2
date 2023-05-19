import java.util.*;
import java.util.Random;

public class Generation {
    public Integer[] generateDistinctArray(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Array size must be at least 1.");
        }
        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        while (set.size() != size) {
            int num = random.nextInt(); // Generate a random number
            set.add(num); // Add the number to the set
        }
        return set.toArray(new Integer[0]);
    }
}

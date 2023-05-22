package Main;

import java.util.Random;

public class Generate {

    public static int[] generate(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = Math.abs(random.nextInt(2001) - 1000); // Generates random integer between -1000 and 1000
        }

        return array;
    }
}

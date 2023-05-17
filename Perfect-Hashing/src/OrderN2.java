import java.util.Arrays;

public class OrderN2 extends PerfectHashing {
    private Integer[] hashTable;

    private int numberOfCollision;

    public OrderN2(int[] values) {
        tableLength = values.length * values.length;
        universalHashingFamily = new UniversalHashingFamily(get_u(values), get_b());
        hashing_matrix = universalHashingFamily.getrandomizedH();
        hashTable = new Integer[tableLength];
        numberOfCollision = 0;
    }

    public void hashValues(int[] values) {
        if ((tableLength & (tableLength - 1)) != 0) {
            System.out.println("The M is not a power of 2, get the hell out!");
            return;
        }
        for (int value : values) {
            int index = universalHashingFamily.HF(value, hashing_matrix);
            index %= tableLength;
            if (hashTable[index] == null || (hashTable[index] == value)) {
                hashTable[index] = value;
            } else {
                hashTable = new Integer[tableLength];
                hashing_matrix = universalHashingFamily.getrandomizedH();
                numberOfCollision++;
                hashValues(values);
            }
        }
    }


    public int getNumberOfCollisions() {
        return numberOfCollision;
    }

    public int printHTable() {
        int count = 0;
        for (Integer val : hashTable) {
            if (val != null) count++;
            System.out.print(val + " ");
        }
        System.out.println();
        return count;
    }

}

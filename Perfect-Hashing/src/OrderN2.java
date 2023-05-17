import java.util.Arrays;

public class OrderN2 {
    private Integer[] hashTable;
    private int tableLength;
    private int[][] hashing_matrix;
    private int numberOfCollision;
    UniversalHashingFamily universalHashingFamily;

    public OrderN2(int[] values) {
        tableLength = values.length * values.length;
        numberOfCollision = 0;
        universalHashingFamily = new UniversalHashingFamily(get_u(values), get_b(values));
    }

    // number of bits in hash table index
    private int get_b(int[] values) {
        return (int) (Math.floor(Math.log(tableLength) / Math.log(2)) + 1);
    }

    //number of bits in the largest value of the input
    private int get_u(int[] values) {
        int maxBits = 0;
        for (int value : values) {
            int numBits = Integer.SIZE - Integer.numberOfLeadingZeros(value);
            maxBits = Math.max(numBits, maxBits);
        }
        return maxBits;
    }

    public void hashValues(int[] values) {
        if ((tableLength & (tableLength - 1)) != 0) {
            System.out.println("The M is not a power of 2, get the hell out!");
            return;
        }
        hashTable = new Integer[tableLength];
        hashing_matrix = universalHashingFamily.getrandomizedH();
        for (int value : values) {
            int index = universalHashingFamily.HF(value, hashing_matrix);
            index %= tableLength;
            if (hashTable[index] == null || (hashTable[index] == value)) {
                hashTable[index] = value;
            } else {
                numberOfCollision++;
                hashValues(values);
            }
        }
    }


    public int getNumberOfCollisions() {
        return numberOfCollision;
    }

    public int printHTable() {
        System.out.println("Number of items is : " + tableLength);
        int count = 0;
        for (Integer val : hashTable) {
            if (val != null) count++;
            System.out.print(val + " ");
        }
        System.out.println();
        return count;
    }

}

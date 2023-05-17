import java.util.Arrays;
import java.util.Random;

public class UniversalHashingFamily {
    private final int u; // max Key size (number of bits)
    private final int b; // Index size (number of bits) such that M = 2^b


    public UniversalHashingFamily(int u, int b) {
        this.u = u;
        this.b = b;
//        hashing_matrix = randomizingH(b, u);
    }

    public int[][] getrandomizedH() {
        int[][] matrix = new int[b][u];
        Random random = new Random(); //instance of the Random class to get a random number each time
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < u; j++) {
                matrix[i][j] = random.nextInt(2); //to get a random number {0, 1}
            }
        }
        return matrix;
    }


    private int[] convertToBinary(int key, int maxNoOfBits) {
        int[] binaryNumber = new int[maxNoOfBits];
        for (int i = maxNoOfBits - 1; i >= 0; i--) { //because the number is begins logically from the end of the array
            binaryNumber[i] = key & 1; //extracting the LSB of the number each iteration
            key >>= 1;
        }
        return binaryNumber;
    }


    private int convertToDecimal(int[] binaryNumber) {
        int decimal = 0;
        for (int j : binaryNumber) {
            decimal = (decimal << 1) | j;
        }
        return decimal;
    }


    public int HF(int x,int[][] hashing_matrix) {
        int[] binaryX = convertToBinary(x, u);
        int[] hx = new int[b];
        for (int i = 0; i < b; i++) {
            int sum = 0; //getting the sum of product for each row
            for (int j = 0; j < u; j++) {
                sum += hashing_matrix[i][j] * binaryX[j];
            }
            hx[i] = sum % 2; //must be binary
        }
        return (convertToDecimal(hx));
    }

}

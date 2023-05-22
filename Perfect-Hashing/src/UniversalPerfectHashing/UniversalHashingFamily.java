package UniversalPerfectHashing;

import java.util.Arrays;
import java.util.Random;

public class UniversalHashingFamily {
    public final int u;
    public final int b;
    public long[] coefficients;
    int numCoefficients;
    final long MOD= (long) 1e9+7;

    protected int get_b(int tableLength) {
        return (int)Math.floor(Math.log((double)tableLength) / Math.log(2.0));
    }


    public UniversalHashingFamily(int u, int n) {
        this.b = get_b(Integer.highestOneBit((n - 1) << 1));
        this.u = u;
        numCoefficients = 100;
        this.coefficients = generateCoefficients();
    }

    public int[][] getrandomizedH() {

        int[][] matrix = new int[this.b][this.u];
        Random random = new Random();

        for(int i = 0; i < this.b; ++i) {
            for(int j = 0; j < this.u; ++j) {
                matrix[i][j] = random.nextInt(2);
            }
        }
        return matrix;
    }

    private int[] convertToBinary(int key, int maxNoOfBits) {
        int[] binaryNumber = new int[maxNoOfBits];
        for(int i = maxNoOfBits - 1; i >= 0; --i) {
            binaryNumber[i] = key & 1;
            key >>= 1;
        }
        return binaryNumber;
    }

    private int convertToDecimal(int[] binaryNumber) {
        int decimal = 0;
        int[] var3 = binaryNumber;
        int var4 = binaryNumber.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int j = var3[var5];
            decimal = decimal << 1 | j;
        }

        return decimal;
    }

    public int HF(int x, int[][] hashing_matrix) {
        int[] binaryX = this.convertToBinary(x, this.u);
        int[] hx = new int[this.b];

        for(int i = 0; i < this.b; ++i) {
            int sum = 0;

            for(int j = 0; j < this.u; ++j) {
                sum += hashing_matrix[i][j] * binaryX[j];
            }

            hx[i] = sum % 2;
        }

        return this.convertToDecimal(hx);
    }

    //------------string-hasher methods-----------------------------

    public int computeHash(String s, int[][] hashing_matrix){
        long hashValue = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hashValue = (hashValue * this.coefficients[i % this.numCoefficients] + c) % MOD;
        }
        return HF((int)hashValue, hashing_matrix);
    }

    private long[] generateCoefficients() {
        int numCoefficients = this.numCoefficients;
        Random random = new Random();
        long[] coefficients = new long[numCoefficients];
        for (int i = 0; i < numCoefficients; i++) {
            coefficients[i] = random.nextLong(MOD - 1) + 1; // Random coefficient between 1 and prime-1
        }
        return coefficients;
    }



}
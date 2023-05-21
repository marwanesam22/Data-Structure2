package UniversalPerfectHashing;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;

public class UniversalHashingFamily {
    private final int u;
    private final int b;

    public UniversalHashingFamily(int u, int b) {
        this.u = u;
        this.b = b;
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
}


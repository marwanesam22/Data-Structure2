public class PerfectHashing {
    protected  int tableLength;

    public  int[][] hashing_matrix;

    UniversalHashingFamily universalHashingFamily;

    public PerfectHashing(){
    }

    protected int get_b() {
        System.out.println();
        return (int) (Math.floor(Math.log(tableLength) / Math.log(2)));
    }

    protected int get_u(int[] values) {
        int maxBits = 0;
        for (int value : values) {
            int numBits = Integer.SIZE - Integer.numberOfLeadingZeros(value);
            maxBits = Math.max(numBits, maxBits);
        }
        System.out.println("Max bits = " + maxBits);
        return maxBits;
    }




}

package UniversalPerfectHashing;

public class PerfectHashing {
    protected int tableLength;
    public int[][] hashing_matrix;
    protected UniversalHashingFamily universalHashingFamily;

    public PerfectHashing() {
    }

    protected int get_b(int tableLength) {
        return (int)Math.floor(Math.log((double)tableLength) / Math.log(2.0));
    }
}

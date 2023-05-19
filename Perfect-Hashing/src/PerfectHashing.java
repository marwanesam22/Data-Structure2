import java.util.ArrayList;

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
}
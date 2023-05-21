package LinearHashing;
import UniversalPerfectHashing.UniversalHashingFamily;

import java.util.ArrayList;

public class Order_N_Node {
    Integer []elements;
    int [][]hash_function;
    Integer size = 0;
    Integer numOfCollisions;
    UniversalHashingFamily universalObject;

    public Order_N_Node() { this.numOfCollisions = 0; }

    public int hashing(ArrayList<Integer> arr) {
            numOfCollisions = 0;
            int inserted = 0;
            int n = arr.size() * arr.size();
            boolean collision = true;
            universalObject = new UniversalHashingFamily(32,get_b(n));
            while(collision) {
                inserted = 0;
                collision = false;
                elements = new Integer[n];
                size = n;
                hash_function = universalObject.getrandomizedH();
                for(Integer val : arr) {
                    int index = universalObject.HF(val,hash_function);
                    index %= size;
                    if (elements[index] == null) {
                        inserted++;
                        elements[index] = val;
                    }
                    else if(!elements[index].equals(val)) {
                        numOfCollisions++;
                        collision = true;
                        break;
                    }
                }
            }

            return inserted;
    }

    protected int get_b(int tableLength) {
        return (int) (Math.floor(Math.log(tableLength) / Math.log(2)));
    }
}

import java.util.ArrayList;
import java.util.Arrays;

public class OrderN extends PerfectHashing {

    private OrderN2[] hashTable;
    private ArrayList<Integer>[] collisions;

    public OrderN(int length) {
        tableLength = length;
        this.collisions = new ArrayList[length];
        universalHashingFamily = new UniversalHashingFamily(31, get_b());
        hashing_matrix = universalHashingFamily.getrandomizedH();
        hashTable = new OrderN2[tableLength];
    }

    public Boolean insert(int key) {
        if(search(key))
            return false;

        ArrayList<Integer> arr = new ArrayList<>();
        for(ArrayList<Integer> list : this.collisions) {
            arr.addAll(list);
        }
        hashing(arr);
        return true;
    }

    public Boolean delete(int key) {
        if(!search(key))
            return false;
        int index = universalHashingFamily.HF(key, hashing_matrix);
        hashTable[index].delete(key);
        return true;
    }

    public Boolean search(int key) {
        int index = universalHashingFamily.HF(key, hashing_matrix);
        return hashTable[index].search(key);
    }



    public void hashing(ArrayList<Integer> arr) {
        ArrayList<Integer>[] collisions = firstLevelHashing(arr);
        for(int i=0;i<arr.size();i++) {
            hashTable[i] = new OrderN2(arr.size());
        }
        secondLevelHashing(collisions);
    }

    private ArrayList<Integer>[] firstLevelHashing(ArrayList<Integer> arr) {
        ArrayList<Integer>[] collisions = new ArrayList[arr.size()];
        //        Arrays.fill(collisions, new ArrayList<Integer>());
        for(int i=0;i<arr.size();i++) {
            collisions[i] = new ArrayList<Integer>();
        }
        for(int value : arr) {
            int index = universalHashingFamily.HF(value, hashing_matrix);
            index %= tableLength;
            collisions[index].add(value);
        }

        return collisions;
    }

    private void secondLevelHashing(ArrayList<Integer>[] arr) {
        for(int i=0;i<arr.length;i++) {
            for(int val : arr[i]) {
                hashTable[i].insert(val);
            }
        }
    }

    public void printTable() {
        for(int i=0;i<tableLength;i++) {
            hashTable[i].printHTable();
            System.out.println();
        }
    }

    public void rehashingNum() {
        for(int i=0;i<tableLength;i++) {
            System.out.println("index " + (i + 1) + " " + hashTable[i].getNumberOfCollisions());
        }
    }



}

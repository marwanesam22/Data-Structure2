import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderN extends PerfectHashing {

//    private OrderN2[] hashTable;
    private ArrayList<Integer>[] collisions;
    private Order_N_Node[] hashTable;

//    private ArrayList<Integer> inserted_elements = new ArrayList<>();

    public OrderN(int length) {
//        tableLength = length;
        hashTable = new Order_N_Node[length];
        collisions = new ArrayList[length];
        universalHashingFamily = new UniversalHashingFamily(31, get_b(tableLength));
        hashing_matrix = universalHashingFamily.getrandomizedH();
    }

    public Boolean insert(int key) {
        if(search(key))
            return false;

//        inserted_elements.add(key);

        //insert key in null node
        //insert key in not null node without collision
        //insert key in not null node with collision

        // insert 10
        //  0     1     2     3
        // null, null, null, nu

        int index_in_first_level = universalHashingFamily.HF(key, hashing_matrix);
        Order_N_Node node = hashTable[index_in_first_level];
        collisions[index_in_first_level].add(key);

        if(node.elements == null){
            node.elements = new Integer[1];
            node.size = 1;
            node.universalObject = new UniversalHashingFamily(31,get_b(node.size));
            node.hash_function = node.universalObject.getrandomizedH();
            int index_in_second_level = node.universalObject.HF(key, node.hash_function);
            node.elements[index_in_second_level] = key;
        }else {
            int index_in_second_level = node.universalObject.HF(key, node.hash_function);
            if(node.elements[index_in_second_level] == null){
                node.elements[index_in_second_level] = key;
            } else {

            }

        }

        ArrayList<Integer> arr = new ArrayList<>();
        for(ArrayList<Integer> list : this.collisions) {
            arr.addAll(list);
        }
        hashing(arr);
        return true;
    }

    public void rehash_second_level(ArrayList<Integer> collisions, Order_N_Node node) {
        int n = collisions.size();
        node.elements = new Integer[n * n];
        node.size = n * n;
        node.universalObject = new UniversalHashingFamily(31,get_b(node.size));
        node.hash_function = node.universalObject.getrandomizedH();
        int index_in_second_level = node.universalObject.HF(key, node.hash_function);
        node.elements[index_in_second_level] = key;
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

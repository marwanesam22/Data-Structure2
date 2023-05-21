package LinearHashing;

import UniversalPerfectHashing.IPerfectHashing;
import UniversalPerfectHashing.PerfectHashing;
import UniversalPerfectHashing.UniversalHashingFamily;

import java.util.ArrayList;
import java.util.Objects;

public class OrderN extends PerfectHashing implements IPerfectHashing {

    private ArrayList<Integer>[] collisions;
    private Order_N_Node[] hashTable;

    public OrderN(int length) {
        hashTable = new Order_N_Node[length];
        collisions = new ArrayList[length];
        for(int i=0;i<length;i++) {
            collisions[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<length;i++) {
            hashTable[i] = new Order_N_Node();
            hashTable[i].universalObject = new UniversalHashingFamily(32,get_b(1));
            hashTable[i].hash_function = hashTable[i].universalObject.getrandomizedH();
            hashTable[i].elements = new Integer[1];
        }
        universalHashingFamily = new UniversalHashingFamily(32, get_b(length));
        hashing_matrix = universalHashingFamily.getrandomizedH();
    }

    public boolean insert(int key) {
        if(search(key))
            return false;

        int index_in_first_level = universalHashingFamily.HF(key, hashing_matrix);
        Order_N_Node node = hashTable[index_in_first_level];
        collisions[index_in_first_level].add(key);

        int index_in_second_level = node.universalObject.HF(key, node.hash_function);
        if(node.elements[index_in_second_level] == null){
            node.elements[index_in_second_level] = key;
        } else {
            node.hashing(collisions[index_in_first_level]);
        }
        return true;
    }

    public int[] batchInsert(int[] arr) {
       firstLevelHashing(arr);
        int inserted = 0;
        for(int i=0;i<hashTable.length;i++) {
            Order_N_Node node = hashTable[i];
            if (collisions[i].size() > 0) {
                inserted += node.hashing(collisions[i]);
            }
        }
        return new int[]{inserted,arr.length - inserted};
    }

    public int[] batchDelete(int[] arr) {
        int deleted = 0;
        for(int val : arr) {
            if(delete(val)) deleted++;
        }
        return new int[]{deleted, arr.length - deleted};
    }

    public boolean delete(int key) {
        if(!search(key))
            return false;
        int index = universalHashingFamily.HF(key, hashing_matrix);
        Order_N_Node node = hashTable[index];
        int index_in_second_level = node.universalObject.HF(key, node.hash_function);
        node.elements[index_in_second_level] = null;
        return true;
    }

    public boolean search(int key) {
        int index = universalHashingFamily.HF(key, hashing_matrix);
        Order_N_Node node = hashTable[index];
        int index_in_second_level = node.universalObject.HF(key, node.hash_function);
        return Objects.equals(node.elements[index_in_second_level], (Integer) key);
    }

    public int printHTable() {
        int count = 0;
        for (Order_N_Node node : hashTable) {
            for(Integer val : node.elements) {
                if (val != null) count++;
            }
        }
        int ma = 0, sz = 0;
        for (Order_N_Node node : hashTable) {
            ma += node.numOfCollisions;
            sz += node.size;
        }
        int x = 0;
        for(ArrayList<Integer> a: collisions) {
            if(a.size() > 0) x++;
        }
        System.out.println("collisions = " +  ma / x);
        System.out.println("tableSize = " + ( sz + hashTable.length));
        return count;
    }

    private void firstLevelHashing(int[] arr) {
        for(int value : arr) {
            int index = universalHashingFamily.HF(value, hashing_matrix);
            index %= hashTable.length;
            collisions[index].add(value);
        }
    }

}

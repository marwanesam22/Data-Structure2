package LinearHashing;

import UniversalPerfectHashing.IPerfectHashing;
import UniversalPerfectHashing.PerfectHashing;
import UniversalPerfectHashing.UniversalHashingFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class OrderN extends PerfectHashing implements IPerfectHashing {

    private ArrayList<String>[] collisions;
    private Order_N_Node[] hashTable;
    int inserted = 0;

    public OrderN(int length) {
        length =  Integer.highestOneBit((length - 1) << 1);
        hashTable = new Order_N_Node[length];
        collisions = new ArrayList[length];
        for(int i=0;i<length;i++) {
            collisions[i] = new ArrayList<String>();
        }
        for(int i=0;i<length;i++) {
            hashTable[i] = new Order_N_Node();
            hashTable[i].universalObject = new UniversalHashingFamily(32,2);
            hashTable[i].hash_function = hashTable[i].universalObject.getrandomizedH();
            hashTable[i].elements = new String[2];
        }
        universalHashingFamily = new UniversalHashingFamily(32, length);
        hashing_matrix = universalHashingFamily.getrandomizedH();
        inserted = 0;
    }

    public boolean insert(String key) {
        if(search(key))
            return false;

        int index_in_first_level = universalHashingFamily.computeHash(key, hashing_matrix);
        Order_N_Node node = hashTable[index_in_first_level];
        collisions[index_in_first_level].add(key);

        int index_in_second_level = node.universalObject.computeHash(key, node.hash_function);
        if(node.elements[index_in_second_level] == null){
            node.elements[index_in_second_level] = key;
            inserted++;
        } else {
            node.hashing(collisions[index_in_first_level]);
        }
        return true;
    }

    public int[] batchInsert(String[] arr) {
        firstLevelHashing(arr);
        int before_insert = inserted;
        inserted = 0;
        for(int i=0;i<hashTable.length;i++) {
            Order_N_Node node = hashTable[i];
            if (collisions[i].size() > 0) {
                inserted += node.hashing(collisions[i]);

            }
        }
        int diff = inserted -  before_insert;
        return new int[]{diff, arr.length - diff};
    }

    public int[] batchDelete(String[] arr) {
        int deleted = 0;
        for(String val : arr) {
            if(delete(val)) deleted++;
        }
        return new int[]{deleted, arr.length - deleted};
    }

    public boolean delete(String key) {
        if(!search(key))
            return false;
        int index = universalHashingFamily.computeHash(key, hashing_matrix);
        Order_N_Node node = hashTable[index];
        int index_in_second_level = node.universalObject.computeHash(key, node.hash_function);
        node.elements[index_in_second_level] = null;
        inserted--;
        return true;
    }

    public boolean search(String key) {
        int index = universalHashingFamily.computeHash(key, hashing_matrix);
        Order_N_Node node = hashTable[index];
        int index_in_second_level = node.universalObject.computeHash(key, node.hash_function);
        return (node.elements[index_in_second_level] != null && node.elements[index_in_second_level].equals(key));
    }

    public int printHTable() {
        int count = 0;
        for (Order_N_Node node : hashTable) {
            for(String val : node.elements) {
                if (val != null) count++;
            }
        }
        int ma = 0, sz = 0;
        for (Order_N_Node node : hashTable) {
            ma += node.numOfCollisions;
            sz += node.size;
        }
        int x = 0;
        for(ArrayList<String> a: collisions) {
            if(a.size() > 0) x++;
        }
        try{
            System.out.println("collisions = " +  ma);
        }catch(Exception e){
            System.out.println("Check if the dictionary is empty...");
        }

        System.out.println("tables_Sizes = " + ( sz + hashTable.length));
        return count;
    }

    @Override
    public int getInserted() {
        return this.inserted;
    }

    private void firstLevelHashing(String[] arr) {
        for(String value : arr) {
            int index = universalHashingFamily.computeHash(value, hashing_matrix);
            index %= hashTable.length;
            collisions[index].add(value);
        }
    }

}

import java.util.ArrayList;
import java.util.Arrays;

public class OrderN2 extends PerfectHashing {
    public Integer[] hashTable;

    public int numberOfCollision;

    public OrderN2(int length) {
        tableLength = length * length;
        universalHashingFamily = new UniversalHashingFamily(32, get_b(tableLength));
        hashing_matrix = universalHashingFamily.getrandomizedH();
        hashTable = new Integer[tableLength];
        numberOfCollision = 0;
    }

    public boolean insert(int key){
        int index = universalHashingFamily.HF(key, hashing_matrix);
        if(hashTable[index] == null){
            hashTable[index] = key;
        }else if(hashTable[index].equals(key)){
            return false;
        }else{
            //so there's a collision here we rehash again
            numberOfCollision++;
            numberOfCollision += reHash(key, this.hashTable, tableLength);
        }
        return true;
    }

    public int[] batchInsert(Integer[] keys){
        int[] fails_and_success = new int[2];
        Arrays.fill(fails_and_success,0);
        for(int key : keys){
            if(insert(key))fails_and_success[0]++;
            else fails_and_success[1]++;
        }
        return fails_and_success;
    }

    public boolean delete(int key){
        if(!search(key))return false;
        int index = universalHashingFamily.HF(key, hashing_matrix);
        hashTable[index] = null;
        return true;
    }

    public int[] batchDelete(Integer[] keys){
        int[] failsnSuccesses = new int[2];
        Arrays.fill(failsnSuccesses,0);
        for(int key : keys){
            if(delete(key))failsnSuccesses[0]++;
            else failsnSuccesses[1]++;
        }
        return failsnSuccesses;
    }

    public boolean search(int key){
        int index = universalHashingFamily.HF(key, hashing_matrix);
        return hashTable[index] != null;
    }



//    public void batchInsert(ArrayList<Integer> values) {
////        if ((tableLength & (tableLength - 1)) != 0) {
////            System.out.println("The M is not a power of 2, get the hell out!");
////            return;
////        }
////
////        for (int value : values) {
////            int index = universalHashingFamily.HF(value, hashing_matrix);
////            index %= tableLength;
////            if (hashTable[index] == null || (hashTable[index] == value)) {
////                currentElements.add(key);
////                hashTable[index] = value;
////            } else {
////                hashTable = new Integer[tableLength];
////                hashing_matrix = universalHashingFamily.getrandomizedH();
////                numberOfCollision++;
////                hashValues(values);
////            }
////        }
//    }


    public int getNumberOfCollisions() {
        return numberOfCollision;
    }

    public int printHTable() {
        int count = 0;
        for (Integer val : hashTable) {
            if (val != null) count++;
            System.out.print(val + " ");
        }
        System.out.println();
        return count;
    }

}

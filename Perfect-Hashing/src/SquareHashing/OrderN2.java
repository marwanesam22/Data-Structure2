package SquareHashing;

import UniversalPerfectHashing.IPerfectHashing;
import UniversalPerfectHashing.PerfectHashing;
import UniversalPerfectHashing.UniversalHashingFamily;

import java.util.Arrays;

public class OrderN2 extends PerfectHashing implements IPerfectHashing {

    public String[] hashTable;
    public int numberOfCollision;

    public OrderN2(int length) {
        tableLength = Integer.highestOneBit(length * length - 1) << 1;//to get the nearest power of two larger than or equal to len * len
        universalHashingFamily = new UniversalHashingFamily(32, get_b(tableLength));
        hashing_matrix = universalHashingFamily.getrandomizedH();
        hashTable = new String[tableLength];
        numberOfCollision = 0;
    }

    public boolean insert(String key){
        int index = universalHashingFamily.HF(key.hashCode(), hashing_matrix);
        if(hashTable[index] == null){
            hashTable[index] = key;
        }else if(hashTable[index].equals(key)){
            return false;
        } else{
            //so there's a collision here we rehash again
            numberOfCollision++;
            reHash(key);
        }
        return true;
    }

    public int[] batchInsert(String[] keys){
        int[] fails_and_success = new int[2];
        Arrays.fill(fails_and_success,0);
        for(String key : keys){
            if(insert(key))fails_and_success[0]++;
            else fails_and_success[1]++;
        }
        return fails_and_success;
    }

    public boolean delete(String key){
        if(!search(key))return false;
        int index = universalHashingFamily.HF(key.hashCode(), hashing_matrix);
        hashTable[index] = null;
        return true;
    }

    public int[] batchDelete(String[] keys){
        int[] fails_and_success = new int[2];
        Arrays.fill(fails_and_success,0);
        for(String key : keys){
            if(delete(key))fails_and_success[0]++;
            else fails_and_success[1]++;
        }
        return fails_and_success;
    }

    public boolean search(String key){
        int index = universalHashingFamily.HF(key.hashCode(), hashing_matrix);
        return (hashTable[index] != null && hashTable[index].equals(key));
    }


    public int getNumberOfCollisions() {
        return numberOfCollision;
    }

    public int printHTable() {
        int count = 0;
        for (String val : hashTable) {
            if (val != null) count++;
//            System.out.print(val + " ");
        }
        System.out.println();
        System.out.println("The number of non-null elements is : " + count);
        System.out.println("The current number of collisions is: " + numberOfCollision);
        return count;
    }

    private void reHash(String keyToBeInserted){
        boolean collision = true;
        String[] previousTable = hashTable;
        // To find the first free spot in the table to add keyToBeInserted
        for(int i=0; i<tableLength; i++){
            if(previousTable[i] == null){
                previousTable[i] = keyToBeInserted;
                break;
            }
        }

        while(collision){
            collision = false;
            hashTable = new String[tableLength];
            hashing_matrix = universalHashingFamily.getrandomizedH();
            for(String element: previousTable){
                if(element != null){
                    int index = universalHashingFamily.HF(element.hashCode(), hashing_matrix);
                    if(hashTable[index] == null || hashTable[index].equals(element)){
                        hashTable[index] = element;
                    }else{
                        numberOfCollision++;
                        collision = true;
                        break;
                    }
                }
            }
        }
    }

}

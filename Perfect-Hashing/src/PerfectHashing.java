import java.util.ArrayList;

public class PerfectHashing {

    protected  int tableLength;
    public  int[][] hashing_matrix;
    UniversalHashingFamily universalHashingFamily;

    public PerfectHashing(){
    }

    protected int get_b(int tableLength) {
//        System.out.println();
        return (int) (Math.floor(Math.log(tableLength) / Math.log(2)));
    }

    protected int reHash(int keyToBeInserted, Integer[] hashTable, int tableLength){
        int numberOfCollision = 0;
        boolean collision = true;
        Integer[] previousTable = hashTable;
        // To find the first free spot in the table to add keyToBeInserted
        for(int i=0; i<tableLength; i++){
            if(previousTable[i] == null){
                previousTable[i] = keyToBeInserted;
                break;
            }
        }

        while(collision){
            collision = false;
            hashTable = new Integer[tableLength];
            hashing_matrix = universalHashingFamily.getrandomizedH();
            for(Integer element: previousTable){
                if(element != null){
                    int index = universalHashingFamily.HF(element, hashing_matrix);
                    if(hashTable[index] == element || hashTable[index] == null){
                        hashTable[index] = element;
                    }else{
                        numberOfCollision++;
                        collision = true;
                        break;
                    }
                }
            }
        }
        return numberOfCollision;
    }
}
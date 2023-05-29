package Main;

import LinearHashing.OrderN;
import SquareHashing.OrderN2;
import UniversalPerfectHashing.IPerfectHashing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Dictionary {

    public IPerfectHashing perfectHashing;

    private final int type;
    private int size;
    public int inserted;

    public int getType() {
        return type;
    }

    public int getSize() {
        return size;
    }


    public Dictionary(int dictionaryType, int size){
        this.type = dictionaryType;
        this.size = size;
        if(dictionaryType == 1){
            perfectHashing = new OrderN2(size);
        }else{
            perfectHashing = new OrderN(size);
        }
        inserted = 0;
    }

    public boolean insert(String word){
        boolean success = perfectHashing.insert(word);
        if(success){
            System.out.println(  "=> "+ "\"" + word + "\"" + " has been inserted successfully in the dictionary");
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " already exists in the dictionary");
        }
        perfectHashing.printHTable();
        inserted++;
        return success;
    }

    public boolean delete(String word){
        boolean exists = perfectHashing.delete(word);
        if(exists){
            System.out.println("=> "+ "\"" + word + "\"" + " has been deleted successfully from the dictionary");
            inserted--;
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " doesn't exist in the dictionary");
        }
        perfectHashing.printHTable();

        return exists;
    }

    public boolean search(String word){
        if(perfectHashing.search(word)){
            System.out.println("=> "+ "\"" + word + "\"" + " is in the dictionary");
            return true;
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " does not exist in the dictionary");
            return false;
        }
    }

    public void batchInsert(String filePath){
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }

        String[] keyArray = new String[words.size()];
        for(int i=0;i<keyArray.length;i++) keyArray[i] = words.get(i);

        int[] nSuccessesAndFails = perfectHashing.batchInsert(keyArray);
        System.out.println("=> "+ "The number of newly added words = " + nSuccessesAndFails[0] + " words");
        System.out.println("=> "+ "The number of already existing words = " + nSuccessesAndFails[1] + " words");
    }

    public void batchDelete(String filePath){
        ArrayList<String> keys = new ArrayList<>();
        int nSuccesses = 0, nFails = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                keys.add(word);
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
        String[] keyArray = new String[keys.size()];
        for(int i=0;i<keyArray.length;i++) keyArray[i] = keys.get(i);
        int[] nSuccessAndFails = perfectHashing.batchDelete(keyArray);
        System.out.println("=> "+ "The number of successfully deleted words = " + nSuccessAndFails[0] + " words");
        System.out.println("=> "+ "The number of non existing words = " + nSuccessAndFails[1] + " words");
    }

    public void getData(){
        if(this.type == 2){
            perfectHashing.printHTable();
        }else{
            System.out.println("Number of collisions = "+((OrderN2)perfectHashing).getNumberOfCollisions());
            System.out.println("Size = " + this.size*this.size);
        }
    }

    public int getInserted(){
        return this.perfectHashing.getInserted();
    }

}

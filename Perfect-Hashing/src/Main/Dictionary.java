package Main;

import LinearHashing.OrderN;
import SquareHashing.OrderN2;
import UniversalPerfectHashing.IPerfectHashing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Dictionary {

    IPerfectHashing perfectHashing;
    public Dictionary(int dictionaryType, int size){
        if(dictionaryType == 1){
            perfectHashing = new OrderN2(size);
        }else{
            perfectHashing = new OrderN(size);
        }
    }

    public void insert(String word){
        if(perfectHashing.insert(word.hashCode())){
            System.out.println(  "=> "+ "\"" + word + "\"" + " has been inserted successfully in the dictionary");
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " already exists in the dictionary");
        }
    }

    public void delete(String word){
        if(perfectHashing.delete(word.hashCode())){
            System.out.println("=> "+ "\"" + word + "\"" + " has been deleted successfully from the dictionary");
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " doesn't exist in the dictionary");
        }
    }

    public void search(String word){
        if(perfectHashing.search(word.hashCode())){
            System.out.println("=> "+ "\"" + word + "\"" + " is in the dictionary");
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " does not exist in the dictionary");
        }
    }

    public void batchInsert(String filePath){
        ArrayList<Integer> keys = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                keys.add(word.hashCode());
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }


//        for(int i=0;i<keyArray.length;i++) keyArray[i] = keys.get(i);
//
//
//        int[] nSuccessesAndFails = perfectHashing.batchInsert(keyArray);
//        System.out.println("=> "+ "The number of newly added words = " + nSuccessesAndFails[0] + " words");
//        System.out.println("=> "+ "The number of already existing words = " + nSuccessesAndFails[1] + " words");
    }

    public void batchDelete(String filePath){
        ArrayList<Integer> keys = new ArrayList<>();
        int nSuccesses = 0, nFails = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                keys.add(word.hashCode());
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
        int[] keyArray = new int[keys.size()];
        for(int i=0;i<keyArray.length;i++) keyArray[i] = keys.get(i);
        int[] nSuccessAndFails = perfectHashing.batchDelete(keyArray);
        System.out.println("=> "+ "The number of successfully deleted words = " + nSuccesses + " words");
        System.out.println("=> "+ "The number of non existing words = " + nFails + " words");
    }

}

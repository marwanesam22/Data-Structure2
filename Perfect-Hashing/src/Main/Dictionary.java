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
        if(perfectHashing.insert(word)){
            System.out.println(  "=> "+ "\"" + word + "\"" + " has been inserted successfully in the dictionary");
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " already exists in the dictionary");
        }
        perfectHashing.printHTable();
    }

    public void delete(String word){
        if(perfectHashing.delete(word)){
            System.out.println("=> "+ "\"" + word + "\"" + " has been deleted successfully from the dictionary");
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " doesn't exist in the dictionary");
        }
        perfectHashing.printHTable();
    }

    public void search(String word){
        if(perfectHashing.search(word)){
            System.out.println("=> "+ "\"" + word + "\"" + " is in the dictionary");
        }else{
            System.out.println("=> "+ "\"" + word + "\"" + " does not exist in the dictionary");
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

}

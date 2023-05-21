import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {

    IPerfectHashing perfectHashing;
    public Dictionary(int dictionaryType, int size){
        if(dictionaryType == 1){
            //O(n^2) based dictionary
            perfectHashing = new OrderN2(size);
        }else{
            //TODO make an instance of O(N) hash
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
        int nSuccesses = 0, nFails = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                if (perfectHashing.insert(word.hashCode())) {
                    nSuccesses++;
                } else {
                    nFails++;
                }
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
        System.out.println("=> "+ "The number of newly added words = " + nSuccesses + " words");
        System.out.println("=> "+ "The number of already existing words = " + nFails + " words");
    }

    public void batchDelete(String filePath){
        int nSuccesses = 0, nFails = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                if (perfectHashing.delete(word.hashCode())) {
                    nSuccesses++;
                } else {
                    nFails++;
                }
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
        System.out.println("=> "+ "The number of successfully deleted words = " + nSuccesses + " words");
        System.out.println("=> "+ "The number of non existing words = " + nFails + " words");
    }

}

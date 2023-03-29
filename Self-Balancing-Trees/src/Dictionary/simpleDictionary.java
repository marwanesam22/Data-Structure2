package Dictionary;
import AVLTree.*;
import Main.*;
import RBTree.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class simpleDictionary {
    IBalancedTree<String> dictionary;
    public simpleDictionary(int dictionaryType){
        if(dictionaryType == 1)dictionary = new AVL<String>();
        else dictionary = new RBTree<String>();//initialize dictionary as a RB tree
    }

    public boolean insert(String newWord){
        return dictionary.insert(newWord);
    }

    public boolean delete(String word){
        return dictionary.delete(word);
    }

    public boolean search(String word){
        return dictionary.search(word);
    }

    public int[] batchInsert(String filePath){
        int nSuccesses = 0, nFails = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String word;
                while ((word = reader.readLine()) != null) {
                    if (this.insert(word)) {
                        nSuccesses++;
                    } else {
                        nFails++;
                    }
                }
            } catch (IOException e) {
                System.out.println("Please enter a valid file path");
            }
        System.out.println("The number of newly added words = " + nSuccesses + " words");
        System.out.println("The number of already existing words = " + nFails + " words");
        return new int[]{nSuccesses,nFails};
    }

    public int[] batchDelete(String filePath){
        int nSuccesses = 0, nFails = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                if (this.delete(word)) {
                    nSuccesses++;
                } else {
                    nFails++;
                }
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
        System.out.println("The number of successfully deleted words = " + nSuccesses + " words");
        System.out.println("The number of non existing words = " + nFails + " words");
        return new int[]{nSuccesses,nFails};
    }

    public int size(){
        return dictionary.size();
    }

    public int height(){
        return dictionary.height();
    }
}

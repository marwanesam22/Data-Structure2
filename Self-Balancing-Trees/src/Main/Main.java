package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Dictionary.*;

public class Main {

    public static void simpleDictionary(){
        Scanner scan = new Scanner(System.in);
        int treeOption;
        simpleDictionary dictionary;

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Select the type of the tree you want the dictionary to be based on: ");
        System.out.println("1-for a AVL-tree based dictionary");
        System.out.println("2-for a Red-Black-tree based dictionary");
        treeOption = scan.nextInt();

        switch (treeOption) {
            case 1 -> dictionary = new simpleDictionary(1);
            case 2 -> dictionary = new simpleDictionary(2);
            default -> {
                System.out.println("Invalid option");
                return;
            }
        }

        while(true){
            int optionSelected;
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("\t\t\t\tWelcome to Q-Dictionary");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Select the desired operation from the following list by typing out the number:");
            System.out.println("1-Insert a new word");
            System.out.println("2-Delete a word");
            System.out.println("3-Search for a word");
            System.out.println("4-Batch insert");
            System.out.println("5-Batch delete");
            System.out.println("6-Size");
            System.out.println("7-Height");
            optionSelected = scan.nextInt();

            scan.nextLine(); //for cleaning the buffer

            if(optionSelected == 1){
                //insert new word option
                String newWord;
                System.out.print("Enter the word to be inserted: ");
                newWord = scan.next();
                System.out.println();
                if(dictionary.insert(newWord))
                    System.out.println("\"" + newWord + "\"" + " has been inserted successfully to the dictionary");
                else
                    System.out.println("\"" + newWord + "\"" + " already exists");
            }

            else if(optionSelected == 2){
                //delete a word option
                String word;
                System.out.print("Enter the word to be deleted: ");
                word = scan.next();
                System.out.println();
                if(dictionary.delete(word))
                    System.out.println("\"" + word + "\"" + " has been deleted successfully from the dictionary");
                else
                    System.out.println("\"" + word + "\"" + " doesn't exist in the dictionary");
            }

            else if(optionSelected == 3){
                //search for a word option
                String word;
                System.out.print("Enter the word to be searched for: ");
                word = scan.next();
                System.out.println();
                if(dictionary.search(word))
                    System.out.println("\"" + word + "\"" + " does exist");
                else
                    System.out.println("\"" + word + "\"" + " doesn't exist in the dictionary");
            }

            else if(optionSelected == 4){
                //batch insert option
                String filePath = "";
                while(true){
                    System.out.print("please enter the file path: ");
                    filePath = scan.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }

                long startTime = System.currentTimeMillis();
                dictionary.batchInsert(filePath);
                long endTime = System.currentTimeMillis();
                double time_taken = (endTime-startTime);
                System.out.println("Time taken is: " + time_taken + " ms");
            }

            else if(optionSelected == 5){
                //batch delete option
                String filePath = "";
                while(true){
                    System.out.print("please enter the file path: ");
                    filePath = scan.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }

                long startTime = System.currentTimeMillis();
                dictionary.batchDelete(filePath);
                long endTime = System.currentTimeMillis();
                double time_taken = (endTime-startTime);
                System.out.println("Time taken is: " + time_taken + " ms");
            }

            else if(optionSelected == 6){
                //size option
                System.out.println("The size of the dictionary = " + dictionary.size() + " words");
            }

            else if(optionSelected == 7){
                //height option
                System.out.println("The height of the tree = " + dictionary.height());
            }

            else{
                //invalid option
                System.out.println("Invalid option");
            }

        }

    }

    public static void main(String[] args) {
        simpleDictionary();
    }
}

/*
C:\Users\Adel\Desktop\Lab1\Data-Structure2\Self-Balancing-Trees
 */




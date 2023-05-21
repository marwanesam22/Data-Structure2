package Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import UniversalPerfectHashing.IPerfectHashing;

import java.util.Scanner;

public class CLI {

    public void start() {
        Scanner scan = new Scanner(System.in);
        int perfectHashing_Option;
        Dictionary dictionary;

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Select the type of the perfect hashing you want the dictionary to be based on: ");
        System.out.println("1-for an O(N^2)-space based dictionary");
        System.out.println("2-for an O(N)-space based dictionary");
        System.out.print("Option: ");
        perfectHashing_Option = scan.nextInt();

        if (perfectHashing_Option != 1 && perfectHashing_Option != 2) return;

        System.out.print("Enter the size of the hash table: ");
        int size = scan.nextInt();
        dictionary = new Dictionary(perfectHashing_Option, size);


        while (true) {
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
            System.out.println("6-Exit");
            System.out.print("Option: ");
            optionSelected = scan.nextInt();

            scan.nextLine(); //for cleaning the buffer

            if (optionSelected == 1) {
                //insert new word option
                String newWord;
                System.out.print("Enter the word to be inserted: ");
                newWord = scan.next();
                System.out.println();
                dictionary.insert(newWord);
            } else if (optionSelected == 2) {
                //delete a word option
                String word;
                System.out.print("Enter the word to be deleted: ");
                word = scan.next();
                System.out.println();
                dictionary.delete(word);
            } else if (optionSelected == 3) {
                //search for a word option
                String word;
                System.out.print("Enter the word to be searched for: ");
                word = scan.next();
                System.out.println();
                dictionary.search(word);
            } else if (optionSelected == 4) {
                //batch insert option
                String filePath = "";
                while (true) {
                    System.out.print("please enter the file path: ");
                    filePath = scan.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }

//                long startTime = System.nanoTime();
                dictionary.batchInsert(filePath);
//                long endTime = System.nanoTime();
//                double time_taken = (endTime - startTime) / (1000.0);
//                System.out.println("Time taken is: " + time_taken + " us");
            } else if (optionSelected == 5) {
                //batch delete option
                String filePath = "";
                while (true) {
                    System.out.print("please enter the file path: ");
                    filePath = scan.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }

//                long startTime = System.nanoTime();
                dictionary.batchDelete(filePath);
//                long endTime = System.nanoTime();
//                double time_taken = (endTime - startTime) / (1000.0);
//                System.out.println("Time taken is: " + time_taken + " us");
            } else if (optionSelected == 6) {
                break;
            } else {
                //invalid option
                System.out.println(dictionary.perfectHashing.printHTable());
                System.out.println("Invalid option");
            }

        }
    }
}

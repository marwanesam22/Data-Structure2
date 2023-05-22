package Sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SortArray {

    public int[] arr;

    public SortArray(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word = reader.readLine();
            String[] temp = word.split(",");
            this.arr = new int[temp.length];
            for(int i=0;i<arr.length;i++) {
                this.arr[i] = Integer.parseInt(temp[i]);
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
    }




}

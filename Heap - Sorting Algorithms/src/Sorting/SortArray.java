package Sorting;

import BinaryHeap.Heap;

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



    public int[] getArray(){
        return this.arr;
    }

    public void simpleSort(int printIntermediate){
        new SimpleSort().bubbleSort(arr,printIntermediate);
    }

    public void efficientSort(int printIntermediate){
        new EfficientSort().mergeSort(arr,printIntermediate);
    }

    public void nonComparisonSort(int printIntermediate){
        arr = new NonComparisonSort().counting_Sort(arr,printIntermediate);
    }

    public void heapSort(int printIntermediate){
        new Heap().heapSort(arr,printIntermediate);
    }

}
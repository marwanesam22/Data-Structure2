package Sorting;

import java.util.Arrays;

public class SimpleSort {

//    public void insertionSort(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int value = arr[i];
//            int hole = i;
//            while (hole > 0 && arr[hole - 1] > value) {
//                arr[hole] = arr[hole - 1];
//                hole--;
//            }
//            arr[hole] = value;
//        }
//    }


    public  void bubbleSort(int[] arr, int printIntermediate) {
//        System.out.println("Input size is  " + arr.length);
//        long start = System.nanoTime();
        boolean already_ordered = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // move the larger element to the right
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    already_ordered = true;
                }
            }
            // If no swapping occurred in the inner loop, the array is already sorted, so we'll just break the loop
            if (!already_ordered) {
                break;
            }
            if(printIntermediate == 1) System.out.println(Arrays.toString(arr));
        }
//        long duration = System.nanoTime() - start;
//        System.out.println("Time taken is : " + duration + " us");
    }

}

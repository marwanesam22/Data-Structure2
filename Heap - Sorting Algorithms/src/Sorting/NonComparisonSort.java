package Sorting;

import java.util.Arrays;

public class NonComparisonSort {

    public int[] counting_Sort(int[] arr) {
        System.out.println("Input size is  " + arr.length);
        long start = System.nanoTime();
        int[] result = new int[arr.length];
        int[] freqArray = new int[Arrays.stream(arr).max().orElse(0)+1];
        Arrays.fill(freqArray, 0);

        for (int j : arr) {
//            int tmp = arr[i] / (int) Math.pow(radix, ith_iteration - 1) % radix;
            freqArray[j]++;
        }
        //getting the prefix sum
        for (int i = 1; i < freqArray.length; i++) {
            freqArray[i] += freqArray[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i];
            int currIndex = freqArray[tmp] - 1;
            result[currIndex] = tmp;
            freqArray[tmp]--;
        }
        long duration = System.nanoTime() - start;
        System.out.println("Time taken is : " + duration / 1000.0 + " us");
        return result;
    }

//    public void countingSort(int[] arr, int radix, int ith_iteration) {
//
//        int[] freqArray = new int[radix];
//        int[] result = new int[arr.length];
//        Arrays.fill(freqArray, 0);
//        for (int j : arr) {
//            int tmp = j / (int) Math.pow(radix, ith_iteration - 1) % radix;
//            freqArray[tmp]++;
//        }
//
//        for (int i = 1; i < freqArray.length; i++) {
//            freqArray[i] += freqArray[i - 1];
//        }
//
//        for (int i = arr.length - 1; i >= 0; i--) {
//            int tmp = arr[i] / (int) Math.pow(radix, ith_iteration - 1) % radix;
//            int currIndex = freqArray[tmp] - 1;
//            result[currIndex] = arr[i];
//            freqArray[tmp]--;
//        }
//
//        System.arraycopy(result, 0, arr, 0, arr.length);
//    }
//
//    public int[] radixSort(int[] arr, int radix) {
//
//        int MaxElement = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            if (arr[i] > MaxElement) MaxElement = arr[i];
//        }
//
//        int totalIterations = ((int) (Math.log(MaxElement) / Math.log(radix))) + 1;
//        System.out.println("total : " + totalIterations);
//        for (int i = 1; i <= totalIterations; i++) {
//            countingSort(arr, radix, i);
//        }
//
//        return arr;
//    }
}

package Sorting;

import java.util.Arrays;
import java.util.Random;

public class EfficientSort {

    public void mergeSort(int[] arr, int printIntermediate){
//        System.out.println("Input size is  " + arr.length);
//        long start = System.nanoTime();
        merge_Sort(arr,printIntermediate);
//        System.out.println(Arrays.toString(arr));
//        long duration = System.nanoTime() - start;
//        System.out.println("Time taken is : " + duration + " us");
     }
    public void merge_Sort(int[] arr, int printIntermediate) {
        if (arr == null || arr.length <= 1) {
            return; // Array is already sorted or empty
        }

        int n = arr.length;
        int mid = n / 2;

        int[] left = new int[mid];
        int[] right = new int[n - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, n - mid);

        merge_Sort(left,printIntermediate);
        merge_Sort(right,printIntermediate);
        merge(arr, left, right);
        if(printIntermediate == 1)System.out.println(Arrays.toString(arr));
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int n1 = left.length;
        int n2 = right.length;
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < n1) {
            arr[k++] = left[i++];
        }

        while (j < n2) {
            arr[k++] = right[j++];
        }
    }

//    public void quickSort(int[] arr, int lower, int upper) {
//        if (lower >= upper) return;
//        int pivotIndex = partition(arr, lower, upper);
//        quickSort(arr, lower, pivotIndex - 1);
//        quickSort(arr, pivotIndex + 1, upper);
//    }

//
//    private int partition(int[] arr, int lower, int upper) {
//        int i = lower;
//        int j = lower;
//        int pivot_index = new Random().nextInt(upper - lower + 1) + lower;
//        int pivot = arr[pivot_index];
//        int tmpEl = arr[upper];
//        arr[upper] = arr[pivot_index];
//        arr[pivot_index] = tmpEl;
//
//        while (i <= upper) {
//            if (arr[i] < pivot) {
//                int tmp = arr[j];
//                arr[j] = arr[i];
//                arr[i] = tmp;
//                i++;
//                j++;
//            } else {
//                i++;
//            }
//        }
//        tmpEl = arr[j];
//        arr[j] = pivot;
//        arr[upper] = tmpEl;
//        return j;
//    }


}

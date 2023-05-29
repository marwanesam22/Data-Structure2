package Main;

import BinaryHeap.Heap;
import Sorting.EfficientSort;
import Sorting.NonComparisonSort;
import Sorting.SimpleSort;
import Sorting.SortArray;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new CLI().start();

        //TODO adding testing for sorting one element in the array
//        int[] arr = {10,5,6,8,4,3,2,20,30};
//        new SortArray("C:\\Users\\Adel\\Downloads\\tst.txt").efficientSort(0);

//        Heap heap = new Heap();
//
//        int[] arr = Generate.generate(100000);
//        int[] arr2 = new int[100000];
//        System.arraycopy(arr, 0, arr2, 0, arr.length);
//        Arrays.sort(arr2);
//        for(int i : arr) {
//            heap.maxHeapInsert(i);
//        }
//        heap.heapSort(arr);
//        arr = new NonComparisonSort().counting_Sort(arr);
//        System.out.println(Arrays.toString(arr));
//        for(int i=0;i<arr.length;i++) {
//            if(arr[i] != arr2[i]) System.out.println("Problem!");
//        }
//        heap.printHeap();
//        System.out.println(Arrays.toString(heap.heapSort(arr)));;
//        heap.printHeap();
//        System.out.println(heap.extractMax());
//        heap.printHeap();

//        String path = "E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\perfect hashing\\Data-Structure2\\Heap - Sorting Algorithms\\src\\Main\\in.txt";
//        SortArray arr = new SortArray(path);
    }

}
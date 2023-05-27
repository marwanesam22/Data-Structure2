package BinaryHeap;

import Sorting.SortArray;
//
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    int[] readFile(String Path){
        int[] arr = new int[5];
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            String word = reader.readLine();
            String[] temp = word.split(",");
            arr = new int[temp.length];
            for(int i=0;i<arr.length;i++) {
                arr[i] = Integer.parseInt(temp[i]);
            }
        } catch (IOException e) {
            System.out.println("Please enter a valid file path");
        }
        return arr;
    }

    interface TimeMeasure{
        void foo();
    }

    interface SortTimeMeasure{
        int[] foo();
    }

    public void measureTime(TimeMeasure tm){
        long startTime = System.nanoTime();
        tm.foo();
        long endTime = System.nanoTime();
        System.out.println("Time elapsed = "+((endTime-startTime)/1000.0)+" us");
    }

    public int[] measureSortTime(SortTimeMeasure stm){
        int[] sortedArr;
        long startTime = System.nanoTime();
        sortedArr = stm.foo();
        long endTime = System.nanoTime();
        System.out.println("Time elapsed = "+((endTime-startTime)/1000.0)+" us");
        return sortedArr;
    }

    static final String PATH = "C:\\Users\\Adel\\Desktop\\6th term\\DS 2\\labs\\Data-Structure2\\Heap - Sorting Algorithms\\src\\Test_Cases";


    @Test
    void test1(){
        //Test correctness under 100 integers with average case with positive valuse
        String filePath = PATH+"\\in100\\in100_average.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in100\\in100_best.txt";
        int []outArray = readFile(filePath);
        Heap heap = new Heap();
        int[] sorted = measureSortTime(()-> heap.heapSort(inputArray,0));
        assertArrayEquals(outArray, sorted);
    }

    @Test
    void test2(){
        //Test correctness under 100 integers with worst case with positive valuse
        String filePath = PATH+"\\in100\\in100_best.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in100\\in100_best.txt";
        int []outArray = readFile(filePath);
        int[] sorted = measureSortTime(()-> new Heap().heapSort(inputArray,0));
        assertArrayEquals(outArray, sorted);
    }

    @Test
    void test3(){
        //average case under a very large input with positive valuse
        String filePath = PATH+"\\in1000000\\in1000000_average.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in1000000\\in1000000_best.txt";
        int []outArray = readFile(filePath);
        int[] sorted = measureSortTime(()-> new Heap().heapSort(inputArray,0));
        assertArrayEquals(outArray, sorted);
    }

    @Test
    void test4(){
        //worst case on a very large input file with positive valuse
        String filePath = PATH+"\\in1000000\\in1000000_best.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in1000000\\in1000000_best.txt";
        int []outArray = readFile(filePath);
        int[] sorted = measureSortTime(()-> new Heap().heapSort(inputArray,0));
        assertArrayEquals(outArray, sorted);
    }

    @Test
    void Test5(){
        //mix of negative and positive numbers small input file(10_000)
        String filePath = PATH+"\\in_neg10000\\in_neg10000_best.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in_neg10000\\in_neg10000_best.txt";
        int []outArray = readFile(filePath);
        int[] sorted = measureSortTime(()-> new Heap().heapSort(inputArray,0));
        assertArrayEquals(outArray, sorted);
    }

    @Test
    void Test6(){
        //mix of negative and positive numbers large input file(100_000)
        String filePath = PATH+"\\in_neg100000\\in_neg100000_best.txt";
        int []inputArray = readFile(filePath);

        filePath = PATH+"\\in_neg100000\\in_neg100000_best.txt";
        int []outArray = readFile(filePath);
        int[] sorted = measureSortTime(()-> new Heap().heapSort(inputArray,0));
        assertArrayEquals(outArray, sorted);
    }

}
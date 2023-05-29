package Time_Tests;

import BinaryHeap.Heap;
import Sorting.EfficientSort;
import Sorting.NonComparisonSort;
import Sorting.SimpleSort;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    //large sizes -> heap, merge, counting
    //small -> heap, merge, counting, bubble
    //fastest: counting sort
    //then faster: *heap, merge [nlog(n)]
    //last fast: bubble;

    String basePath = "C:\\Users\\Adel\\Desktop\\6th term\\DS 2\\labs\\Data-Structure2\\Heap - Sorting Algorithms\\src\\count";



    @Test
    void test100Merge(){
        String path = basePath + "\\in100_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 100 ");
         path = basePath + "\\in100_best.txt";
         arr = readFile(path);

          start = System.nanoTime();
         new NonComparisonSort().counting_Sort(arr,0);;
          duration = (System.nanoTime()- start) / 1000.0 ;
         System.out.println("merge sort best case: " + duration + " us");


        path = basePath + "\\in100_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in100_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }
    
    

    @Test
    void test1000Merge(){
        String path = basePath + "\\in1000_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 1000 ");
        path = basePath + "\\in1000_best.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort best case: " + duration + " us");

        path = basePath + "\\in1000_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in1000_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }


    @Test
    void test10000Merge(){
        String path = basePath + "\\in10000_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 10_000 ");
         path = basePath + "\\in10000_best.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort best case: " + duration + " us");


        path = basePath + "\\in10000_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in10000_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }


    @Test
    void test100000Merge(){
        String path = basePath + "\\in100000_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 100_000 ");
        path = basePath + "\\in100000_best.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort best case: " + duration + " us");


        path = basePath + "\\in100000_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in100000_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }

    @Test
    void test500000Merge(){
        String path = basePath + "\\in500000\\in500000_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 500_000 ");
        path = basePath + "\\in500000\\in500000_best.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort best case: " + duration + " us");


        path = basePath + "\\in500000\\in500000_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in500000\\in500000_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }

    @Test
    void test800000Merge(){
        String path = basePath + "\\in800000\\in800000_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 800_000 ");
        path = basePath + "\\in800000\\in800000_best.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort best case: " + duration + " us");


        path = basePath + "\\in800000\\in800000_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in800000\\in800000_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }


    @Test
    void test1000000Merge(){
        String path = basePath + "\\in1000000\\in1000000_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 1000_000 ");
        path = basePath + "\\in1000000\\in1000000_best.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort best case: " + duration + " us");


        path = basePath + "\\in1000000\\in1000000_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in1000000\\in1000000_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }
    @Test
    void test5000000Merge(){
        String path = basePath + "\\in5000000\\in5000000_best.txt";
        int[] arr = readFile(path);
        double start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        double duration = (System.nanoTime()- start) / 1000.0 ;


        System.out.println("size: 5000_000 ");
        path = basePath + "\\in5000000\\in5000000_best.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort best case: " + duration + " us");


        path = basePath + "\\in5000000\\in5000000_average.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);;
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort average case: " + duration + " us");


        path = basePath + "\\in5000000\\in5000000_worst.txt";
        arr = readFile(path);
        start = System.nanoTime();
        new NonComparisonSort().counting_Sort(arr,0);
        duration = (System.nanoTime()- start) / 1000.0 ;
        System.out.println("merge sort worst case: " + duration + " us");
    }


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



}
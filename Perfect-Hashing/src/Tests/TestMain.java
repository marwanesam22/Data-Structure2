package Tests;

import Main.Dictionary;

import java.io.File;
import java.util.Scanner;

public class TestMain {

    static final String TEST_PATH =
            "D:\\Engineering\\DS2\\Data-Structure2\\Perfect-Hashing\\Random Input";

    static final int[] commonSizes = {100, 1000, 5000, 10_000};
    static final int[] sizes = {100, 1000, 5000, 10_000, 50_000, 100_000, 200_000, 400_000, 500_000, 600_000, 800_000, 1_000_000};
    static final int[] MiniSizes = {1000, 3000, 5000, 7000, 10_000, 11_000, 13_000, 15_000, 17_000};

    /*
     * batch insert time
     * batch delete time
     * search time
     * insert time
     * delete time
     * (N2 vs N): space, no. of times of rehashing, time
     * (hashes vs trees): mean search time
     *
     * in5000.txt
     * list =  [elemenst in 5000]
     * search for each element in list
     * total search time
     * divide by 5000
     * get mean search time
     *
     * */

    interface Test{
        void run(String s);
    }

    public void batchInsertTest(Dictionary dict){
        System.out.println("************* BATCH INSERT ************");
        runBatchTest(dict, dict::batchInsert);
    }

    public void batchDeleteTest(Dictionary dict){
        System.out.println("************* BATCH DELETE ************");
        runBatchTest(dict, dict::batchDelete);
    }

    public void searchTest(Dictionary dict, String val){
        System.out.println("************* SEARCH ************");
        runTest(dict, dict::search, val);
    }

    public void insertTest(Dictionary dict, String val){
        System.out.println("************* INSERT ************");
        runTest(dict, dict::insert, val);
    }

    public void deleteTest(Dictionary dict, String val){
        System.out.println("************* DELETE ************");
        runTest(dict, dict::delete, val);
    }

    public void init(Dictionary dict){
        String path = TEST_PATH+"\\in"+dict.getSize()+".txt";
        dict.batchInsert(path);
    }

    public void runBatchTest(Dictionary dict, Test test){
        String path = TEST_PATH+"\\in"+dict.getSize()+".txt";
        long startTime = System.nanoTime();
        test.run(path);
        long endTime = System.nanoTime();
        System.out.println("time elapsed: " + (endTime-startTime)/1000.0);
        dict.getData();
    }

    public void runTest(Dictionary dict, Test test, String val){
        long startTime = System.nanoTime();
        test.run(val);
        long endTime = System.nanoTime();
        System.out.println("time elapsed: " + (endTime-startTime)/1000.0);
        dict.getData();
    }

    public void generalTimeTest(int type, int size){
        Dictionary dict = new Dictionary(type, size);
        this.batchInsertTest(dict);
        this.insertTest(dict, "jumbo");
        this.searchTest(dict, "jumbo");
        this.deleteTest(dict, "jumbo");
        this.deleteTest(dict, "jumbo");
        this.searchTest(dict, "jumbo");
        this.batchDeleteTest(dict);
    }

    public void allSizeGeneralTimeTest(int type){
        for(int size: sizes){
            System.out.println("************* SIZE = "+size+" *******************");
            generalTimeTest(type, size);
        }
    }

    public void allMiniSizeGeneralTimeTest(int type){
        for(int size: MiniSizes){
            System.out.println("************* SIZE = "+size+" *******************");
            generalTimeTest(type, size);
        }
    }

    public void compareDictionaries(Dictionary dictN2, Dictionary dictN){
        for(int size: commonSizes){
            this.batchInsertTest(dictN2);
            this.batchInsertTest(dictN);
        }
    }

    public void allElementSearchTest(Dictionary dict) throws Exception{
        String path = TEST_PATH+"\\in"+dict.getSize()+".txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        long totalTime = 0;
        while(scanner.hasNextLine()){
            String ele = scanner.nextLine();
            System.out.println(ele);
            long start = System.nanoTime();
            dict.search(ele);
            long end = System.nanoTime();
            totalTime += (end-start);
        }
        System.out.println("Total search time = " + totalTime/1000.0+"us");
        System.out.println("Mean search time = " + (totalTime/1000.0)/dict.getSize()+"us");
    }

    public static void main(String[] args){
        TestMain t = new TestMain();
//        Dictionary dict = new Dictionary(1, 1_000);
//        t.allSizeGeneralTimeTest(2);

//        Dictionary dict = new Dictionary(1, size);
//        t.allSizeGeneralTimeTest(2);

        Dictionary dict = new Dictionary(2, 1_000_000);
        dict.batchInsert(TEST_PATH+"\\in"+dict.getSize()+".txt");
        try {
            t.allElementSearchTest(dict);
        }catch(Exception e){
            e.printStackTrace();
        }
        t.searchTest(dict, "jumbo");
        t.searchTest(dict, "ZKyLSTQRXU");

//        t.allMiniSizeGeneralTimeTest(1);
//            t.allSizeGeneralTimeTest(2);
    }


}

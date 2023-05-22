package Tests;
import static org.junit.jupiter.api.Assertions.*;

import LinearHashing.OrderN;
import Main.Dictionary;
import Main.Main;
import org.junit.jupiter.api.Test;

public class Cases {

    static final String TEST_PATH =
            "D:\\Engineering\\DS2\\Data-Structure2\\Perfect-Hashing\\Random Input";

    @Test
    void searchTestN2A(){
        //searching for a non-existing word
        Dictionary dict = new Dictionary(1,128);
        assertFalse(dict.search("jumbo"));
    }

    @Test
    void searchTestN2B(){
        //searching for an existing word
        Dictionary dict = new Dictionary(1,128);
        dict.insert("jumbo");
        assertTrue(dict.search("jumbo"));
    }

    @Test
    void searchTestN2C(){
        //searching for a word after deletion
        Dictionary dict = new Dictionary(1,128);
        dict.insert("jumbo");
        dict.delete("jumbo");
        assertFalse(dict.search("jumbo"));
    }

    @Test
    void searchTestNA(){
        //searching for a non-existing word
        Dictionary dict = new Dictionary(2,128);
        assertFalse(dict.search("jumbo"));
    }

    @Test
    void searchTestNB(){
        //searching for an existing word
        Dictionary dict = new Dictionary(2,128);
        dict.insert("jumbo");
        assertTrue(dict.search("jumbo"));
    }

    @Test
    void searchTestNC(){
        //searching for a word after deletion
        Dictionary dict = new Dictionary(2,128);
        dict.insert("jumbo");
        dict.delete("jumbo");
        assertFalse(dict.search("jumbo"));
    }

    @Test
    void insertionTestN2A(){
        //inserting an existing word
        Dictionary dict = new Dictionary(1, 256);
        dict.insert("Jumbo");
        assertFalse(dict.insert("Jumbo"));
    }

    @Test
    void insertionTestNA(){
        //inserting an existing word
        Dictionary dict = new Dictionary(2, 256);
        dict.insert("Jumbo");
        assertFalse(dict.insert("Jumbo"));
    }

    @Test
    void insertionTestN2B(){
        //inserting duplicate items more than once
        Dictionary dict = new Dictionary(1, 256);
        assertTrue(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
    }

    @Test
    void insertionTestNB(){
        //inserting duplicate items more than once
        Dictionary dict = new Dictionary(2, 256);
        assertTrue(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
        assertFalse(dict.insert("pizza"));
    }

    @Test
    void deletionTestN2A(){
        //deleting a non-existing word
        Dictionary dict = new Dictionary(1, 512);
        assertFalse(dict.delete("Jumbo"));
    }

    @Test
    void deletionTestNA(){
        //deleting a non-existing word
        Dictionary dict = new Dictionary(2, 512);
        assertFalse(dict.delete("Jumbo"));
    }

    @Test
    void elementsTestN2(){
        //size
        Dictionary dict = new Dictionary(1, 1000);
        dict.batchInsert(TEST_PATH+"\\in100.txt");
        assertEquals(100, dict.getInserted());
        dict.batchInsert(TEST_PATH+"\\in1000.txt");
        assertEquals(1100, dict.getInserted());
    }

    @Test
    void elementsTestN(){
        //size
        Dictionary dict = new Dictionary(2, 1000);
        dict.batchInsert(TEST_PATH+"\\in100.txt");
        assertEquals(100, dict.getInserted());
        dict.batchInsert(TEST_PATH+"\\in1000.txt");
        assertEquals(1100, dict.getInserted());
    }


    @Test
    void mixTestN2(){
        Dictionary dict = new Dictionary(1, 512);
        String[] values = {"pizza", "hot-dog", "sauce", "ice cream"};
        for(String val: values){
            assertTrue(dict.insert(val));
        }

        assertEquals(4, dict.getInserted());

        assertTrue(dict.search("sauce"));
        assertTrue(dict.search("pizza"));

        assertTrue(dict.delete("hot-dog"));

        assertEquals(3, dict.getInserted());

        assertFalse(dict.search("hot-dog"));
        assertTrue(dict.insert("hot-dog"));
        assertTrue(dict.search("hot-dog"));

        assertFalse(dict.search("Mississippi"));
        assertFalse(dict.delete("Mississippi"));

        for(String val: values){
            assertTrue(dict.delete(val));
        }

        assertEquals(0, dict.getInserted());
    }

    @Test
    void mixTestN(){
        Dictionary dict = new Dictionary(2, 512);
        String[] values = {"pizza", "hot-dog", "sauce", "ice cream"};
        for(String val: values){
            assertTrue(dict.insert(val));
        }



        assertEquals(4, dict.getInserted());


        assertTrue(dict.search("sauce"));
        assertTrue(dict.search("pizza"));

        assertTrue(dict.delete("hot-dog"));

        assertEquals(3, dict.getInserted());

        assertFalse(dict.search("hot-dog"));
        assertTrue(dict.insert("hot-dog"));
        assertTrue(dict.search("hot-dog"));

        assertFalse(dict.search("Mississippi"));

        for(String val: values){
            assertTrue(dict.delete(val));
        }

        assertEquals(0, dict.getInserted());
    }

    @Test
    void spaceComparison(){
        Dictionary n2 = new Dictionary(1, 15_000);
        Dictionary n = new Dictionary(2, 15_000);

        System.out.println("**********O(N^2)**********");
        System.out.println();
        n2.batchInsert(TEST_PATH+"\\in15000.txt");
        n2.perfectHashing.printHTable();


        System.out.println("**********O(N)**********");
        System.out.println();
        n.batchInsert(TEST_PATH+"\\in15000.txt");
        n.perfectHashing.printHTable();
    }


    @Test
    void insertionTimeComparison(){
        TestMain t = new TestMain();
        Dictionary dictN2 = new Dictionary(1, 15_000);
        Dictionary dictN = new Dictionary(2, 15_000);
//        dictN2.batchInsert(TEST_PATH+"\\in15000.txt");
//        dictN.batchInsert(TEST_PATH+"\\in15000.txt");


//        Dictionary dummy = new Dictionary(1, 15_000);
//        dummy.batchInsert(TEST_PATH+"\\in15000.txt");

        for(int i = 0; i < 5; i++){
            System.out.println("************** O(N) *************");
            t.batchInsertTest(dictN);
            t.searchTest(dictN, "jumbo");
            t.searchTest(dictN, "uiaughoi");
            t.deleteTest(dictN, "uiaughoi");
            t.batchDeleteTest(dictN);
        }

//        for(int i = 0; i < 5; i++){
//            System.out.println("************** O(N^2) *************");
//            t.batchInsertTest(dictN2);
//            t.searchTest(dictN2, "jumbo");
//            t.searchTest(dictN2, "uiaughoi");
//            t.deleteTest(dictN2, "uiaughoi");
//
//        }


    }

    @Test
    void foo(){
        TestMain t = new TestMain();
        Dictionary dictN2 = new Dictionary(1, 15_000);
        for(int i = 0; i < 5; i++){
            System.out.println("************** O(N^2) *************");
            t.batchInsertTest(dictN2);
            t.searchTest(dictN2, "jumbo");
            t.searchTest(dictN2, "uiaughoi");
            t.deleteTest(dictN2, "uiaughoi");
            t.batchDeleteTest(dictN2);

        }
    }

    @Test
    void bar(){
        TestMain t = new TestMain();
        Dictionary dict = new Dictionary(2, 15_000);
        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in7000.txt"));
        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in5000.txt"));
        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in3000.txt"));
        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in1000.txt"));
//        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in50000.txt"));
//        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in1000000.txt"));
    }


    @Test
    void bar2(){
        TestMain t = new TestMain();
        Dictionary dict = new Dictionary(2, 1_000_000);

//        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in50000.txt"));
        t.runBatchTest(dict, (size)->dict.batchInsert(TEST_PATH+"\\in1000000.txt"));
    }

    //size tests, #collisions test
}

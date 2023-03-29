package Dictionary;

import AVLTree.AVL;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class simpleDictionaryTest {

    simpleDictionary dictionary;
    ArrayList<String> arr = new ArrayList<>(Arrays.asList("abc", "bcd",
            "d", "e", "f", "g", "j","i","l","z","a","q","dd"));
    @Test
    void insertTest1() {
        dictionary = new simpleDictionary(1);
        for(String i : arr) {
            boolean ok = dictionary.insert(i);
            assertTrue(ok);
        }
        boolean notOk = dictionary.insert("a");
        assertFalse(notOk);
    }

    @Test
    void insertTest2() {
        dictionary = new simpleDictionary(2);
        for(String i : arr) {
            boolean ok = dictionary.insert(i);
            assertTrue(ok);
        }
        boolean notOk = dictionary.insert("a");
        assertFalse(notOk);
    }

    @Test
    void deleteTest1() {
        dictionary = new simpleDictionary(1);
        for(String i : arr) {
            boolean ok = dictionary.insert(i);
            assertTrue(ok);
        }
        for(String i : arr){
            assert(dictionary.delete(i));
        }
        assertFalse(dictionary.delete("a"));
    }

    @Test
    void deleteTest2() {
        dictionary = new simpleDictionary(2);
        for(String i : arr) {
            boolean ok = dictionary.insert(i);
            assertTrue(ok);
        }
        for(String i : arr){
            assert(dictionary.delete(i));
        }
        assertFalse(dictionary.delete("a"));
    }

    @Test
    void searchTest1() {
        dictionary = new simpleDictionary(1);
        for(String i : arr) { dictionary.insert(i); }
        assertTrue(dictionary.search("z"));
        assertTrue(dictionary.search("bcd"));
        assertFalse(dictionary.search("o"));
        assertFalse(dictionary.search("alo"));
        assertFalse(dictionary.search(" "));
    }

    @Test
    void searchTest2() {
        dictionary = new simpleDictionary(2);
        for(String i : arr) { dictionary.insert(i); }
        assertTrue(dictionary.search("abc"));
        assertTrue(dictionary.search("bcd"));
        assertFalse(dictionary.search("adel"));
        assertFalse(dictionary.search(" "));
    }

    @Test
    void batchInsertTest1() {
        dictionary = new simpleDictionary(1);
        int[] successAndFails = dictionary.batchInsert("E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\Finalllllllllll\\Data-Structure2\\Self-Balancing-Trees\\Random Input\\in1000000.txt");
        assertEquals(1000000, successAndFails[0]);
        assertEquals(0, successAndFails[1]);
    }

    @Test
    void batchInsertTest2() {
        dictionary = new simpleDictionary(2);
        int[] successAndFails = dictionary.batchInsert("E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\Finalllllllllll\\Data-Structure2\\Self-Balancing-Trees\\Random Input\\in200000.txt");
        assertEquals(199800, successAndFails[0]);
        assertEquals(200, successAndFails[1]);
    }

    @Test
    void batchDeleteTest1() {
        dictionary = new simpleDictionary(1);
        dictionary.batchInsert("E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\Finalllllllllll\\Data-Structure2\\Self-Balancing-Trees\\Random Input\\in1000000.txt");
        int[] successAndFails = dictionary.batchDelete("E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\Finalllllllllll\\Data-Structure2\\Self-Balancing-Trees\\Random Input\\in1000000.txt");
        assertEquals(1000000, successAndFails[0]);
        assertEquals(0, successAndFails[1]);
    }
    @Test
    void batchDeleteTest2() {
        dictionary = new simpleDictionary(2);
        dictionary.batchInsert("E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\Finalllllllllll\\Data-Structure2\\Self-Balancing-Trees\\Random Input\\in1000000.txt");
        int[] successAndFails = dictionary.batchDelete("E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\Finalllllllllll\\Data-Structure2\\Self-Balancing-Trees\\Random Input\\in100.txt");
        assertEquals(0, successAndFails[0]);
        assertEquals(100, successAndFails[1]);
        int[] successAndFails2 = dictionary.batchDelete("E:\\CSED25\\2nd Year\\Second term\\Data Structures\\Labs\\Finalllllllllll\\Data-Structure2\\Self-Balancing-Trees\\Random Input\\in1000000.txt");
        assertEquals(1000000, successAndFails2[0]);
        assertEquals(0, successAndFails2[1]);
    }

    @Test
    void sizeTest1() {
        dictionary = new simpleDictionary(1);
        for(String i : arr) { dictionary.insert(i); }
        int size = dictionary.size();
        assertEquals(size,13);
    }
    @Test
    void sizeTest2() {
        dictionary = new simpleDictionary(2);
        for(String i : arr) { dictionary.insert(i); }
        int size = dictionary.size();
        assertEquals(size,13);
    }

    @Test
    void heightTest1() {
        dictionary = new simpleDictionary(1);
        for(String i : arr) { dictionary.insert(i); }
        int height = dictionary.height();
        assertEquals(height,4);
    }

    @Test
    void heightTest2() {
        dictionary = new simpleDictionary(2);
        for(String i : arr) { dictionary.insert(i); }
        int height = dictionary.height();
        assertEquals(height,5);
    }
}
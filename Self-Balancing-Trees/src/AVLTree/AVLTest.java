package AVLTree;

import RBTree.RBTree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AVLTest {
    private  String insertPath = new File("").getAbsolutePath().concat("\\src\\AVLTree\\TestCases\\InsertCases\\");
    private String deletePath = new File("").getAbsolutePath().concat("\\src\\AVLTree\\TestCases\\DeleteCases\\");
    private String searchPath = new File("").getAbsolutePath().concat("\\src\\AVLTree\\TestCases\\SearchCases\\");
    private String sizePath = new File("").getAbsolutePath().concat("\\src\\AVLTree\\TestCases\\SizeCases\\");

    AVL<String> readFromFile(String fileName) {
        AVL<String> tree = new AVL<String>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                boolean ok = tree.insert(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return tree;
    }


    ////////////////////insert test cases///////////
    @org.junit.jupiter.api.Test
    //inserting in acending order the tree balances itself automatically
    void insertTest1() {
        AVL<String> tree = readFromFile(insertPath.concat("insertTest1"));
        String expected = "a b c d e f g ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    //duplicate elements are neglected
    void insertTest2(){
        AVL<String> tree = readFromFile(insertPath.concat("insertTest2"));
        String expected = "aa bb cc dd ee ff ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    //inserting in random order
    void insertTest3(){
        AVL<String> tree = readFromFile(insertPath.concat("insertTest3"));
        String expected = "aa cc nn xx yy zz ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    //inserting words with the same prefix
    void insertTest4(){
        AVL<String> tree = readFromFile(insertPath.concat("insertTest4"));
        String expected = "app apple application apply ";
        String output = tree.test();
        assertEquals(expected,output);
    }


    @org.junit.jupiter.api.Test
    //inserting elements that cause multiple double rotations
    void insertTest5(){
        AVL<String> tree = readFromFile(insertPath.concat("insertTest5"));
        String expected = "a d e i j k n x ";
        String output = tree.test();
        assertEquals(expected,output);
    }
    ////////////////////end insert test cases///////////


    ////////////////////deletion test cases///////////
    @org.junit.jupiter.api.Test
    // delete leaf node
    void deleteTest1() {
        AVL<String> tree = readFromFile(deletePath.concat("deleteTest1"));
        boolean ok = tree.delete("25");
        String expected = "10 20 30 40 42 45 50 52 55 60 70 75 80 85 ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    //deleting the root node and deleting a non-existing node
    void deleteTest2() {
        AVL<String> tree = readFromFile(deletePath.concat("deleteTest2"));
        boolean ok = tree.delete("b");
        String expected = "a d n x ";
        String output = tree.test();
        boolean ok2 = tree.delete("adel");
        assertEquals(expected,output);
        assertTrue(ok);
        assertFalse(ok2);
    }



    @org.junit.jupiter.api.Test
    // delete internal node with one child + delete unlisted node
    void deleteTest3() {
        AVL<String> tree = readFromFile(deletePath.concat("deleteTest3"));
        tree.delete("c");
        String expected = "a aa aaa b ba cc ccc d e f g h i j q ";
        String output = tree.test();
        boolean ok = tree.delete("foo");
        assertEquals(expected,output);
        assertFalse(ok);
    }

    @org.junit.jupiter.api.Test
    //when the balance can be done in a single rotation and a double one then single rotation is applied
    void deleteTest4() {
        AVL<String> tree = readFromFile(deletePath.concat("deleteTest4"));
        boolean ok = tree.delete("a");
        String expected = "b c f z ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    // propagated imbalance
    void deleteTest5() {
        AVL<String> tree = readFromFile(deletePath.concat("deleteTest5"));
        boolean ok = tree.delete("0009");
        String expected = "0002 0003 0005 0010 0011 0012 0013 0015 0017 0018 0020 0030 0033 ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    //deleting all the elements till the tree is empty and then delete extra element
    void deleteTest6() {
        AVL<String> tree = readFromFile(deletePath.concat("deleteTest6"));
        assertFalse(tree.delete("Adel")); //ignoring the case
        assertTrue(tree.delete("adel"));
        assertTrue(tree.delete("belal"));
        assertTrue(tree.delete("mohamed"));
        assertTrue(tree.delete("z"));
        assertFalse(tree.delete("z"));
        assertTrue(tree.delete("x"));
        assertFalse(tree.delete("Ahmed"));
    }
    ////////////////////end deletion test cases///////////

    @org.junit.jupiter.api.Test
    void searchTest1() {
        AVL<String> tree = readFromFile(searchPath.concat("searchTest1"));
        boolean ok = tree.search("amr");
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    void searchTest2() {
        AVL<String> tree = readFromFile(searchPath.concat("searchTest2"));
        boolean ok = tree.search("?");
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    void searchTest3() {
        AVL<String> tree = readFromFile(searchPath.concat("searchTest3"));
        boolean ok = tree.search("parent");
        assertFalse(ok);
    }

    @org.junit.jupiter.api.Test
    void searchTest4() {
        AVL<String> tree = readFromFile(searchPath.concat("searchTest4"));
        boolean ok = tree.search("A");
        assertFalse(ok);
    }

    ///////////////size test cases////////////////
    @org.junit.jupiter.api.Test
    void sizeTest1() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest1"));
        int size = tree.size();
        assertEquals(8,size);
    }

    @org.junit.jupiter.api.Test
        // insert the same element more than once
    void sizeTest2() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest2"));
        int size = tree.size();
        assertEquals(21,size);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void sizeTest3() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest3"));
        int size = tree.size();
        assertEquals(10,size);
    }

    @org.junit.jupiter.api.Test
    void sizeTest4() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest4"));
        int size = tree.size();
        assertEquals(26,size);
    }
    ///////////////end size test cases////////////////


    //////////////////////height test cases///////////////
    @org.junit.jupiter.api.Test
    void heightTest1() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest1"));
        int height = tree.height();
        assertEquals(4,height);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void heightTest2() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest2"));
        int height = tree.height();
        assertEquals(5,height);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void heightTest3() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest3"));
        int height = tree.height();
        assertEquals(4,height);
    }

    @org.junit.jupiter.api.Test
    void heightTest4() {
        AVL<String> tree = readFromFile(sizePath.concat("sizeTest4"));
        int height = tree.height();
        assertEquals(5,height);
    }
    //////////////////////end height test cases///////////////

}
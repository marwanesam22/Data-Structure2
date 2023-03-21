package Test;

import Implementation.RBTree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {

    private String insertPath = new File("").getAbsolutePath().concat("\\src\\Test\\TestCases\\InsertCases\\");
    private String deletePath = new File("").getAbsolutePath().concat("\\src\\Test\\TestCases\\DeleteCases\\");
    private String searchPath = new File("").getAbsolutePath().concat("\\src\\Test\\TestCases\\SearchCases\\");
    private String sizePath = new File("").getAbsolutePath().concat("\\src\\Test\\TestCases\\SizeCases\\");


    RBTree<String> readFromFile(String fileName) {
        RBTree<String> tree = new RBTree<>();
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
    @org.junit.jupiter.api.Test
    // Empty tree
    void insertTest1() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest1"));
        String expected = "amr B ";
        String output = tree.test();
        assertEquals(expected,output);
    }
    @org.junit.jupiter.api.Test
    // insert leaf red nodes and parent is black
    void insertTest2() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest2"));
        String expected = "amr R bmr B cmr R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is red with grandparent root
    void insertTest3() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest3"));
        String expected = "aar R amr B bmr B cmr B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is red with grandparent not root (recursion)
    void insertTest4() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest4"));
        String expected = "aaa R aar B amr R azz B bmr B cmr B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is red with grandparent not root (recursion)
    void insertTest5() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest5"));
        String expected = "aaa B aar R aaw B aaz R amr B azz B bmr R cmr B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is red with grandparent not root (recursion)
    void insertTest6() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest6"));
        String expected = "aaa B aab R aar B aas B aaw R aaz B alz R amr B amz R azz B bmr B cmr B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // insert an element already in the tree
    void insertTest7() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest7"));
        boolean ok = tree.insert("z");
        String expected = "a R b B z R ";
        String output = tree.test();
        assertEquals(expected,output);
        assertFalse(ok);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - LL rotation
    void insertTest8() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest8"));
        String expected = "aaa R alr B amr R bmr B cmr B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - LL rotation ( grandparent is root )
    void insertTest9() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest9"));
        String expected = "aaa R amr B bmr R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - RR rotation
    void insertTest10() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest10"));
        String expected = "amr B bmr B cmr R ddd B zzz R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - RR rotation ( grandparent is root )
    void insertTest11() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest11"));
        String expected = "bmr R ccc B ddd R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - LR rotation
    void insertTest12() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest12"));
        String expected = "bmr B ccc B ddd R dza B dzz R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - LR rotation ( grandparent is root )
    void insertTest13() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest13"));
        String expected = "bmr R cca B ccc R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - RL rotation
    void insertTest14() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest14"));
        String expected = "amr R amw B amx R bmr B cmr B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // parent red and sibling is null - RL rotation ( grandparent is root )
    void insertTest15() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest15"));
        String expected = "bmr R bza B bzz R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void insertTest16() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest16"));
        String expected = "a B b B c B d B e B f B g B h R i B j R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void insertTest17() {
        RBTree<String> tree = readFromFile(insertPath.concat("insertTest17"));
        String expected = "a B b B c B d B e B f B g B h B i B j B k B l R m B n B o B p B q B r B s B t R u B v B w B x R y B z R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void searchTest1() {
        RBTree<String> tree = readFromFile(searchPath.concat("searchTest1"));
        boolean ok = tree.search("amr");
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    void searchTest2() {
        RBTree<String> tree = readFromFile(searchPath.concat("searchTest2"));
        boolean ok = tree.search("?");
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    void searchTest3() {
        RBTree<String> tree = readFromFile(searchPath.concat("searchTest3"));
        boolean ok = tree.search("parent");
        assertFalse(ok);
    }

    @org.junit.jupiter.api.Test
    void searchTest4() {
        RBTree<String> tree = readFromFile(searchPath.concat("searchTest4"));
        boolean ok = tree.search("A");
        assertFalse(ok);
    }

    @org.junit.jupiter.api.Test
    void sizeTest1() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest1"));
        int size = tree.getSize();
        assertEquals(8,size);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void sizeTest2() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest2"));
        int size = tree.getSize();
        assertEquals(17,size);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void sizeTest3() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest3"));
        int size = tree.getSize();
        assertEquals(10,size);
    }

    @org.junit.jupiter.api.Test
    void sizeTest4() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest4"));
        int size = tree.getSize();
        assertEquals(26,size);
    }

    @org.junit.jupiter.api.Test
    void heightTest1() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest1"));
        int height = tree.getHeight();
        assertEquals(4,height);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void heightTest2() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest2"));
        int height = tree.getHeight();
        assertEquals(5,height);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void heightTest3() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest3"));
        int height = tree.getHeight();
        assertEquals(4,height);
    }

    @org.junit.jupiter.api.Test
    void heightTest4() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest4"));
        int height = tree.getHeight();
        assertEquals(7,height);
    }




}
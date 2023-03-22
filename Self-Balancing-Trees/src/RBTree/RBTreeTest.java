package RBTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {

    private String insertPath = new File("").getAbsolutePath().concat("\\src\\RBTree\\TestCases\\InsertCases\\");
    private String deletePath = new File("").getAbsolutePath().concat("\\src\\RBTree\\TestCases\\DeleteCases\\");
    private String searchPath = new File("").getAbsolutePath().concat("\\src\\RBTree\\TestCases\\SearchCases\\");
    private String sizePath = new File("").getAbsolutePath().concat("\\src\\RBTree\\TestCases\\SizeCases\\");


    RBTree<String> readFromFile(String fileName) {
        RBTree<String> tree = new RBTree<String>();
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
        int size = tree.size();
        assertEquals(8,size);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void sizeTest2() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest2"));
        int size = tree.size();
        assertEquals(17,size);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void sizeTest3() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest3"));
        int size = tree.size();
        assertEquals(10,size);
    }

    @org.junit.jupiter.api.Test
    void sizeTest4() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest4"));
        int size = tree.size();
        assertEquals(26,size);
    }

    @org.junit.jupiter.api.Test
    void heightTest1() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest1"));
        int height = tree.height();
        assertEquals(4,height);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void heightTest2() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest2"));
        int height = tree.height();
        assertEquals(5,height);
    }

    @org.junit.jupiter.api.Test
    // insert the same element more than once
    void heightTest3() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest3"));
        int height = tree.height();
        assertEquals(4,height);
    }

    @org.junit.jupiter.api.Test
    void heightTest4() {
        RBTree<String> tree = readFromFile(sizePath.concat("sizeTest4"));
        int height = tree.height();
        assertEquals(7,height);
    }

    @org.junit.jupiter.api.Test
    // delete leaf red node
    void deleteTest1() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest1"));
        boolean ok = tree.delete("amr");
        String expected = "bmr B cmr R ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    // delete leaf red node
    void deleteTest2() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest2"));
        boolean ok = tree.delete("f");
        String expected = "a R b B c R d R h B i B j B ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    // delete internal and leaf red node
    void deleteTest3() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest3"));
        boolean ok = tree.delete("f");
        tree.delete("a");
        tree.delete("q");
        tree.delete("ba");
        String expected = "aa B aaa R b B c B d B e B g R h B i B j B ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    // delete element that doesn't exist
    void deleteTest4() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest4"));
        boolean ok = tree.delete("x");
        String expected = "a B b B c B z R ";
        String output = tree.test();
        assertEquals(expected,output);
        assertFalse(ok);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is black and has black children
    void deleteTest5() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest5"));
        tree.delete("g");
        boolean ok = tree.delete("e");
        String expected = "a R aa B aaa R b B ba R c B d B f B h R i B j B q R ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is black and has black children plus node is double black
    void deleteTest6() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest6"));
        tree.delete("d");
        boolean ok = tree.delete("ab");
        String expected = "ba B ca R ";
        String output = tree.test();
        assertEquals(expected,output);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is black and has black children
    void deleteTest7() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest7"));
        tree.delete("cc");
        tree.delete("ds");
        String expected = "ab B ba B ca R d B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is red plus other cases
    void deleteTest8() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest8"));
        tree.delete("bn");
        String expected = "1 B a B a1 B aa R an B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is red plus other cases
    void deleteTest9() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest9"));
        tree.delete("ac");
        tree.delete("aaa");
        String expected = "1 B a B a1 B aa B an B bn R bz B bzz B bzzz R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is red plus other cases
    void deleteTest10() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest10"));
        tree.delete("ac");
        tree.delete("aaa");
        tree.delete("a1");
        tree.delete("1");
        tree.delete("a");
        String expected = "aa B an R bn B bz B bzz B bzzz R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is black and has far red child
    void deleteTest11() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest11"));
        tree.delete("ac");
        tree.delete("aaa");
        tree.delete("a1");
        tree.delete("1");
        tree.delete("bn");
        tree.delete("an");
        String expected = "a B aa B bz B bzz R bzzz B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is red plus other cases
    void deleteTest12() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest12"));
        tree.delete("ac");
        tree.delete("aaa");
        tree.delete("a1");
        tree.delete("1");
        tree.delete("bn");
        tree.delete("an");
        tree.delete("a");
        String expected = "aa B bz R bzz B bzzz B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    // delete black node, it's sibling is black and has near red child
    void deleteTest13() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest13"));
        tree.delete("ac");
        tree.delete("aaa");
        tree.delete("a1");
        tree.delete("1");
        tree.delete("bn");
        tree.delete("an");
        tree.delete("a");
        tree.delete("bzzz");
        String expected = "aa B bz B bzz B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void deleteTest14() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest14"));
        tree.delete("ac");
        tree.delete("aaa");
        tree.delete("a1");
        tree.delete("1");
        tree.delete("bn");
        tree.delete("an");
        tree.delete("a");
        tree.delete("bzzz");
        tree.delete("aa");
        String expected = "bz B bzz R ";
        String output = tree.test();
        tree.delete("bz");
        String expected2 = "bzz B ";
        String output2 = tree.test();
        tree.delete("bzz");
        String expected3 = "";
        String output3 = tree.test();
        assertEquals(expected,output);
        assertEquals(expected2,output2);
        assertEquals(expected3,output3);
    }

    @org.junit.jupiter.api.Test
    // delete root
    void deleteTest15() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest15"));
        tree.delete("aa");
        String expected = "1 B a B a1 B aaa B ac B an B bn B bz R bzz B bzzz R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void deleteTest16() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest16"));
        tree.delete("m");
        String expected = "a B b B c B d R e B f B g B h B i B j B k B l R n B o R p B q R r B s R ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void deleteTest17() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest17"));
        tree.delete("m");
        tree.delete("q");
        tree.delete("s");
        tree.delete("r");
        String expected = "a B b B c B d R e B f B g B h B i B j B k B l R n B o B p B ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void deleteTest18() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest18"));
        tree.delete("m");
        tree.delete("q");
        tree.delete("s");
        tree.delete("r");
        tree.delete("k");
        String expected = "a B b B c B d R e B f B g B h B i R j B l B n B o R p B ";
        String output = tree.test();
        tree.delete("i");
        tree.delete("h");
        String expected2 = "a B b B c B d R e B f B g B j B l B n R o B p B ";
        String output2 = tree.test();
        assertEquals(expected,output);
        assertEquals(expected2,output2);
    }

    @org.junit.jupiter.api.Test
    void deleteTest19() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest19"));
        tree.delete("z");
        tree.delete("w");
        String expected = "a B b B c B d B e B f B g B h B i B j B k B l R m B n B o B p B q B r B s B t R u B v B x B y R y1 B ";
        String output = tree.test();
        tree.delete("l");
        String expected2 = "a B b B c B d B e B f B g B h B i B j R k B m B n B o R p B q B r B s B t R u B v B x B y R y1 B ";
        String output2 = tree.test();
        assertEquals(expected,output);
        assertEquals(expected2,output2);
    }

    @org.junit.jupiter.api.Test
    void deleteTest20() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest20"));
        tree.delete("z");
        tree.delete("w");
        tree.delete("u");
        tree.delete("x");
        String expected = "a B b B c B d B e B f B g B h B i B j B k B l R m B n B o B p B q B r B s B t R v B y B y1 B ";
        String output = tree.test();
        tree.delete("a");
        String expected2 = "b B c R d B e B f R g B h B i B j B k B l R m B n B o B p B q B r B s B t B v B y B y1 B ";
        String output2 = tree.test();
        tree.delete("q");
        String expected3 = "b B c R d B e B f R g B h B i B j B k B l B m B n B o B p B r B s R t B v B y R y1 B ";
        String output3 = tree.test();
        assertEquals(expected,output);
        assertEquals(expected2,output2);
        assertEquals(expected3,output3);
    }

    @org.junit.jupiter.api.Test
    void deleteTest21() {
        RBTree<String> tree = readFromFile(deletePath.concat("deleteTest20"));
        tree.delete("z");
        tree.delete("w");
        tree.delete("u");
        tree.delete("x");
        tree.delete("a");
        tree.delete("q");
        tree.delete("c");
        tree.delete("b");
        tree.delete("g");
        String output = tree.test();
        String expected = "d B e B f B h B i B j B k B l B m B n B o B p B r B s R t B v B y R y1 B ";
        tree.delete("s");
        tree.delete("r");
        String output2 = tree.test();
        String expected2 = "d B e B f B h B i B j B k B l B m B n B o B p B t B v R y B y1 B ";

        tree.delete("y1");
        String expected3 = "d B e B f B h B i B j B k B l B m B n B o B p B t B v B y B ";
        String output3 = tree.test();

        assertEquals(expected,output);
        assertEquals(expected2,output2);
        assertEquals(expected3,output3);

    }



}
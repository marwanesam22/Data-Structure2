package AVLTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AVLTest {
    private String insertPath = new File("").getAbsolutePath().concat("\\src\\AVLTree\\TestCases\\InsertCases\\");
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



    @org.junit.jupiter.api.Test
    void insertTest1() {
        AVL<String> tree = readFromFile(insertPath.concat("insertTest1"));
        String expected = "amr ";
        String output = tree.test();
        assertEquals(expected,output);
    }

    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void height() {
    }

    @org.junit.jupiter.api.Test
    void search() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

}
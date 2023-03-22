package Implementation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RBTree<String> tree = new RBTree<>();
        Scanner read = new Scanner(System.in);
        String t;

        tree.insert("an");
        tree.insert("bn");
        tree.insert("a");
        tree.insert("aa");
        tree.insert("1");
        tree.insert("a1");
        tree.insert("aaa");
        tree.insert("ac");
        tree.insert("bz");
        tree.insert("bzz");
        tree.insert("bzzz");

        tree.delete("ac");
        tree.delete("aaa");
        tree.delete("a1");
        tree.delete("1");
        tree.delete("bn");
        tree.delete("an");
        tree.delete("a");
        tree.delete("bzzz");
        tree.delete("aa");




//        while (true){
//            t = read.next();
//            if(t.equals("-1")) break;
//            boolean x = tree.insert(t);
//            System.out.println((x ? "inserted successfully" : "insertion failed"));
//            tree.traverseInorder(tree.getRoot());
//            System.out.println("height = " + tree.getHeight());
//            System.out.println("size = " + tree.getSize());
//        }

//        while(true){
//            t = read.next();
//            if(t.equals("-1"))break;
//            tree.delete(t);
//            tree.traverseInorder(tree.getRoot());
//        }
        tree.delete("bz");
//        System.out.println("dasldhalj");
//        tree.delete(60);
//        tree.delete(5555);
//        tree.insert(701);
//        tree.delete(630);
        tree.traverseInorder(tree.getRoot());
//        System.out.println("hahaha");
//        RBNode<Integer> x = tree.getNodeToBeDeletedExactly(tree.search(tree.root, 50));
//        System.out.println(x.getData());
//        System.out.println(x.getColor());
//        System.out.println(x.getParent().getData());
//        System.out.println(x.getRight().getData());
//        System.out.println(x.getLeft().getData());
    }
}
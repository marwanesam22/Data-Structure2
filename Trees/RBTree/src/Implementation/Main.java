package Implementation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RBTree<Integer> tree = new RBTree<>();
        Scanner read = new Scanner(System.in);
        int t;
//        tree.insert(800);
//        tree.insert(700);
//        tree.insert(600);
//        tree.insert(500);
//        tree.insert(550);
//        tree.insert(620);

        while (true){
            t = read.nextInt();
            if(t == -1) break;
            boolean x = tree.insert(t);
            System.out.println((x ? "inserted successfully" : "insertion failed"));
            tree.traverseInorder(tree.getRoot());
            System.out.println("height = " + tree.getHeight());
            System.out.println("size = " + tree.getSize());
        }
//        System.out.println("dasldhalj");
//        tree.delete(60);
        tree.delete(5555);
//        tree.insert(701);
//        tree.delete(630);
        tree.traverseInorder(tree.getRoot());
        System.out.println("hahaha");
//        RBNode<Integer> x = tree.getNodeToBeDeletedExactly(tree.search(tree.root, 50));
//        System.out.println(x.getData());
//        System.out.println(x.getColor());
//        System.out.println(x.getParent().getData());
//        System.out.println(x.getRight().getData());
//        System.out.println(x.getLeft().getData());
    }
}
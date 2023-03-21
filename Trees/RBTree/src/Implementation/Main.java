package Implementation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        RBTree<Integer> tree = new RBTree<>();
        Scanner read = new Scanner(System.in);
        int t;
        do {
            t = read.nextInt();
            boolean x = tree.insert(t);
            System.out.println((x?"inserted successfully":"insertion failed"));
            tree.traverseInorder(tree.getRoot());
            System.out.println("height = " + tree.getHeight());
            System.out.println("size = " + tree.getSize());
        } while(t != -1);

        System.out.println(tree.getNodeToBeDeletedExactly(tree.search(tree.root, 50)).getData());

    }
}
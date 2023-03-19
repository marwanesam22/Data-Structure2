public class Main {

    public static void main(String[] args) {

        BST tree = new BST();

        tree.insert(5);
        tree.insert(3);
        tree.insert(20);
        tree.insert(120);
        tree.insert(230);
        tree.insert(22);
        tree.insert(11);
        tree.insert(1);

        tree.traverseInOrder(tree.root);

        tree.delete(5);

//        tree.traverseInOrder(tree.root);




    }
}

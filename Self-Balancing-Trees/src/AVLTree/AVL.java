package AVLTree;
import Main.*;
import RBTree.RBNode;

public class AVL < T extends Comparable < T >> implements IBalancedTree<T> {

    private int Number_of_nodes;
    private AvlNode < T > root;
    private String testTree = "";

    public AVL() {
        //default constructor
//        this.Number_of_nodes = 0;
//        this.root = null;
    }

    public AvlNode < T > getRoot() {
        return root;
    }



    ///////////////auxiliary functions//////////////////////
    private void set_height(AvlNode < T > node) {
        //function to set the height of by node.height = max(node.right.height, node.left.height)+1
        if (node.getRight() == null && node.getLeft() == null) node.setHeight(0);
        else if (node.getRight() == null) node.setHeight(node.getLeft().getHeight() + 1);
        else if (node.getLeft() == null) node.setHeight(node.getRight().getHeight() + 1);
        else node.setHeight(Math.max(node.getRight().getHeight(), node.getLeft().getHeight()) + 1);
    }

    ///////////////rotation functions//////////////////////
    private AvlNode < T > single_right_rotation(AvlNode < T > root) {
        AvlNode < T > tmp = root.getRight();
        root.setRight(tmp.getLeft());
        tmp.setLeft(root);
        set_height(root);
        set_height(tmp);
        return tmp;
    }
    private AvlNode < T > single_left_rotation(AvlNode < T > root) {
        AvlNode < T > tmp = root.getLeft();
        root.setLeft(tmp.getRight());
        tmp.setRight(root);
        set_height(root);
        set_height(tmp);
        return tmp;
    }
    private AvlNode < T > double_right_rotation(AvlNode < T > root) {
        root.setRight(single_left_rotation(root.getRight()));
        return single_right_rotation(root);
    }
    private AvlNode < T > double_left_rotation(AvlNode < T > root) {
        root.setLeft(single_right_rotation(root.getLeft()));
        return single_left_rotation(root);
    }
    /////////////// end rotation functions//////////////////////

    private int getBalanceFactor(AvlNode < T > node) {
        //function to get the balance factor of a given node by
        //balance factor = left height - right height
        int balance_factor;
        if (node.getRight() == null && node.getLeft() == null) balance_factor = 0;
        else if (node.getRight() == null) {
            balance_factor = node.getLeft().getHeight() + 1;
        } else if (node.getLeft() == null) {
            balance_factor = (node.getRight().getHeight() + 1) * -1;
        } else {
            balance_factor = node.getLeft().getHeight() - node.getRight().getHeight();
        }
        return balance_factor;
    }

    private AvlNode < T > check_node_balance(AvlNode < T > node) {
        //is the given node is imbalanced then perform the suitable rotation(s) to get it fixed

        int balance_factor = getBalanceFactor(node);

        if (Math.abs(balance_factor) <= 1) return node;

        String imbalance_cause = "";
        AvlNode < T > nextNode;
        if (balance_factor < 0) {
            imbalance_cause += 'r';
            nextNode = node.getRight();
        } else {
            imbalance_cause += 'l';
            nextNode = node.getLeft();
        }

        int nextBalanceFactor = getBalanceFactor(nextNode);
        if (nextBalanceFactor < 0) imbalance_cause += 'r';
        else if (nextBalanceFactor > 0) imbalance_cause += 'l';
        else {
            if (imbalance_cause.equals("r")) imbalance_cause += 'r';

            else imbalance_cause += 'l';
        }

        switch (imbalance_cause) {
            case "ll":
                node = single_left_rotation(node);
                break;
            case "rr":
                node = single_right_rotation(node);
                break;
            case "lr":
                node = double_left_rotation(node);
                break;
            case "rl":
                node = double_right_rotation(node);
                break;
            default:

        }
        return node;
    }

    public void traverse_tree(AvlNode < T > root) {
        //function to traverse the tree in an in-order way
        if (root == null) return;
        traverse_tree(root.getLeft());
        String rightChild = "", leftChild = "";
        if (root.getRight() != null && root.getLeft() != null) {
            rightChild += root.getRight().getData();
            leftChild += root.getLeft().getData();
        } else if (root.getRight() != null) {
            rightChild += root.getRight().getData();
            leftChild += "null";
        } else if (root.getLeft() != null) {
            leftChild += root.getLeft().getData();
            rightChild += "null";
        } else {
            leftChild += "null";
            rightChild += "null";
        }
        System.out.println("data = " + root.getData() + ", height = " + root.getHeight() + ", left = " + leftChild + ", right = " + rightChild);
        traverse_tree(root.getRight());
    }

    public void traverse(){
        this.traverse_tree(this.root);
    }

    private AvlNode < T > getInorderPredecessor(AvlNode < T > root) {
        //function to get the rightmost child of the parent of the given node
        if (root.getRight() == null) return root;
        return getInorderPredecessor(root.getRight());
    }

    private AvlNode < T > getInorderSuccessor(AvlNode < T > root) {
        //function to get the leftmost child of the parent of the given node
        if (root.getLeft() == null) return root;
        return getInorderSuccessor(root.getLeft());
    }

    ///////////////end auxiliary functions//////////////////////



    ////////////////insert functions/////////////////////////////
    private AvlNode < T > insert(AvlNode < T > root, T data) {
        if (root == null) {
            return new AvlNode < > (data);
        }
        if (data.compareTo(root.getData()) > 0) {
            root.setRight(insert(root.getRight(), data));
            set_height(root);
            root = check_node_balance(root);
        } else if (data.compareTo(root.getData()) < 0) {
            root.setLeft(insert(root.getLeft(), data));
            set_height(root);
            root = check_node_balance(root);
        }
        return root;
    }

    public boolean insert(T data) {
        if (search(data)) return false;
        root = insert(root, data);
        Number_of_nodes++;
        return true;
    }

    ////////////////end insert functions/////////////////////////////




    ////////////// size function////////////////////////////
    public int size() {
        return this.Number_of_nodes;
    }
    ////////////// end size function////////////////////////////



    ////////////// height function////////////////////////////
    public int height() {
        return this.root != null? this.root.getHeight() : 0;
    }
    ////////////// end height function////////////////////////////



    ////////////////search functions/////////////////////////////
    private AvlNode < T > search(AvlNode < T > root, T data) {
        if (root == null) return null;
        if (data.compareTo(root.getData()) > 0) {
            return search(root.getRight(), data);
        } else if (data.compareTo(root.getData()) < 0) {
            return search(root.getLeft(), data);
        } else return root;
    }

    public boolean search(T data) {
        return search(this.root, data) != null ? true : false;
    }

    ////////////////end search functions/////////////////////////////



    ////////////////delete functions/////////////////////////////
    private AvlNode < T > delete(AvlNode < T > root, T data) {
        if (data.compareTo(root.getData()) == 0) {
            if (root.getLeft() == null && root.getRight() == null) return null;
            else if (root.getRight() != null) return root.getRight();
            else if (root.getLeft() != null) return root.getLeft();
        }
        if (data.compareTo(root.getData()) > 0) {
            root.setRight(delete(root.getRight(), data));
            set_height(root);
            root = check_node_balance(root);
        } else if (data.compareTo(root.getData()) < 0) {
            root.setLeft(delete(root.getLeft(), data));
            set_height(root);
            root = check_node_balance(root);
        }
        return root;
    }

    public boolean delete(T data) {
        AvlNode < T > deleted_node = search(this.root, data);
        if (deleted_node == null) return false;
        if (deleted_node.getRight() == null && deleted_node.getLeft() == null) this.root = delete(this.root, data);
        else if ((deleted_node.getRight() == null && deleted_node.getLeft() != null)) {
            AvlNode < T > inorderPredecessor = getInorderPredecessor(deleted_node.getLeft());
            T tmp = inorderPredecessor.getData();
            this.root = delete(this.root, inorderPredecessor.getData());
            deleted_node.setData(tmp);
        } else {
            AvlNode < T > inorderSuccessor = getInorderSuccessor(deleted_node.getRight());
            T tmp = inorderSuccessor.getData();
            this.root = delete(this.root, inorderSuccessor.getData());
            deleted_node.setData(tmp);
        }
        this.Number_of_nodes--;
        return true;
    }

    ////////////////end delete functions/////////////////////////////

    public String test() {
        testTree = "";
        print(this.root);
        return testTree;
    }

    private void print(AvlNode<T> current) {
        if (current == null) {
            return;
        }
        print(current.getLeft());
        testTree += current.getData() + " ";
        print(current.getRight());
    }
}
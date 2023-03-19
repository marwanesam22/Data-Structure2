public class BST {

    static class Node {

        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

    }

    Node root;

    // INSERT
    private Node insertRecursive(Node curr, int value) {

        if(curr == null) {
            return new Node(value);
        }

        if(value < curr.key) {
            curr.left = insertRecursive(curr.left, value);
        } else if(value > curr.key) {
            curr.right = insertRecursive(curr.right, value);
        } else {
            // value already exists
            return curr;
        }

        return curr;

    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    // SEARCH
    private Boolean searchRecursively(Node curr, int value) {

        if(curr == null) {
            return false;
        }

        if(value == curr.key) {
            return true;
        }

        if(value < curr.key) {
            return searchRecursively(curr.left,value);
        } else {
            return searchRecursively(curr.right,value);
        }

    }

    public Boolean search(int value) {
        return searchRecursively(root, value);
    }

    // DELETE
    private Node deleteRecursively(Node curr, int value) {

        if(curr == null) {
            return null;
        }

        if(value == curr.key) {
            //HERE the element to be removed
            // CASE1
            if(curr.left == null && curr.right == null) {
                return null;
            }

            //CASE2
            if(curr.right == null) {
                return curr.left;
            }

            //CASE3
            if(curr.left == null) {
                return curr.right;
            }

            //CASE4
            int smallestValue = smallestRightSubTree(curr.right);
            curr.key = smallestValue;
            curr.right = deleteRecursively(curr.right, smallestValue);
            return curr;

        }

        if(value < curr.key) {
            curr.left = deleteRecursively(curr.left, value);
        } else {
            curr.right = deleteRecursively(curr.right, value);
        }

        return curr;
    }

    private int smallestRightSubTree(Node curr) {
        if(curr.left == null) {
            return root.key;
        }
        return smallestRightSubTree(curr.left);
    }

    public void delete(int value) {
        root = deleteRecursively(root, value);
    }

    // TRAVERSE
    // In-Order
    public void traverseInOrder(Node root) {
        if(root != null) {
            traverseInOrder(root.left);
            System.out.print(root.key + " ");
            traverseInOrder(root.right);
        }
    }

    // Pre-Order
    public void traversePreOrder(Node root) {
        if(root != null) {
            System.out.print(root.key + " ");
            traversePreOrder(root.left);
            traversePreOrder(root.right);
        }
    }

    // In-Order
    public void traversePostOrder(Node root) {
        if(root != null) {
            traversePostOrder(root.left);
            traversePostOrder(root.right);
            System.out.print(root.key + " ");
        }
    }

}

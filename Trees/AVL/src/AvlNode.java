

public class AvlNode < T extends Comparable < T >>{
    //the node data structure for the AVL tree
    private T data;
    private int height;
    private AvlNode < T > right;
    private AvlNode < T > left;


    AvlNode(T data) {
        this.data = data;
        this.right = null;
        this.left = null;
        this.height = 0;
    }
    AvlNode(T data, AvlNode < T > right, AvlNode < T > left) {
        this.data = data;
        this.right = right;
        this.left = left;
        this.height = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AvlNode < T > getRight() {
        return right;
    }

    public void setRight(AvlNode < T > right) {
        this.right = right;
    }

    public AvlNode < T > getLeft() {
        return left;
    }

    public void setLeft(AvlNode < T > left) {
        this.left = left;
    }
}


package RBTree;

public class RBNode < T extends Comparable<T> > {

    private T data;
    private RBNode<T> parent;
    private RBNode<T> right;
    private RBNode<T> left;
    private boolean color;

    public RBNode(T data, RBNode<T> parent, RBNode<T> right, RBNode<T> left, boolean color) {this.data = data;
        this.parent = parent;
        this.right = right;
        this.left = left;
        this.color = color;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setParent(RBNode<T> parent) {
        this.parent = parent;
    }

    public void setRight(RBNode<T> right) {
        this.right = right;
    }

    public void setLeft(RBNode<T> left) {
        this.left = left;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public T getData() {
        return data;
    }

    public RBNode<T> getParent() {
        return parent;
    }

    public RBNode<T> getRight() {
        return right;
    }

    public RBNode<T> getLeft() {
        return left;
    }

    public boolean getColor() {
        return color;
    }
}


public interface IBalancedTree<T extends Comparable> {
    //interface to be implemented by every self-balanced tree data structure
    public boolean insert(T data);
    public boolean search(T data);
    public int size();
    public int height();
    public boolean delete(T data);
}

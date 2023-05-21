public interface IPerfectHashing {
    boolean insert(int key);
    boolean delete(int key);

    boolean search(int key);

    int[] batchInsert(int[] keys);

    int[] batchDelete(int[] keys);

    int printHTable();
}

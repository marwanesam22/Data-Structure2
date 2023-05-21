package UniversalPerfectHashing;

public interface IPerfectHashing {
    boolean insert(int var1);

    boolean delete(int var1);

    boolean search(int var1);

    int[] batchInsert(int[] var1);

    int[] batchDelete(int[] var1);

    int printHTable();
}

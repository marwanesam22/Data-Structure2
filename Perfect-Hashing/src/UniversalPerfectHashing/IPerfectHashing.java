package UniversalPerfectHashing;

public interface IPerfectHashing {
    boolean insert(String word);

    boolean delete(String word);

    boolean search(String word);

    int[] batchInsert(String[] words);

    int[] batchDelete(String[] words);

    int printHTable();
}

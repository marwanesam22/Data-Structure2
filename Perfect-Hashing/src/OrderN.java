public class OrderN extends PerfectHashing {

    private OrderN2[] hashTable;

    public OrderN(int[] values) {
        tableLength = values.length;
        universalHashingFamily = new UniversalHashingFamily(get_u(values), get_b());
        hashing_matrix = universalHashingFamily.getrandomizedH();
    }

}

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
            int[] arr = {1,2,3,4,5,6,7,8};
            OrderN2 orderN2 = new OrderN2(arr);
            orderN2.hashValues(arr);
            System.out.println(orderN2.printHTable());
            System.out.println("Number of collisions =  " + orderN2.getNumberOfCollisions() );
        System.out.println(Arrays.deepToString(orderN2.hashing_matrix));
//        UniversalHashingFamily u = new UniversalHashingFamily(4,3);
//        System.out.println(Arrays.deepToString(u.hashing_matrix));
//        int x = 7; // Example key
//        int hashValue = u.HF(x);
//
//        System.out.println("Hash value for key " + x + ": " + hashValue);
    }
}
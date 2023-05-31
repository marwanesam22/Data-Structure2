package Main;

import Sorting.NonComparisonSort;
import Sorting.SortArray;

import java.util.Arrays;
import java.util.Scanner;

public class CLI {
    public void start() {

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the path of the file containing the array: ");
            String path = scan.nextLine();
            System.out.println();
            SortArray sort = new SortArray(path);

            System.out.println("---------------------- Main Menu--------------------------");
            System.out.println("Select one of the following algorithms to run (1/2/3/4): ");
            System.out.println("1- Simple Sort      2- Efficient Sort       3- Non-Comparison Sort      4- Heap Sort");
            System.out.print("Option: ");
            int option = scan.nextInt();
            while (option < 1 || option > 4) {
                System.out.println("Please select a valid input !");
                option = scan.nextInt();
            }


            System.out.println("----------------------------------------------------------");
            System.out.println("Would you like the intermediate steps to show up ? (1 for yes & 0 for no)");
            System.out.print("Option: ");


            int printIntermediate = scan.nextInt();
            while (printIntermediate != 0 && printIntermediate != 1) {
                System.out.println("Please select a valid input !");
                printIntermediate = scan.nextInt();

            }

            if (printIntermediate == 1) {
                System.out.println();
                System.out.println("The intermediate steps are: ");
            }

            if (option == 1) {
                sort.simpleSort(printIntermediate);
            } else if (option == 2) {
                sort.efficientSort(printIntermediate);
            } else if (option == 3) {
                sort.nonComparisonSort(printIntermediate);
            } else {
                sort.heapSort(printIntermediate);
            }

            System.out.println();
            System.out.println("The final result is: ");
            System.out.println(Arrays.toString(sort.getArray()));
            System.out.println("============================================================================");
        }

    }
}

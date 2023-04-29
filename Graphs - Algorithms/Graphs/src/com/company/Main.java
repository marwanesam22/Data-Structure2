package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Graph g = new Graph(input.nextLine());
        int[][] costs = new int[g.get_graph_size()][g.get_graph_size()];
//        System.out.println(g.floyed_warshal(g.costsMatrix,costs));
//        System.out.println(Arrays.deepToString(costs));

        int[] bfCosts = new int[g.get_graph_size()];
        Arrays.fill(bfCosts, Integer.MAX_VALUE);
        bfCosts[0] = 0;
        int[] bfParents = new int[g.get_graph_size()];
        System.out.println(g.bfShortestPath(0, bfCosts, bfParents));
        System.out.println(Arrays.toString(bfCosts));
        System.out.println(Arrays.toString(bfParents));


//        System.out.println(Arrays.deepToString(g.floyed_warshal2(g.costsMatrix)));
//        int v = input.nextInt();
//        int e = input.nextInt();
//        int arr[][] = new int [e][3];
//        for(int i =0; i<e; i++){
//            int x = input.nextInt();
//            arr[i][0] = x;
//
//            x = input.nextInt();
//            arr[i][1] = x;
//
//            x = input.nextInt();
//            arr[i][2] = x;
//        }
//

//        int []costs = new int [v];
//        int []parent = new int [v];
//        g.apply_dijkstra(0, costs, parent);
//
//        System.out.println("finished");
//
//    }

//        C:\Users\Adel\Desktop\inputFile.txt
    }
}

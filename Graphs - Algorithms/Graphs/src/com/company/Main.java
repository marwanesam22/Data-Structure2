package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int v = input.nextInt();
        int e = input.nextInt();
        int arr[][] = new int [e][3];
        for(int i =0; i<e; i++){
            int x = input.nextInt();
            arr[i][0] = x;

            x = input.nextInt();
            arr[i][1] = x;

            x = input.nextInt();
            arr[i][2] = x;
        }

        Graph g = new Graph(v, e, arr);
        int []costs = new int [v];
        int []parent = new int [v];
        g.apply_dijkstra(0, costs, parent);

        System.out.println("finished");

    }
}

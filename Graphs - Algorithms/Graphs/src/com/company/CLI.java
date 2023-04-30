package com.company;

import java.util.Scanner;

public class CLI {

    Graph graph;

    Scanner scan;
    public void CLI_runner(){
        scan = new Scanner(System.in);
        System.out.println("Enter the path of the file containing the graph structure, please: ");
        String path = scan.nextLine();
        graph = new Graph(path);
        while (true){
            System.out.println("===================================================================================");
            System.out.println("\t\t\t\t\t\tMain menu");
            System.out.println("Please select the desired operation by typing out the number associated to it");
            System.out.println("1 : Find the shortest paths from source node to all other nodes.");
            System.out.println("2 : Find the shortest paths between all the pairs of nodes.");
            System.out.println("3 : Check if the graph contains a negative cycle.");
            System.out.println("4 : Exit.");
            System.out.print("Option: ");
            int option = scan.nextInt();
            System.out.println("===================================================================================");
            if(option == 1){
                System.out.print("Enter the source node please:");
                int src = scan.nextInt();
                System.out.println();
                src = check_validation_of_node(src);
                int algo = specify_algorithm();
                if(algo == 1){
                    //Dijkstra
                    int[] parents = new int[graph.size()];
                    int[] costs = new int[graph.size()];
                    graph.apply_dijkstra(src,costs,parents);
                    while (true){
                        int subOption = print_subMenue("Dijkstra");
                        if(subOption == 1 || subOption == 2){
                            System.out.print("Enter the destination node please: ");
                            int dest = scan.nextInt();
                            System.out.println();
                            dest = check_validation_of_node(dest);
                            if(subOption == 1){
                                System.out.print("The shortest path cost form node " + src + " to node " + dest + " is: ");
                                System.out.print(costs[dest]);
                                System.out.println();
                            }else {
                                System.out.print("The shortest path itself form node " + src + " to node " + dest + " is: ");
                                System.out.print(getPath(parents,src,dest));
                                System.out.println();
                            }
                        } else if (subOption == 3) {
                            break;
                        } else{
                            System.out.println("invalid option please reselct the desired operation..");
                        }
                    }
                } else if (algo == 2) {
                    //bellman
                    //TODO errornous bellman
                    int[] parents = new int[graph.size()];
                    int[] costs = new int[graph.size()];
                    graph.bfShortestPath(src,costs,parents);
                    while (true){
                        int subOption = print_subMenue("Bellman-Ford");
                        if(subOption == 1 || subOption == 2){
                            System.out.print("Enter the destination node please: ");
                            int dest = scan.nextInt();
                            System.out.println();
                            dest = check_validation_of_node(dest);
                            if(subOption == 1){
                                System.out.print("The shortest path cost form node " + src + " to node " + dest + " is: ");
                                System.out.print(costs[dest]);
                                System.out.println();
                            }else {
                                System.out.print("The shortest path itself form node " + src + " to node " + dest + " is: ");
                                System.out.print(getPath(parents,src,dest));
                                System.out.println();
                            }
                        } else if (subOption == 3) {
                            break;
                        } else{
                            System.out.println("invalid option please reselct the desired operation..");
                        }
                    }
                }
                else{
                    //floyed
                    int[][] costs = new int[graph.size()][graph.size()];
                    int[] parents = new int[graph.size()];
                    int[] c = new int[graph.size()];
                    graph.bfShortestPath(src,c,parents);
                    graph.floyed_warshal(graph.costsMatrix, costs);
                    while (true){
                        int subOption = print_subMenue("Floyed-Warshal");
                        if(subOption == 1 || subOption == 2){
                            System.out.print("Enter the destination node please: ");
                            int dest = scan.nextInt();
                            System.out.println();
                            dest = check_validation_of_node(dest);
                            if(subOption == 1){
                                System.out.print("The shortest path cost form node " + src + " to node " + dest + " is: ");
                                System.out.print(costs[src][dest]);
                                System.out.println();
                            }else {
                                System.out.print("The shortest path itself form node " + src + " to node " + dest + " is: ");
                                System.out.print(getPath(parents,src,dest));
                                System.out.println();
                            }
                        } else if (subOption == 3) {
                           break;
                        } else{
                            System.out.println("invalid option please reselct the desired operation..");
                        }
                    }
                }

            } else if (option == 2) {

            } else if (option == 3) {

            } else if (option == 4) {
                break;
            }
            else{
                System.out.print("Error, type out 1 to restart otherwise type anything: ");
                int errorHandler = scan.nextInt();
                if(errorHandler != 1)break;
            }
        }
    }

    private int specify_algorithm(){
        System.out.println("Specify the algorithm to run out of the following three: ");
        System.out.println("1 : Dijkestra");
        System.out.println("2 : Bellman-Ford");
        System.out.println("3 : Floyd-Warshall");
        System.out.print("Option: ");
        int option = scan.nextInt();
        System.out.println();
        if(option == 1 || option ==2 || option == 3)return option;
        else{
            System.out.println("Error, please select a valid option!");
            return specify_algorithm();
        }
    }
    private int check_validation_of_node(int nodeEntered){
        int tmp  = nodeEntered;
        while (!(nodeEntered >= 0 && nodeEntered < graph.size())){
            System.out.println("Please enter a valid node: ");
            tmp = scan.nextInt();
        }
        return tmp;
    }

    private String getPath(int[] parents, int src, int dest){
        //TODO if the node has two digits
        String path = "";
        path += dest;
        if(src == dest)return path;
        int tmp = dest;
        while (parents[tmp] != src){
            tmp = parents[tmp];
            path += tmp;
        }
        path += src;
        String result = "";
        for(int i = path.length()-1 ; i>=0; i--){
            result += path.charAt(i);
            if(i != 0)result += " => ";
        }
        return result;
    }

    private int print_subMenue(String algorithm){
        System.out.println("============================================================");
        System.out.println("\t\t\t\t\t\tSub menu of " + algorithm);
        System.out.println("Please select the desired operation out of these two: ");
        System.out.println("1 : Find the shortest path to a specific node.");
        System.out.println("2 : Find the path itself to a specific node.");
        System.out.println("3 : Return to the main menu.");
        System.out.print("Option: ");
        int subOption = scan.nextInt();
        System.out.println();
        return subOption;
    }

}

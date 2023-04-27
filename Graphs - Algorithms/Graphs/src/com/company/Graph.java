package com.company;

import java.security.KeyPair;
import java.sql.Array;
import java.util.*;

public class Graph {
    Map<Integer, Map<Integer, Integer>> graph; /* first int for node number - second map <neighbourNode, weight> */
    final int oo = Integer.MAX_VALUE;

    Graph(int v, int e, int [][] arr){
        graph = new HashMap<>(v);
        for(int i=0; i<v; i++){
            Map<Integer, Integer> innerMap  = new HashMap<>();
            graph.put(i, innerMap);
        }

        for(int i=0; i<e; i++) {
            int from = arr[i][0];
            int to = arr[i][1];
            int weight = arr[i][2];
            Map<Integer, Integer> innerMap  = graph.get(from);
            innerMap.put(to, weight);
            graph.put(from, innerMap);
        }
    }

    int get_graph_size(){
        return graph.size();
    }

    void apply_dijkstra(int source_node, int []costs, int []parent){
        int vertices = get_graph_size();
        boolean []visited = new boolean[vertices];
        /* first int in PQ is weight, second is node number */
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();

        Arrays.fill(visited, false);
        Arrays.fill(costs, oo);
        Arrays.fill(parent, -1);

        costs[source_node] = 0;
        priorityQueue.offer(new Pair(0, source_node));

        while (!priorityQueue.isEmpty()){
            int node = priorityQueue.peek().second;
            priorityQueue.poll();
            Map<Integer, Integer> neighbours = graph.get(node);
            visited[node] = true;

            for(Map.Entry<Integer, Integer> element : neighbours.entrySet()){
                Integer neighbour_node = element.getKey();
                Integer weight = element.getValue();
                if(!visited[neighbour_node]){
                    if(costs[node] + weight < costs[neighbour_node]){
                        costs[neighbour_node] = costs[node] + weight;
                        parent[neighbour_node] = node;
                    }
                }
                priorityQueue.offer(new Pair(costs[neighbour_node], neighbour_node));
            }
        }

        System.out.println("finished DI");
    }

}
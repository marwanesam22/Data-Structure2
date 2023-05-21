package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    public List<List<Pair>> graph; /* first int for node number - second map <neighbourNode, weight> */
    final int oo = 1000000000;
    public int V,E;
    public int[][] costsMatrix;

    public Graph(String path){
        graphInitializer(path);
    }

    private void graphInitializer(String inputFile){
        try {
            Scanner scanner = new Scanner(new File(inputFile));
            V = scanner.nextInt();
            graph = new ArrayList<>(V);
            for(int i=0; i<V; i++){
                List<Pair> innerList = new ArrayList<>();
                graph.add(innerList);
            }

            E = scanner.nextInt();
            costsMatrix= new int[V][V];
            for (int i = 0; i < E; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                int weight = scanner.nextInt();
                costsMatrix[start][end] = weight;
                List<Pair> innerList  = graph.get(start);
                innerList.add(new Pair(end, weight));
//                graph.add(start, innerList);
//                graph.put(start, innerMap);
            }
            scanner.close();
            // Print the graph
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(i != j && costsMatrix[i][j] == 0)costsMatrix[i][j] = oo;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int size(){
        return graph.size();
    }

     public void apply_dijkstra(int source_node, int []costs, int []parent){
        int vertices = size();
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
            List<Pair> neighbours = graph.get(node);
            visited[node] = true;
            if(neighbours != null){
                for(Pair element : neighbours){
                    int neighbour_node = element.first;
                    int weight = element.second;
                    if(!visited[neighbour_node]){
                        if(costs[node] + weight < costs[neighbour_node]){
                            costs[neighbour_node] = costs[node] + weight;
                            parent[neighbour_node] = node;
                            priorityQueue.offer(new Pair(costs[neighbour_node], neighbour_node));
                        }
                    }
                }
            }
        }
     }

    public boolean floyed_warshal(int[][] predecessors, int[][] costs){

        for(int i = 0 ; i<V ;i ++){
            for(int j = 0 ; j < V ; j++){
                predecessors[i][j] = -1;
                costs[i][j] = costsMatrix[i][j];
                if(costs[i][j] != oo && costs[i][j] != 0){
                    predecessors[i][j] = i;
                }
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (costs[i][k] != oo && costs[k][j] != oo && costs[i][j] > costs[i][k] + costs[k][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        predecessors[i][j] = predecessors[k][j];
                    }
                }
            }
        }

        for(int i = 0 ; i<V ; i++){
            if(costs[i][i] < 0)return false;
        }
        return true;
    }

    public boolean bfShortestPath(int sourceNode, int[] costs, int[] parents){
        //returns true if shortest paths are defined in the graph,
        //when there are no negative cycles
        //else returns false
        //updates costs, and parents data which are given as parameters

        //costs --> cost of the shortest path between source node and all other nodes
        //parents --> the parent of target node in the shortest path from source to target

        //number of nodes
        int n = costs.length;
        Arrays.fill(costs, oo);
        Arrays.fill(parents, -1);
        costs[sourceNode] = 0;
        boolean shortestPathExists = true;
        //loop |E|-1 times
        for(int i = 0; i < this.E-1; i++){
            //for each node
            for(int j = 0; j < n; j++){
                final int node = j;
                //for each neighbor in the current node
                List<Pair> neighbours = graph.get(node);
                for(Pair element: neighbours){
                    int newCost = costs[node] + element.second;
                    if(newCost < costs[element.first]){
                        costs[element.first] = newCost;
                        parents[element.first] = node;
                    }
                }
            }
        }
        //last iteration to check for negative loops
        //for each node
        for(int j = 0; j < n; j++){
            final int node = j;
            for(Pair pair: graph.get(node)){
                int neighbor = pair.first;
                int weight = pair.second;
                int newCost = costs[node]+weight;
                if(newCost < costs[neighbor]){
                    shortestPathExists = false;
                    break;
                }
            }
            if(!shortestPathExists){break;}
        }
        return shortestPathExists;
    }
}


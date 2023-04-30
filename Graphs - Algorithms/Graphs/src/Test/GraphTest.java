package Test;

import com.company.Graph;
import org.junit.Assert;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    final int oo = 1000000000;
    String filePath = new File("").getAbsolutePath().concat("\\src\\Test\\Test Files\\");

    // test size (vertices - edges) on large graph
    @Test
    void size() {
        Graph g = new Graph(filePath.concat("\\Test1.txt"));
        assertEquals(18,g.V);
        assertEquals(58,g.E);
    }

    // test large graph on dijkstra
    @Test
    void apply_dijkstra1() {
        Graph g = new Graph(filePath.concat("\\Test1.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {0,oo,4,5,5,6,8,1,10,10,15,6,9,11,6,15,13,16};
        Assert.assertArrayEquals( result, ans );

    }

    // test small graph on dijkstra
    @Test
    void apply_dijkstra2() {
        Graph g = new Graph(filePath.concat("\\Test2.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {0,2,3,7,10};
        Assert.assertArrayEquals( result, ans );

    }

    // test small graph on dijkstra
    @Test
    void apply_dijkstra3() {
        Graph g = new Graph(filePath.concat("\\Test3.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {0,4,12,19,28,16,18,8,14};
        Assert.assertArrayEquals( result, ans );
    }


    // right answer for negative edges + cycles
    @Test
    void apply_dijkstra4() {
        Graph g = new Graph(filePath.concat("\\Test4.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {0,3,8,2,-4};
        Assert.assertArrayEquals( result, ans );
    }

    // wrong answer for negative edges
    @Test
    void apply_dijkstra5() {
        Graph g = new Graph(filePath.concat("\\Test5.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {0,2,7,4,9,6,8};
        Assert.assertArrayEquals( result, ans );
    }

    // linked list + reach node with 0 cost
    @Test
    void apply_dijkstra6() {
        Graph g = new Graph(filePath.concat("\\Test6.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {0,1,2,3,4,5,6,7,8,9,0};
        Assert.assertArrayEquals( result, ans );
    }

    // linked list + self loop ( + , - )
    @Test
    void apply_dijkstra7() {
        Graph g = new Graph(filePath.concat("\\Test7.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {0,1,2,3,4,5,6,7,8,9,0};
        Assert.assertArrayEquals( result, ans );
    }

    // different source node
    @Test
    void apply_dijkstra8() {
        Graph g = new Graph(filePath.concat("\\Test8.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(10,cost,parent);
        int[] ans = g.costDijkstra;
        int[] result = {1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 0, 1000000000, 1000000000, 1000000000, 2, 1000000000, 1000000000, 5, 6, 6};
        Assert.assertArrayEquals( result, ans );
    }















    @org.junit.jupiter.api.Test
    void floyed_warshal1() {
        Graph g = new Graph(filePath.concat("\\Test3.txt"));
        int[][] cost = g.costsMatrix;
        int[][] predecessors = new int[g.size()][g.size()];
        for(int i=0;i<g.size();i++) {
            for(int j=0;j<g.size();j++) predecessors[i][j] = oo;
        }
        g.floyed_warshal(predecessors,cost);
        int[][] ans = g.costFloyd;
        int[][] result = {
                {0, 4, 12, 19, 21, 11, 9, 8, 14},
                {4, 0, 8, 15, 17, 7, 11, 11, 2},
                {12, 8, 0, 7, 9, 5, 13, 10, 2},
                {19, 15, 7, 0, 2, 14, 20, 17, 9},
                {21, 17, 9, 2, 0, 16, 18, 19, 5},
                {11, 7, 5, 14, 16, 0, 6, 13, 9},
                {9, 11, 13, 20, 18, 6, 0, 1, 7},
                {8, 11, 10, 17, 19, 13, 1, 0, 6},
                {14, 2, 2, 9, 5, 9, 7, 6, 0}
        };
        for(int i=0;i<g.size();i++) {
            for(int j=0;j<g.size();j++) {
                System.out.println(cost[i][j]);;
            }
        }
//        Assert.assertArrayEquals( result, ans );
    }

    @org.junit.jupiter.api.Test
    void bfShortestPath() {
    }
}
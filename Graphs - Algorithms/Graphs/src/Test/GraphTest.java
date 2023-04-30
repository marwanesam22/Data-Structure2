package Test;

import com.company.Graph;
import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    final int oo = 1000000000;
    String filePath = new File("").getAbsolutePath().concat("\\src\\Test\\Test Files\\");

    // ----------------------SIZE TESTS----------------------------

    // test size (vertices - edges) on large graph
    @Test
    void size1() {
        Graph g = new Graph(filePath.concat("\\Test1.txt"));
        assertEquals(18,g.V);
        assertEquals(58,g.E);
    }

    // test size (vertices - edges) on large graph
    @Test
    void size2() {
        Graph g = new Graph(filePath.concat("\\Test8.txt"));
        assertEquals(20,g.graph.size());
        assertEquals(20,g.V);
        assertEquals(32,g.E);
    }

    // ----------------------SIZE TESTS----------------------------


    // ----------------------DIJKSTRA TESTS----------------------------

    // test large graph on dijkstra
    @Test
    void apply_dijkstra1() {
        Graph g = new Graph(filePath.concat("\\Test1.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {0,oo,4,5,5,6,8,1,10,10,15,6,9,11,6,15,13,16};
        assertArrayEquals( result, cost );

    }

    // test small graph on dijkstra
    @Test
    void apply_dijkstra2() {
        Graph g = new Graph(filePath.concat("\\Test2.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {0,2,3,7,10};
        assertArrayEquals( result, cost );

    }

    // test small graph on dijkstra
    @Test
    void apply_dijkstra3() {
        Graph g = new Graph(filePath.concat("\\Test3.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {0,4,12,19,28,16,18,8,14};
        assertArrayEquals( result, cost );
    }


    // right answer for negative edges + cycles
    @Test
    void apply_dijkstra4() {
        Graph g = new Graph(filePath.concat("\\Test4.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {0,3,8,2,-4};
        assertArrayEquals( result, cost );
    }

    // wrong answer for negative edges
    @Test
    void apply_dijkstra5() {
        Graph g = new Graph(filePath.concat("\\Test5.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {0,2,7,4,9,6,8};
        assertArrayEquals( result, cost );
    }

    // linked list + reach node with 0 cost
    @Test
    void apply_dijkstra6() {
        Graph g = new Graph(filePath.concat("\\Test6.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {0,1,2,3,4,5,6,7,8,9,0};
        assertArrayEquals( result, cost );
    }

    // linked list + self loop ( + , - )
    @Test
    void apply_dijkstra7() {
        Graph g = new Graph(filePath.concat("\\Test7.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {0,1,2,3,4,5,6,7,8,9,0};
        assertArrayEquals( result, cost );
    }

    // different source node
    @Test
    void apply_dijkstra8() {
        Graph g = new Graph(filePath.concat("\\Test8.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(10,cost,parent);
        int[] result = {oo, oo, oo, oo, oo, oo, oo, oo, oo, oo, 0, oo, oo, oo, 2, oo, oo, 5, 6, 6};
        assertArrayEquals(result, cost);
    }

    // Test parent array
    @Test
    void apply_dijkstra9() {
        Graph g = new Graph(filePath.concat("\\Test3.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
        int[] result = {-1,0,1,2,3,2,5,0,2};
        assertArrayEquals( result, parent );
    }

    // ----------------------DIJKSTRA TESTS----------------------------

    // ----------------------BELLMAN-FORD TESTS----------------------------

    @Test
    void bellmanFord1() {
        Graph g = new Graph(filePath.concat("\\Test1.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,oo,4,5,5,6,8,1,10,10,15,6,9,11,6,15,13,16};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    @Test
    void bellmanFord2() {
        Graph g = new Graph(filePath.concat("\\Test2.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,2,3,7,10};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    @Test
    void bellmanFord3() {
        Graph g = new Graph(filePath.concat("\\Test3.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,4,12,19,28,16,18,8,14};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    @Test
    void bellmanFord4() {
        Graph g = new Graph(filePath.concat("\\Test4.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,3,8,2,-4};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    @Test
    void bellmanFord5() {
        Graph g = new Graph(filePath.concat("\\Test5.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,2,7,3,9,5,2};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    @Test
    void bellmanFord6() {
        Graph g = new Graph(filePath.concat("\\Test6.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,1,2,3,4,5,6,7,8,9,0};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    @Test
    void bellmanFord7() {
        Graph g = new Graph(filePath.concat("\\Test7.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        assertFalse(negativeCycle);
    }

    @Test
    void bellmanFord8() {
        Graph g = new Graph(filePath.concat("\\Test8.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(10,cost,parent);
        int[] result = {oo, oo, oo, oo, oo, oo, oo, oo, oo, oo, 0, oo, oo, oo, 2, oo, oo, 5, 6, 6};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    @Test
    void bellmanFord9() {
        Graph g = new Graph(filePath.concat("\\Test3.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {-1,0,1,2,3,2,5,0,2};
        assertArrayEquals( result, parent );
        assertTrue(negativeCycle);
    }

    // ----------------------BELLMAN-FORD TESTS----------------------------


}
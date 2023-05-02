package Test;

import com.company.Graph;
import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    final int oo = 1000000000;
    String filePath = new File("").getAbsolutePath().concat("\\src\\Test\\Test Files\\");
    String timePath = new File("").getAbsolutePath().concat("\\src\\Test\\Test Files\\Time Test\\");

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

    // test large graph on dijkstra
    @Test
    void bellmanFord1() {
        Graph g = new Graph(filePath.concat("\\Test1.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,oo,4,5,5,6,8,1,10,10,15,6,9,11,6,15,13,16};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // test small graph on dijkstra
    @Test
    void bellmanFord2() {
        Graph g = new Graph(filePath.concat("\\Test2.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,2,3,7,10};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // test small graph on dijkstra
    @Test
    void bellmanFord3() {
        Graph g = new Graph(filePath.concat("\\Test3.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,4,12,19,28,16,18,8,14};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // right answer for negative edges + cycles
    @Test
    void bellmanFord4() {
        Graph g = new Graph(filePath.concat("\\Test4.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,3,8,2,-4};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // right answer for negative edges
    @Test
    void bellmanFord5() {
        Graph g = new Graph(filePath.concat("\\Test5.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,2,7,3,9,5,2};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // linked list + reach node with 0 cost
    @Test
    void bellmanFord6() {
        Graph g = new Graph(filePath.concat("\\Test6.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        int[] result = {0,1,2,3,4,5,6,7,8,9,0};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // linked list + self loop ( + , - ) - negative cycle
    @Test
    void bellmanFord7() {
        Graph g = new Graph(filePath.concat("\\Test7.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(0,cost,parent);
        assertFalse(negativeCycle);
    }

    // different source node
    @Test
    void bellmanFord8() {
        Graph g = new Graph(filePath.concat("\\Test8.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        boolean negativeCycle = g.bfShortestPath(10,cost,parent);
        int[] result = {oo, oo, oo, oo, oo, oo, oo, oo, oo, oo, 0, oo, oo, oo, 2, oo, oo, 5, 6, 6};
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // Test parent array
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

    // ----------------------FLOYD-WARSHALL TESTS----------------------------

    // large graph with only positive edges - test cost
    @Test
    void floydWarshall1() {
        Graph g = new Graph(filePath.concat("\\Test9.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        boolean negativeCycle = g.floyed_warshal(predecessors,cost);
        int[][] result = {
                {0,4,11,oo,13,7,9,oo},
                {1,0,7,oo,9,3,5,oo},
                {oo,oo,0,oo,2,9,oo,oo},
                {10,9,16,0,18,8,11,3},
                {oo,oo,1,oo,0,10,oo,oo},
                {oo,oo,oo,oo,oo,0,oo,oo},
                {oo,oo,8,oo,10,17,0,oo},
                {oo,oo,16,oo,18,5,8,0}
        };
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // large graph with only positive edges - test predecessors
    @Test
    void floydWarshall2() {
        Graph g = new Graph(filePath.concat("\\Test9.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        boolean negativeCycle = g.floyed_warshal(predecessors,cost);
        int[][] result = {
                {-1, 0, 1, -1, 2, 1, 1, -1,},
                {1, -1, 1, -1, 2, 1, 1, -1,},
                {-1, -1, -1, -1, 2, 2, -1, -1},
                {1, 3, 1, -1, 2, 3, 7, 3},
                {-1,-1,4,-1,-1,2,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,6,-1,2,2,-1,-1},
                {-1,-1,6,-1,2,7,7,-1}
        };
        assertArrayEquals( result, predecessors );
        assertTrue(negativeCycle);
    }

    // graph contain negative edges
    @Test
    void floydWarshall3() {
        Graph g = new Graph(filePath.concat("\\Test4.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        boolean negativeCycle = g.floyed_warshal(predecessors,cost);
        int[][] result = {
                {0,3,8,2,-4},
                {3,0,11,1,-1},
                {-3,0,0,-5,-7},
                {2,5,10,0,-2},
                {8,11,16,6,0}
        };
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // graph contain negative cycle
    @Test
    void floydWarshall4() {
        Graph g = new Graph(filePath.concat("\\Test7.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        boolean negativeCycle = g.floyed_warshal(predecessors,cost);
        assertFalse(negativeCycle);
    }

    @Test
    void floydWarshall5() {
        Graph g = new Graph(filePath.concat("\\Test10.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        boolean negativeCycle = g.floyed_warshal(predecessors,cost);
        int[][] result = {
                {0,3,7,5},
                {2,0,6,4},
                {3,1,0,5},
                {5,3,2,0}
        };
        assertArrayEquals( result, cost );
        assertTrue(negativeCycle);
    }

    // ----------------------FLOYD-WARSHALL TESTS----------------------------

    // -------------------------Time TESTS----------------------------------

    @Test
    void dijkstra10_45() {
        Graph g = new Graph(timePath.concat("\\graph1045.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman10_45() {
        Graph g = new Graph(timePath.concat("\\graph1045.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd10_45() {
        Graph g = new Graph(timePath.concat("\\graph1045.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }

    @Test
    void dijkstra100_91() {
        Graph g = new Graph(timePath.concat("\\graph10091.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman100_91() {
        Graph g = new Graph(timePath.concat("\\graph10091.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd100_91() {
        Graph g = new Graph(timePath.concat("\\graph10091.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }

    @Test
    void dijkstra100_990() {
        Graph g = new Graph(timePath.concat("\\graph100990.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman100_990() {
        Graph g = new Graph(timePath.concat("\\graph100990.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd100_990() {
        Graph g = new Graph(timePath.concat("\\graph100990.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }

    @Test
    void dijkstra100_1980() {
        Graph g = new Graph(timePath.concat("\\graph1001980.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman100_1980() {
        Graph g = new Graph(timePath.concat("\\graph1001980.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd100_1980() {
        Graph g = new Graph(timePath.concat("\\graph1001980.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }
    @Test
    void dijkstra100_3960() {
        Graph g = new Graph(timePath.concat("\\graph1003960.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman100_3960() {
        Graph g = new Graph(timePath.concat("\\graph1003960.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd100_3960() {
        Graph g = new Graph(timePath.concat("\\graph1003960.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }
    @Test
    void dijkstra100_5015() {
        Graph g = new Graph(timePath.concat("\\graph1005015.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman100_5015() {
        Graph g = new Graph(timePath.concat("\\graph1005015.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd100_5015() {
        Graph g = new Graph(timePath.concat("\\graph1005015.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }
    @Test
    void dijkstra500_2495() {
        Graph g = new Graph(timePath.concat("\\graph5002495.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman500_2495() {
        Graph g = new Graph(timePath.concat("\\graph5002495.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd500_2495() {
        Graph g = new Graph(timePath.concat("\\graph5002495.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }
    @Test
    void dijkstra500_249500() {
        Graph g = new Graph(timePath.concat("\\graph500249500.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman500_249500() {
        Graph g = new Graph(timePath.concat("\\graph500249500.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd500_249500() {
        Graph g = new Graph(timePath.concat("\\graph500249500.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }
    @Test
    void dijkstra1000_99990() {
        Graph g = new Graph(timePath.concat("\\graph100099990.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
    @Test
    void bellman1000_99990() {
        Graph g = new Graph(timePath.concat("\\graph100099990.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.bfShortestPath(0,cost,parent);
    }
    @Test
    void floyd1000_99990() {
        Graph g = new Graph(timePath.concat("\\graph100099990.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }

    @Test
    void dijkstra1000_999000() {
        Graph g = new Graph(timePath.concat("\\graph1000999000.txt"));
        int[] cost = new int[g.size()],parent = new int[g.size()];
        g.apply_dijkstra(0,cost,parent);
    }
    // 17 218
//    @Test
//    void bellman1000_999000() {
//        Graph g = new Graph(timePath.concat("\\graph1000999000.txt"));
//        int[] cost = new int[g.size()],parent = new int[g.size()];
//        g.bfShortestPath(0,cost,parent);
//    }
    @Test
    void floyd1000_999000() {
        Graph g = new Graph(timePath.concat("\\graph1000999000.txt"));
        int[][] cost = new int[g.size()][g.size()];
        int[][] predecessors = new int[g.size()][g.size()];
        g.floyed_warshal(predecessors,cost);
    }



    // -------------------------Time TESTS----------------------------------




}
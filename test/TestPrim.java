import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class TestPrim {

  Graph exampleGraph;
  Graph exampleGraph2;

  @Before
  public void initializeData() {
    exampleGraph = new Graph(7);
    exampleGraph.addEdge(0, 1, 4);
    exampleGraph.addEdge(0, 2, 26);
    exampleGraph.addEdge(0, 3, 14);
    exampleGraph.addEdge(1, 3, 12);
    exampleGraph.addEdge(1, 4, 18);
    exampleGraph.addEdge(2, 3, 30);
    exampleGraph.addEdge(2, 5, 16);
    exampleGraph.addEdge(3, 4, 2);
    exampleGraph.addEdge(3, 5, 3);
    exampleGraph.addEdge(4, 5, 10);
    exampleGraph.addEdge(4, 6, 8);
    exampleGraph.addEdge(5, 6, 5);

    exampleGraph2 = new Graph(8);
    exampleGraph2.addEdge(0, 1, 6);
    exampleGraph2.addEdge(0, 5, 12);
    exampleGraph2.addEdge(1, 5, 5);
    exampleGraph2.addEdge(1, 2, 14);
    exampleGraph2.addEdge(1, 3, 8);
    exampleGraph2.addEdge(2, 3, 3);
    exampleGraph2.addEdge(3, 4, 10);
    exampleGraph2.addEdge(5, 4, 7);
    exampleGraph2.addEdge(5, 6, 9);
    exampleGraph2.addEdge(4, 7, 15);
  }

  // tests the prim method
  @Test
  public void testPrim() {
    assertEquals(new ArrayList<Pair<Integer, Integer>>(Arrays
        .asList(new Pair<Integer, Integer>(1, 0), new Pair<Integer, Integer>(2, 5),
            new Pair<Integer, Integer>(3, 1),
            new Pair<Integer, Integer>(4, 3), new Pair<Integer, Integer>(5, 3),
            new Pair<Integer, Integer>(6, 5))), Main.prim(exampleGraph));

    assertEquals(new ArrayList<Pair<Integer, Integer>>(Arrays
            .asList(new Pair<Integer, Integer>(1, 0), new Pair<Integer, Integer>(2, 3),
                new Pair<Integer, Integer>(3, 1),
                new Pair<Integer, Integer>(4, 5), new Pair<Integer, Integer>(5, 1),
                new Pair<Integer, Integer>(6, 5), new Pair<Integer, Integer>(7, 4))),
        Main.prim(exampleGraph2));
  }
}

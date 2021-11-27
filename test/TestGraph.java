import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class TestGraph {

  Graph exampleGraph;

  @Before
  public void initializeData() {
    exampleGraph = new Graph();
  }

  // tests the addNode method
  @Test
  public void testAddNode() {
    assertEquals(0, exampleGraph.getNumberOfNodes());
    exampleGraph.addNode();
    assertEquals(1, exampleGraph.getNumberOfNodes());
    exampleGraph.addNode();
    exampleGraph.addNode();
    exampleGraph.addNode();
    assertEquals(4, exampleGraph.getNumberOfNodes());
  }

  // ensures that the addNodes method throws an IllegalArgumentException if the number of nodes
  // given is not positive
  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeNumberOfNodes() {
    exampleGraph.addNodes(-1);
  }

  // tests the addNodes method
  @Test
  public void testAddNodes() {
    assertEquals(0, exampleGraph.getNumberOfNodes());
    exampleGraph.addNodes(3);
    assertEquals(3, exampleGraph.getNumberOfNodes());
  }

  // ensures that the addEdge method throws an IllegalArgumentException if the startingNode is
  // negative
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStartingNode() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(-1, 2, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException if the startingNode is out
  // of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsStartingNode() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(4, 1, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException if the destinationNode is
  // negative
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDestinationNode() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(2, -1, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException if the destinationNode is
  // out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsDestinationNode() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(1, 4, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException if a self-loop is added
  @Test(expected = IllegalArgumentException.class)
  public void testAddSelfLoop() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(1, 1, 10);
  }

  // ensures that the addEdge method throws an IllegalArgumentException if there already exists
  // an edge between startingNode and destinationNode
  @Test(expected = IllegalArgumentException.class)
  public void testAddExistingEdge() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(1, 2, 20);
    assertEquals(1, exampleGraph.getNumberOfEdges());
    exampleGraph.addEdge(2, 1, 10); // reminder that edges are undirected so (2, 1) == (1, 2)
  }

  // tests the addEdge method
  @Test
  public void testAddEdge() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(1, 2, 20);
    assertEquals(1, exampleGraph.getNumberOfEdges());
    assertEquals(
        new ArrayList<Pair<Integer, Integer>>(Arrays.asList(new Pair<Integer, Integer>(2, 20))),
        exampleGraph.getEdgesFrom(1));
    assertEquals(
        new ArrayList<Pair<Integer, Integer>>(Arrays.asList(new Pair<Integer, Integer>(1, 20))),
        exampleGraph.getEdgesFrom(2));
  }
}

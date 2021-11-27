import java.util.ArrayList;
import java.util.List;

public class Main {

  public static List<Pair<Integer, Integer>> prim(Graph inputGraph)
      throws IllegalArgumentException {
    InputValidation.ensureNotNull(inputGraph);

    int startingNode = 0;
    // contains nodes not in S
    PriorityQueue queue = new PriorityQueue(inputGraph.getNumberOfNodes());
    List<Integer> cameFrom = new ArrayList<Integer>(inputGraph.getNumberOfNodes() - 1);

    // initialization of priority queue and parent pointer array
    for (int currentNode = 0; currentNode < inputGraph.getNumberOfNodes(); currentNode++) {
      if (currentNode == startingNode) {
        queue.insert(startingNode, 0);
      } else {
        queue.insert(currentNode, 9999);
      }

      cameFrom.add(-1);
    }

    while (queue.size() > 0) {
      Pair<Integer, Integer> closestNodeAndEdgeWeight = queue.extractMin();
      int closestNode = closestNodeAndEdgeWeight.getFirst();
      List<Pair<Integer, Integer>> neighbors = inputGraph.getEdgesFrom(closestNode);
      for (int index = 0; index < neighbors.size(); index++) {
        int currentNeighbor = neighbors.get(index).getFirst();
        int currentEdgeWeight = neighbors.get(index).getSecond();
        if (queue.contains(currentNeighbor) && currentEdgeWeight < queue.lookup(currentNeighbor)) {
          queue.decreaseKey(currentNeighbor, currentEdgeWeight);
          cameFrom.set(currentNeighbor, closestNode);
        }
      }
    }

    // construction of the minimum spanning tree
    List<Pair<Integer, Integer>> MST = new ArrayList<Pair<Integer, Integer>>(
        inputGraph.getNumberOfNodes() - 1);
    for (int currentNode = 0; currentNode < inputGraph.getNumberOfNodes(); currentNode++) {
      if (currentNode == startingNode) {
        continue;
      } else {
        MST.add(new Pair<Integer, Integer>(currentNode, cameFrom.get(currentNode)));
      }
    }
    return MST;
  }

  public static void main(String[] args) {
    Graph exampleGraph = new Graph(7);
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

    List<Pair<Integer, Integer>> MST = prim(exampleGraph);
    System.out.println(MST);
  }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a priority queue in which the keys are nodes in a graph and the values are the minimum
 * weight edges to a node in a cut S
 */
public class PriorityQueue {

  Map<Integer, Integer> nodeIndices; // maps from node to index in array
  Map<Integer, Integer> reverseIndices; // maps from index in array to node
  List<Integer> nodeValues; // contains the distances for each node

  /**
   * Constructs a new {@code PriorityQueue} object with space allocated for {@code initialCapacity}
   * elements. Elements can be inserted or extracted as needed, however
   *
   * @param initialCapacity The initial capacity of the priority queue
   * @throws IllegalArgumentException if the initial capacity is negative
   */
  public PriorityQueue(int initialCapacity) throws IllegalArgumentException {
    InputValidation.ensureGreaterThan(initialCapacity, -1, "Initial capacity cannot be negative");
    this.nodeIndices = new HashMap<Integer, Integer>(initialCapacity);
    this.reverseIndices = new HashMap<Integer, Integer>(initialCapacity);
    this.nodeValues = new ArrayList<Integer>(initialCapacity);
  }

  /**
   * Checks whether the given node/key is contained in the priority queue
   *
   * @param node The node to be checked
   * @return True if the node/key is contained in the priority queue, False otherwise
   */
  public boolean contains(int node) {
    return this.nodeIndices.containsKey(node);
  }

  /**
   * Looks up the value associated with the given node/key in the priority queue
   *
   * @param node The node/key whose value we want
   * @return The value associated with the given node
   * @throws IllegalArgumentException if the node/key is not contained in the queue
   */
  public int lookup(int node) throws IllegalArgumentException {
    if (!(this.contains(node))) {
      throw new IllegalArgumentException("Node is not contained in the queue");
    }

    int nodeIndex = this.nodeIndices.get(node);
    int nodeValue = this.nodeValues.get(nodeIndex);
    return nodeValue;
  }

  /**
   * Inserts a new key-value pair into the priority queue at the appropriate position
   *
   * @param node  The node/key of the pair
   * @param value The value associated with the node/key
   * @throws IllegalArgumentException if the node/key is already contained in the queue
   */
  public void insert(int node, int value) throws IllegalArgumentException {
    if (this.contains(node)) {
      throw new IllegalArgumentException("Node is already contained in the queue");
    }

    this.nodeValues.add(value);
    if (this.nodeValues.size() == 1) {
      this.nodeIndices.put(node, 0);
      this.reverseIndices.put(0, node);
    } else {
      this.upheap(node, this.nodeValues.size() - 1, value);
    }
  }

  private void upheap(int node, int currentNodeIndex, int value) {
    if (currentNodeIndex == 0) {
      return;
    }

    // we assume that there is at least one other node in the graph in addition to the value to be upheaped
    int parentIndex;
    if (currentNodeIndex % 2 == 0) {
      parentIndex = (currentNodeIndex / 2) - 1;
    } else {
      parentIndex = (int) Math.floor(currentNodeIndex / 2);
    }
    int parentValue = this.nodeValues.get(parentIndex);
    int parentNode = this.reverseIndices.get(parentIndex);

    while (parentValue > value) {
      // swapping the two values in the values array
      Collections.swap(this.nodeValues, parentIndex, currentNodeIndex);

      // fixing the index the parent node points to
      this.nodeIndices.put(parentNode, currentNodeIndex);
      this.reverseIndices.put(currentNodeIndex, parentNode);

      // calculating the new indices
      currentNodeIndex = parentIndex;
      if (currentNodeIndex == 0) {
        break;
      }

      if (currentNodeIndex % 2 == 0) {
        parentIndex = (currentNodeIndex / 2) - 1;
      } else {
        parentIndex = (int) Math.floor(currentNodeIndex / 2);
      }
      parentValue = this.nodeValues.get(parentIndex);
      parentNode = this.reverseIndices.get(parentIndex);
    }

    // assigning the index the node now points to
    this.nodeIndices.put(node, currentNodeIndex);
    this.reverseIndices.put(currentNodeIndex, node);
  }

  /**
   * Extracts the minimum value in the priority queue
   *
   * @return The minimum value in the queue
   * @throws IllegalArgumentException if the queue is empty
   */
  public Pair<Integer, Integer> extractMin() throws IllegalArgumentException {
    if (this.nodeValues.size() == 0) {
      throw new IllegalArgumentException("Cannot extract from an empty queue");
    }
    // removing root connection
    int minimumNode = this.reverseIndices.get(0);
    int minimumDistance = this.nodeValues.get(0);
    this.nodeIndices.remove(this.reverseIndices.remove(0));
    if (this.nodeValues.size() == 1) {
      this.nodeValues.remove(0);
      return new Pair<Integer, Integer>(minimumNode, minimumDistance);
    }
    // grabbing last node, severing connection, placing at front of values array
    int parentDistance = this.nodeValues.get(this.nodeValues.size() - 1);
    int parentNode = this.reverseIndices.get(this.nodeValues.size() - 1);
    this.reverseIndices.remove(this.nodeIndices.remove(parentNode));
    this.nodeValues.set(0, this.nodeValues.remove(this.nodeValues.size() - 1));

    // calculating indices
    int parentIndex = 0;
    int leftChildIndex = 1;
    int rightChildIndex = 2;
    while (((rightChildIndex < this.nodeValues.size()) && (parentDistance > Math
        .min(this.nodeValues.get(leftChildIndex), this.nodeValues.get(rightChildIndex)))) || (
        (leftChildIndex < this.nodeValues.size())
            && (parentDistance > this.nodeValues.get(leftChildIndex)))) {
      if ((rightChildIndex < this.nodeValues.size()) && (this.nodeValues.get(rightChildIndex)
          < this.nodeValues.get(leftChildIndex))) {
        // parent must be swapped with right child
        Collections.swap(this.nodeValues, parentIndex, rightChildIndex);

        // fixing the mapping of the right child
        int rightChildNode = this.reverseIndices.get(rightChildIndex);
        this.reverseIndices.remove(this.nodeIndices.remove(rightChildNode));
        this.nodeIndices.put(rightChildNode, parentIndex);
        this.reverseIndices.put(parentIndex, rightChildNode);

        // calculating new indices and values
        parentIndex = rightChildIndex;
      } else {
        // parent must be swapped with left child
        Collections.swap(this.nodeValues, parentIndex, leftChildIndex);

        // fixing the mapping of the left child
        int leftChildNode = this.reverseIndices.get(leftChildIndex);
        this.reverseIndices.remove(this.nodeIndices.remove(leftChildNode));
        this.nodeIndices.put(leftChildNode, parentIndex);
        this.reverseIndices.put(parentIndex, leftChildNode);

        // calculating new indices and values
        parentIndex = leftChildIndex;
      }

      parentDistance = this.nodeValues.get(parentIndex);
      leftChildIndex = (2 * parentIndex) + 1;
      rightChildIndex = (2 * parentIndex) + 2;
    }

    // fixing the mapping of the original parent node
    this.nodeIndices.put(parentNode, parentIndex);
    this.reverseIndices.put(parentIndex, parentNode);
    return new Pair<Integer, Integer>(minimumNode, minimumDistance);
  }

  /**
   * Decreases the value associated with the given {@code node} to the given {@code value}
   *
   * @param node  The node whose value we want to change
   * @param value The new value of the node
   * @throws IllegalArgumentException if the new value is not strictly less than the current value
   *                                  associated with the node or if the node is not contained in
   *                                  the queue
   */
  public void decreaseKey(int node, int value) throws IllegalArgumentException {
    if (!this.contains(node)) {
      throw new IllegalArgumentException("Node is not contained in the graph");
    }
    InputValidation.ensureLessThan(value, this.nodeValues.get(this.nodeIndices.get(node)),
        "New value must be strictly less than the current value");

    this.nodeValues.set(this.nodeIndices.get(node), value);
    if (this.nodeValues.size() > 1) {
      this.upheap(node, this.nodeIndices.get(node), value);
    }
  }

  /**
   * Obtains a copy of the list of values in the queue
   *
   * @return a copy of the list of values in the queue
   */
  public List<Integer> getValues() {
    List<Integer> valuesCopy = new ArrayList<Integer>(this.nodeValues.size());
    for (int index = 0; index < this.nodeValues.size(); index++) {
      valuesCopy.add(this.nodeValues.get(index));
    }
    return valuesCopy;
  }

  public Map<Integer, Integer> getMappings() {
    return this.getMappingsHelper(this.nodeIndices);
  }

  public Map<Integer, Integer> getReverseMappings() {
    return this.getMappingsHelper(this.reverseIndices);
  }

  private Map<Integer, Integer> getMappingsHelper(Map<Integer, Integer> original) {
    Map<Integer, Integer> mappingsCopy = new HashMap<Integer, Integer>(original.size());
    for (Integer key : original.keySet()) {
      int value = original.get(key);
      mappingsCopy.put(key, value);
    }
    return mappingsCopy;
  }

  /**
   * Returns the size of the queue
   *
   * @return the size of the queue
   */
  public int size() {
    return this.nodeValues.size();
  }
}

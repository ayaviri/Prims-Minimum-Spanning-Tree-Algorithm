import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class TestPriorityQueue {

  PriorityQueue exampleQueue;

  @Before
  public void initializeData() {
    exampleQueue = new PriorityQueue(0);
  }

  // tests the contains method
  @Test
  public void testContains() {
    assertFalse(exampleQueue.contains(3));
    exampleQueue.insert(3, 10);
    assertTrue(exampleQueue.contains(3));
    exampleQueue.insert(5, 3);
    assertTrue(exampleQueue.contains(5));
    assertTrue(exampleQueue.contains(3));
  }

  // ensures that the lookup method throws an IllegalArgumentException if the node is not contained
  // in the queue
  @Test(expected = IllegalArgumentException.class)
  public void testLookupNodeNotInQueue() {
    exampleQueue.insert(3, 10);
    exampleQueue.lookup(1);
  }

  // tests the lookup method
  @Test
  public void testLookup() {
    exampleQueue.insert(3, 10);
    exampleQueue.insert(5, 3);
    assertEquals(10, exampleQueue.lookup(3));
    assertEquals(3, exampleQueue.lookup(5));
  }

  // ensures that the insert method throws an IllegalArgumentException if the node is already
  // contained in the queue
  @Test(expected = IllegalArgumentException.class)
  public void testInsertNodeAlreadyInQueue() {
    exampleQueue.insert(3, 10);
    exampleQueue.insert(3, 5);
  }

  // tests the insert method
  @Test
  public void testInsert() {
    Map<Integer, Integer> testMap = new HashMap<Integer, Integer>(3);
    Map<Integer, Integer> reverseTestMap = new HashMap<Integer, Integer>(3);
    assertEquals(new ArrayList<Integer>(), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
    exampleQueue.insert(3, 10);
    testMap.put(3, 0);
    reverseTestMap.put(0, 3);
    assertEquals(new ArrayList<Integer>(Arrays.asList(10)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
    exampleQueue.insert(5, 4);
    testMap.put(3, 1);
    reverseTestMap.put(1, 3);
    testMap.put(5, 0);
    reverseTestMap.put(0, 5);
    assertEquals(new ArrayList<Integer>(Arrays.asList(4, 10)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
    exampleQueue.insert(4, 6);
    testMap.put(4, 2);
    reverseTestMap.put(2, 4);
    assertEquals(new ArrayList<Integer>(Arrays.asList(4, 10, 6)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
  }

  // ensures that the extractMin method throws an IllegalArgumentException when attempting to remove
  // from an empty queue
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveFromEmptyQueue() {
    exampleQueue.extractMin();
  }

  // tests the extractMin method
  @Test
  public void testExtractMin() {
    Map<Integer, Integer> testMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> reverseTestMap = new HashMap<Integer, Integer>();
    exampleQueue.insert(5, 10);
    testMap.put(5, 0);
    reverseTestMap.put(0, 5);
    assertEquals(new ArrayList<Integer>(Arrays.asList(10)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
    exampleQueue.insert(2, 7);
    testMap.put(2, 0);
    reverseTestMap.put(0, 2);
    testMap.put(5, 1);
    reverseTestMap.put(1, 5);
    assertEquals(new ArrayList<Integer>(Arrays.asList(7, 10)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
    exampleQueue.insert(8, 6);
    testMap.put(8, 0);
    reverseTestMap.put(0, 8);
    testMap.put(5, 1);
    reverseTestMap.put(1, 5);
    testMap.put(2, 2);
    reverseTestMap.put(2, 2);
    assertEquals(new ArrayList<Integer>(Arrays.asList(6, 10, 7)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
    exampleQueue.insert(9, 11);
    testMap.put(9, 3);
    reverseTestMap.put(3, 9);
    assertEquals(new ArrayList<Integer>(Arrays.asList(6, 10, 7, 11)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());

    // onto the extractions
    reverseTestMap.remove(testMap.remove(8));
    reverseTestMap.remove(testMap.remove(2));
    reverseTestMap.remove(testMap.remove(9));
    reverseTestMap.remove(testMap.remove(5));
    testMap.put(2, 0);
    reverseTestMap.put(0, 2);
    testMap.put(5, 1);
    reverseTestMap.put(1, 5);
    testMap.put(9, 2);
    reverseTestMap.put(2, 9);
    assertEquals(new Pair<Integer, Integer>(8, 6), exampleQueue.extractMin());
    assertEquals(new ArrayList<Integer>(Arrays.asList(7, 10, 11)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());

    reverseTestMap.remove(testMap.remove(2));
    reverseTestMap.remove(testMap.remove(9));
    reverseTestMap.remove(testMap.remove(5));
    testMap.put(5, 0);
    reverseTestMap.put(0, 5);
    testMap.put(9, 1);
    reverseTestMap.put(1, 9);
    assertEquals(new Pair<Integer, Integer>(2, 7), exampleQueue.extractMin());
    assertEquals(new ArrayList<Integer>(Arrays.asList(10, 11)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());

    reverseTestMap.remove(testMap.remove(9));
    reverseTestMap.remove(testMap.remove(5));
    testMap.put(9, 0);
    reverseTestMap.put(0, 9);
    assertEquals(new Pair<Integer, Integer>(5, 10), exampleQueue.extractMin());
    assertEquals(new ArrayList<Integer>(Arrays.asList(11)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());

    reverseTestMap.remove(testMap.remove(9));
    reverseTestMap.remove(testMap.remove(5));
    assertEquals(new Pair<Integer, Integer>(9, 11), exampleQueue.extractMin());
    assertEquals(new ArrayList<Integer>(), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
  }

  // ensures that the decreaseKey method throws an IllegalArgumentException if the node is not
  // contained in the queue

  @Test(expected = IllegalArgumentException.class)
  public void testDecreaseNodeNotInQueue() {
    exampleQueue.insert(1, 5);
    exampleQueue.decreaseKey(2, 10);
  }

  // ensures that the decreaseKey method throws an IllegalArgumentException if the new value is
  // not strictly less than the current value in the queue for the given node
  @Test(expected = IllegalArgumentException.class)
  public void testDecreaseValueNotLessThanOriginal() {
    exampleQueue.insert(1, 5);
    exampleQueue.decreaseKey(1, 6);
  }

  // tests the decreaseKey method
  @Test
  public void testDecreaseKey() {
    exampleQueue.insert(1, 5);
    exampleQueue.insert(2, 6);
    exampleQueue.insert(3, 7);
    exampleQueue.insert(4, 8);
    Map<Integer, Integer> testMap = new HashMap<Integer, Integer>(4);
    Map<Integer, Integer> reverseTestMap = new HashMap<Integer, Integer>(4);
    testMap.put(1, 0);
    reverseTestMap.put(0, 1);
    testMap.put(2, 1);
    reverseTestMap.put(1, 2);
    testMap.put(3, 2);
    reverseTestMap.put(2, 3);
    testMap.put(4, 3);
    reverseTestMap.put(3, 4);
    assertEquals(new ArrayList<Integer>(Arrays.asList(5, 6, 7, 8)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
    exampleQueue.decreaseKey(4, 4);
    testMap.remove(1);
    testMap.remove(2);
    testMap.remove(3);
    testMap.remove(4);
    reverseTestMap.remove(0);
    reverseTestMap.remove(1);
    reverseTestMap.remove(2);
    reverseTestMap.remove(3);
    testMap.put(4, 0);
    reverseTestMap.put(0, 4);
    testMap.put(1, 1);
    reverseTestMap.put(1, 1);
    testMap.put(2, 3);
    reverseTestMap.put(3, 2);
    testMap.put(3, 2);
    reverseTestMap.put(2, 3);
    assertEquals(new ArrayList<Integer>(Arrays.asList(4, 5, 7, 6)), exampleQueue.getValues());
    assertEquals(testMap, exampleQueue.getMappings());
    assertEquals(reverseTestMap, exampleQueue.getReverseMappings());
  }
}

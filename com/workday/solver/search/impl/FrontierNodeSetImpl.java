package com.workday.solver.search.impl;

import com.workday.solver.search.*;
import java.util.*;

public class FrontierNodeSetImpl implements FrontierNodeSet {
  Queue<SearchNode> queue;

  FrontierNodeSetImpl() {
    queue = new LinkedList<>();
  }
  /**
   * 
   * 
   * 
   * @return True if no more node to explore.
   * 
   */

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  /**
   * 
   * Add one SearchNode to the collection.
   * 
   * @param aNode - a node to be added
   * 
   */

  public void add(SearchNode aNode) {
    queue.add(aNode);
  }

  /**
   * 
   * Add a list of SearchNodes to the collection.
   * 
   * @param nodeList - a list of nodes to be added
   * 
   */

  public void add(List<? extends SearchNode> nodeList) {
    queue.addAll(nodeList);
  }

  /**
   * 
   * Removed one node from the collection to further evaluate/explore.
   * 
   * If the collection is sorted, it will remove node according to
   * 
   * sorting order.
   * 
   * 
   * 
   * @return The next search node to evaluate/explore.
   * 
   */

  public SearchNode removeNextOne() {
    return queue.remove();
  }
}
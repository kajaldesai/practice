package com.workday.solver.search;

import java.util.List;

 

/**

 * A SearchNode represents one state of the state space we are searching.

 * The data needed in each node depends on the search strategy employed.

 * Immutable part of the data can be shared across multiple nodes.

 * 

 */

public interface SearchNode {

       /**

       * 

       * @return True if the current node represents the goal state.

       */

       boolean isGoal();

      

       /**

       * From the current state, take each allowed action to generate a set of 

       * child states for further explore.

       * 

       * @return A list of child nodes

       */

       List<? extends SearchNode> expand();

}
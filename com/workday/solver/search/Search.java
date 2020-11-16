package com.workday.solver.search;

 

/**

 * A Generic State Space Search framework, allow one to implement a variety of

 * search strategies for any chosen domain.

 * 

 */

public abstract class Search {

       protected SearchNode root;

       protected FrontierNodeSet candidateList;

       protected SearchNode solutionLeafNode;

      

       public enum SolveStatus {

              SOLUTION_FOUND, NO_SOLUTION, TIME_OUT, REACHED_RESOURCE_LIMIT

       }

      

       protected Search() {

              this.solutionLeafNode = null;

       }

      

       /**

       * 

       * @return The leaf solution node. It can be null if a solution has not

       *     been found yet.

       */

       public SearchNode getSolutionNode() {

              return this.solutionLeafNode;

       }

      

       /**

       * Default implementation of a generate state space search. Many search

       * strategies can be achieved by varying the sorting order of the

       * "candidateList" or by implementing how global metrics is updated

       * and how a search branch is pruned.

       * 

       * @return SolveStatus.SOLUTION_FOUND when a solution is found

       *                  SolveStatus.NO_SOLUTION when there is no feasible solution

       *                  Other solve status if time out and resource limit is

       *                  implemented

       */

       public SolveStatus solve() {

              while (!candidateList.isEmpty()) {
                    System.out.println("Looping");
                     SearchNode theNode = candidateList.removeNextOne();

                     if (theNode.isGoal()) {

                           foundSolution(theNode);

                           return SolveStatus.SOLUTION_FOUND;

                     }

                     else {

                           updateGlobalMetrics(theNode);

                           if (!pruneNode(theNode)) {

                                  candidateList.add(theNode.expand());

                           }

                     }

              }

              return SolveStatus.NO_SOLUTION;

       }

      

       /**

       * Default doing nothing implementation of updating the global metrics after

       * one node is evaluated. Need to override this to implement A*, Alpha-Beta 

       * pruning, branch and bound, etc.

       * @param theNode - the node just evaluated with potential fresh metrics

       */

       public void updateGlobalMetrics(SearchNode theNode) {}

      

       /**

       * Default implementation of not pruning branch after a note evaluation.

       * @param theNode - the node just evaluated, potentially to be pruned

       * @return True if the branch starting at "theNode" is pruned, 

       *                  false otherwise.

       */

       public boolean pruneNode(SearchNode theNode) { return false; }

 

       /**

       * Default doing nothing implementation of the found solution method.

       * @param theNode - the node to be evaluated

       */

       public void foundSolution(SearchNode theNode) {
         System.out.println("Solution found:");
         System.out.println(theNode.toString());
         solutionLeafNode = theNode;
       }

}
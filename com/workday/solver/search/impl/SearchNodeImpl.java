package com.workday.solver.search.impl;
import com.workday.solver.search.*;
import java.util.*;

 

/**

 * A SearchNode represents one state of the state space we are searching.

 * The data needed in each node depends on the search strategy employed.

 * Immutable part of the data can be shared across multiple nodes.

 * 

 */

public class SearchNodeImpl implements SearchNode {
      private static final int DIMENSION = 9;
      int[][] sudoku;
      SearchNodeImpl(int[][] sudoku) {
        this.sudoku = sudoku;
      }
       /**

       * 

       * @return True if the current node represents the goal state.

       */

      public boolean isGoal() { 
        for (int r = 0; r < DIMENSION; r++) {
          for (int c = 0; c < DIMENSION; c++) {
            if (sudoku[r][c] == 0) return false;
          }
        }
        return true;
      }

      private int[][] generateNewState(int r, int c, int newValue) {
        int[][] copy = java.util.Arrays.stream(this.sudoku).map(el -> el.clone()).toArray($ -> this.sudoku.clone());
        return copy;
      }

      private boolean isNewStateValid(int r, int c, int newValue) {
        int origValue = sudoku[r][c];
        sudoku[r][c] = newValue;
        boolean valid = Validator.isValid(sudoku, r, c);
        sudoku[r][c] = origValue;
        return valid;
      }

       /**

       * From the current state, take each allowed action to generate a set of 

       * child states for further explore.

       * 

       * @return A list of child nodes

       */

      public List<? extends SearchNode> expand() { 
        List<SearchNodeImpl> elist = new ArrayList<>();
        for (int r = 0; r < DIMENSION; r++) {
          for (int c = 0; c < DIMENSION; c++) {
            if (sudoku[r][c] != 0) continue;
            for (int value = 1; value <= DIMENSION; value++) {
              if (isNewStateValid(r, c, value)){
                elist.add(new SearchNodeImpl(generateNewState(r, c, value)));
              }
            }
          }
        }
        return elist;
      }

    public String toString() {
      return sudoku.toString();
    }
}
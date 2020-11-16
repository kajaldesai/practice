package com.workday.solver.search.impl;

import com.workday.solver.search.*;

public class SearchImpl extends Search {
  public SearchImpl(int[][] sudoku) {
      this.candidateList = new FrontierNodeSetImpl();
      candidateList.add(new SearchNodeImpl(sudoku));
  }
}
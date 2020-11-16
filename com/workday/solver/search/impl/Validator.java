package com.workday.solver.search.impl;
import java.util.stream.*;

public class Validator {
      private static final int BOARD_SIZE = 9;
      private static final int SUBSECTION_SIZE = 9;
      private static final int BOARD_START_INDEX = 0;
      private static final int NO_VALUE = 0;

      public static boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column) 
                && subsectionConstraint(board, row, column));
      }

      private static boolean rowConstraint(int[][] board, int row) {
          boolean[] constraint = new boolean[BOARD_SIZE];
          return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
            .allMatch(column -> checkConstraint(board, row, constraint, column));
      }

      private static boolean columnConstraint(int[][] board, int column) {
          boolean[] constraint = new boolean[BOARD_SIZE];
          return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
            .allMatch(row -> checkConstraint(board, row, constraint, column));
      }

      private static boolean subsectionConstraint(int[][] board, int row, int column) {
          boolean[] constraint = new boolean[BOARD_SIZE];
          int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
          int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;
      
          int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
          int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;
      
          for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
              for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                  if (!checkConstraint(board, r, constraint, c)) return false;
              }
          }
          return true;
      }
      
      private static boolean checkConstraint(
        int[][] board, 
        int row, 
        boolean[] constraint, 
        int column) {
          if (board[row][column] != NO_VALUE) {
              if (!constraint[board[row][column] - 1]) {
                  constraint[board[row][column] - 1] = true;
              } else {
                  return false;
              }
          }
          return true;
      }
}
package io.etd;

public class Grid {
    private int gridSize;
    private final char[][] grid = new char[gridSize][gridSize];

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        for (int i = 0; i < gridSize; ++i) {
            for(int j = 0; j < gridSize; ++j) {
                grid[i][j] = '_';
            }
        }
    }

     public void displayGrid() {
         for (int i = 0; i < gridSize; ++i) {
             for(int j = 0; j < gridSize; ++j) {
                 System.out.print(grid[i][j] + " ");
             }
             System.out.println();
         }
     }

}

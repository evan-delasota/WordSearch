package io.etd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
    private int gridSize;
    private char[][] grid;
    private List<Coordinate> coordinates = new ArrayList<>();
    private class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        grid = new char[gridSize][gridSize];

        for (int i = 0; i < gridSize; ++i) {
            for(int j = 0; j < gridSize; ++j) {
                coordinates.add(new Coordinate(i, j));
                grid[i][j] = '_';
            }
        }
    }
    public void fillGrid(List<String> words) {
        for (String word : words) {
            Collections.shuffle(coordinates);

            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                if (wordFitsGrid(word, coordinate)) {
                    for (char ch : word.toCharArray()) {
                        grid[x][y++] = ch;
                    }
                    break;
                }
            }
        }
    }

    private boolean wordFitsGrid(String word, Coordinate coordinate) {
        int x = coordinate.x;
        int y = coordinate.y;

        if (y + word.length() < gridSize) {
            for (int i = 0; i < word.length(); ++i) {
                if (grid[x][y + i] != '_') {
                    return false;
                }
            }
            return true;
        }

        return false;
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

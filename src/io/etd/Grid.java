package io.etd;

import java.util.ArrayList;
import java.util.Arrays;
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
    private enum Direction {
        HORIZONTAL,
        HORIZONTAL_INVERSE,
        VERTICAL,
        VERTICAL_INVERSE,
        DIAGONAL,
        DIAGONAL_INVERSE
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
    public void fillGridWithProvidedWords(List<String> words) {
        Collections.shuffle(coordinates);
        for (String word : words) {
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                Direction direction = getDirectionForWord(word, coordinate);

                if (direction != null) {
                    switch(direction) {
                        case HORIZONTAL -> {
                            for (char ch : word.toCharArray()) {
                                grid[x][y++] = ch;
                            }
                        }
                        case HORIZONTAL_INVERSE -> {
                            for (char ch : word.toCharArray()) {
                                grid[x][y--] = ch;
                            }
                        }
                        case VERTICAL -> {
                            for (char ch : word.toCharArray()) {
                                grid[x++][y] = ch;
                            }
                        }
                        case VERTICAL_INVERSE -> {
                            for (char ch : word.toCharArray()) {
                                grid[x--][y] = ch;
                            }
                        }
                        case DIAGONAL -> {
                            for (char ch : word.toCharArray()) {
                                grid[x++][y++] = ch;
                            }
                        }
                        case DIAGONAL_INVERSE -> {
                            for (char ch : word.toCharArray()) {
                                grid[x--][y--] = ch;
                            }
                        }
                    }
                    break;
                }
            }
        }
        fillRestOfGridRandomized();
    }

    public void fillRestOfGridRandomized() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < gridSize; ++i) {
            for (int j = 0; j < gridSize; ++j) {
                if (grid[i][j] == '_') {
                    int randomIdx = ThreadLocalRandom.current().nextInt(0, letters.length());
                    grid[i][j] = letters.charAt(randomIdx);
                }
            }
        }
    }

    private Direction getDirectionForWord(String word, Coordinate coordinate) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        for (Direction direction : directions) {
            if (wordFitsDirection(word, coordinate, direction)) {
                return direction;
            }
        }

        return null;
    }

    private boolean wordFitsDirection(String word, Coordinate coordinate, Direction direction) {
        int x = coordinate.x;
        int y = coordinate.y;
        int wordSize = word.length();

        switch (direction) {
            case HORIZONTAL -> {
                if (y + wordSize > gridSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    if (grid[x][y + i] != '_') {
                        return false;
                    }
                }
            }
            case HORIZONTAL_INVERSE -> {
                if (y < wordSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    if (grid[x][y - i] != '_') {
                        return false;
                    }
                }
            }
            case VERTICAL -> {
                if (x + wordSize > gridSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    if (grid[x + i][y] != '_') {
                        return false;
                    }
                }
            }
            case VERTICAL_INVERSE -> {
                if (x < wordSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    if (grid[x - i][y] != '_') {
                        return false;
                    }
                }
            }
            case DIAGONAL -> {
                if (x + wordSize > gridSize || y + wordSize > gridSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    if (grid[x + i][y + i] != '_') {
                        return false;
                    }
                }
            }
            case DIAGONAL_INVERSE -> {
                if (x < wordSize || y < wordSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    if (grid[x - i][y - i] != '_') {
                        return false;
                    }
                }
            }
        }
        return true;
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

package etd.io.wordsearchapi.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WordGridService {
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

    public char[][] generateGridWithProvidedWords(int gridSize, List<String> words) {
        List<Coordinate> coordinates = new ArrayList<>();
        char[][] grid = new char[gridSize][gridSize];
        for (int i = 0; i < gridSize; ++i) {
            for(int j = 0; j < gridSize; ++j) {
                coordinates.add(new Coordinate(i, j));
                grid[i][j] = '_';
            }
        }

        for (String word : words) {
            Collections.shuffle(coordinates);
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                Direction direction = getDirectionForWord(grid, word, coordinate);

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
        fillRestOfGridRandomized(grid);
        return grid;
    }

    public void fillRestOfGridRandomized(char[][] grid) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int gridSize = grid[0].length;

        for (int i = 0; i < gridSize; ++i) {
            for (int j = 0; j < gridSize; ++j) {
                if (grid[i][j] == '_') {
                    int randomIdx = ThreadLocalRandom.current().nextInt(0, letters.length());
                    grid[i][j] = letters.charAt(randomIdx);
                }
            }
        }
    }

    private Direction getDirectionForWord(char[][] grid, String word, Coordinate coordinate) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        for (Direction direction : directions) {
            if (wordFitsDirection(grid, word, coordinate, direction)) {
                return direction;
            }
        }

        return null;
    }

    private boolean wordFitsDirection(char[][] grid, String word, Coordinate coordinate, Direction direction) {
        int x = coordinate.x;
        int y = coordinate.y;
        int wordSize = word.length();
        int gridSize = grid[0].length;

        switch (direction) {
            case HORIZONTAL -> {
                if (y + wordSize > gridSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    char ch = grid[x][y + i];
                    if (ch != '_' && ch != word.charAt(i)) {
                        return false;
                    }
                }
            }
            case HORIZONTAL_INVERSE -> {
                if (y < wordSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    char ch = grid[x][y - i];
                    if (ch != '_' && ch != word.charAt(i)) {
                        return false;
                    }
                }
            }
            case VERTICAL -> {
                if (x + wordSize > gridSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    char ch = grid[x + i][y];
                    if (ch != '_' && ch != word.charAt(i)) {
                        return false;
                    }
                }
            }
            case VERTICAL_INVERSE -> {
                if (x < wordSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    char ch = grid[x - i][y];
                    if (ch != '_' && ch != word.charAt(i)) {
                        return false;
                    }
                }
            }
            case DIAGONAL -> {
                if (x + wordSize > gridSize || y + wordSize > gridSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    char ch = grid[x + i][y + i];
                    if (ch != '_' && ch != word.charAt(i)) {
                        return false;
                    }
                }
            }
            case DIAGONAL_INVERSE -> {
                if (x < wordSize || y < wordSize) {
                    return false;
                }
                for (int i = 0; i < wordSize; ++i) {
                    char ch = grid[x - i][y - i];
                    if (ch != '_' && ch != word.charAt(i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public void displayGrid(char[][] grid) {
        int gridSize = grid[0].length;
        for (int i = 0; i < gridSize; ++i) {
            for(int j = 0; j < gridSize; ++j) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}

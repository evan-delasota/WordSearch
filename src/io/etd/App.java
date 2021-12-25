package io.etd;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class App {

    public static void main(String[] args) {
        final int gridSize = 10;
        char[][] grid = new char[gridSize][gridSize];
        List<String> wordList = Arrays.asList("ONE", "TWO", "THREE");

        for (String word : wordList) {
            int y = ThreadLocalRandom.current().nextInt(0, gridSize);
            int x = ThreadLocalRandom.current().nextInt(0, gridSize);
            grid[x][y] = word.charAt(0);
        }


    }
}

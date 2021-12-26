package io.etd;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class App {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("ONE", "TWO", "THREE");
        Grid crossword = new Grid(10);
        crossword.fillGrid(wordList);
        crossword.displayGrid();


    }
}

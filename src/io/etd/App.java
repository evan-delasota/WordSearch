package io.etd;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("ONE", "TWO", "THREE");
        Grid crossword = new Grid(10);
        crossword.fillGridWithProvidedWords(wordList);
        crossword.displayGrid();


    }
}

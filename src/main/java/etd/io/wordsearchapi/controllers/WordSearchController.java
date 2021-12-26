package etd.io.wordsearchapi.controllers;

import etd.io.wordsearchapi.services.WordGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController("/")
public class WordSearchController {

    @Autowired
    WordGridService wordGridService;

    @GetMapping("/wordgrid")
    public String createWordGrid(@RequestParam int gridSize, @RequestParam String wordList) {
        List<String> words = Arrays.asList(wordList.split(","));
        char[][] grid = wordGridService.generateGridWithProvidedWords(gridSize, words);
        StringBuilder gridToString = new StringBuilder();
        for (int i = 0; i < gridSize; ++i) {
            for(int j = 0; j < gridSize; ++j) {
                gridToString.append(grid[i][j] + " ");
            }
            gridToString.append("\r\n");
        }
        return gridToString.toString();
    }
}

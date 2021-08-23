package ru.alenkin.textanalyzer.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alenkin.textanalyzer.TestData.sourceFileName;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class FileUtilsTest {
    private final String testSourceFileName = "src/test/resources/testdata.txt";

    @Test
    void fileExists() {
        assertTrue(FileUtils.fileExists(sourceFileName));
    }

    @Test
    void fileNotExists() {
        assertFalse(FileUtils.fileExists("dummy"));
        assertFalse(FileUtils.fileExists(" "));
    }

    @Test
    void readWordsFromFile() throws IOException {
        List<String> expected = List.of("one", "two", "three", "four", "five", "six", "seven");
        assertIterableEquals(expected, Files.readAllLines(Paths.get(testSourceFileName)));
    }
}

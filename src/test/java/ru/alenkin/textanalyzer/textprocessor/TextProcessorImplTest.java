package ru.alenkin.textanalyzer.textprocessor;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.alenkin.textanalyzer.TestData.*;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class TextProcessorImplTest {
    private TextProcessor textProcessor = new TextProcessorImpl();
    private final List<String> expected = List.of("стол 6", "о2 3", "контр 2", "11 2",
            "14 1", "тест 0", "д1р1о1в1а1 1", "ст 7", "сс 1", "с1с1 1", "ъ 1");


    @Test
    void checkFileWithSamples() throws IOException {
        String resultFile = textProcessor.checkFileWithSamples(samplesFileName, sourceFileName, destFileName);
        assertIterableEquals(expected, Files.readAllLines(Paths.get(resultFile)));
    }

    @Test
    void checkFileWithSamplesInvalidSamples() {
        assertThrows(IllegalArgumentException.class,
                () -> textProcessor.checkFileWithSamples(" ", sourceFileName, destFileName));
    }

    @Test
    void checkFileWithSamplesInvalidDest() {
        assertThrows(IllegalArgumentException.class,
                () -> textProcessor.checkFileWithSamples(samplesFileName, sourceFileName, ""));
    }

}

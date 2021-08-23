package ru.alenkin.textanalyzer.utils;

import org.junit.jupiter.api.Test;
import ru.alenkin.textanalyzer.model.sample.AbstractSample;
import ru.alenkin.textanalyzer.model.sample.SymbolSequence;
import ru.alenkin.textanalyzer.model.sample.Template;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class SampleParserTest {
    private final List<String> patterns = List.of("стол", "о2", "контр", "11",
            "14", "тест", "д1р1о1в1а1", "ст", "сс", "с1с1", "ъ");
    private final List<AbstractSample> expected = List.of(
            new Template("стол"),
            new SymbolSequence("о2", Map.of("о", 2)),
            new Template("контр"),
            new SymbolSequence("11", Map.of("1", 1)),
            new SymbolSequence("14", Map.of("1", 4)),
            new Template("тест"),
            new SymbolSequence("д1р1о1в1а1", Map.of("д", 1, "р", 1, "о", 1, "в", 1, "а", 1)),
            new Template("ст"),
            new Template("сс"),
            new SymbolSequence("с1с1", Map.of("с", 2)),
            new Template("ъ"));

    @Test
    void parse() {
        assertIterableEquals(expected, SampleParser.parse(patterns));
    }
}

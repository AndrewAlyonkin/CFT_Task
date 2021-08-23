package ru.alenkin.textanalyzer.utils;

import ru.alenkin.textanalyzer.model.sample.AbstractSample;
import ru.alenkin.textanalyzer.model.sample.SymbolSequence;
import ru.alenkin.textanalyzer.model.sample.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Util class for parsing words from file to the {@link AbstractSample patterns}
 */
public class SampleParser {

    private SampleParser() {
    }

    public static List<AbstractSample> parse(List<String> sampleWords) {
        return sampleWords.stream()
                .map(SampleParser::parse)
                .collect(Collectors.toList());
    }

    private static AbstractSample parse(String sample) {
        SymbolSequence symbolSequence = tryParseToSymbolSequence(sample);
        return symbolSequence == null
                ? new Template(sample)
                : symbolSequence;
    }

    /**
     * Checks if a sequence can be converted to a {@link SymbolSequence} pattern.
     * If don't - then return null.
     *
     * @param sample the sequence for parsing;
     * @return the {@link SymbolSequence} object or null, of parsing failed.
     */
    private static SymbolSequence tryParseToSymbolSequence(String sample) {
        // For this pattern, the number of characters in a word must be even - as many letters, as many numbers.
        int wordLength = sample.length();
        if (wordLength % 2 != 0) {
            return null;
        }

        Map<String, Integer> symbolsCount = new HashMap<>();
        for (int i = 0; i < wordLength - 1; i += 2) {
            try {
                symbolsCount.merge(charToString(sample, i),
                        Integer.parseInt(charToString(sample, i + 1)),
                        Integer::sum);
            } catch (Exception e) {
                return null;
            }
        }
        return new SymbolSequence(sample, symbolsCount);
    }

    private static String charToString(String s, int i) {
        return String.valueOf(s.charAt(i));
    }
}

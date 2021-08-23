package ru.alenkin.textanalyzer.utils;

import ru.alenkin.textanalyzer.model.SampleCheckResult;
import ru.alenkin.textanalyzer.model.sample.AbstractSample;
import ru.alenkin.textanalyzer.model.sample.SymbolSequence;
import ru.alenkin.textanalyzer.model.sample.Template;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Util class to assign a text analysis algorithm.
 */
public class TextAnalyzer {

    private TextAnalyzer() {
    }

    /**
     * Counts the number of words that match each pattern.
     *
     * @param word               the word to check.
     * @param sampleCheckResults the list of {@link SampleCheckResult}, which contains pattern for comparison
     */
    public static void analyzeWord(String word, List<SampleCheckResult> sampleCheckResults) {
        for (SampleCheckResult sampleCheckResult : sampleCheckResults) {
            if (check(word.toLowerCase(Locale.ROOT), sampleCheckResult.getSample())) {
                sampleCheckResult.incrementWordsCount();
            }
        }
    }

    private static boolean check(String word, AbstractSample sample) {

        if (sample instanceof Template) {
            return checkTemplate(word, (Template) sample);
        }
        if (sample instanceof SymbolSequence) {
            return checkSequence(word, (SymbolSequence) sample);
        }
        return false;
    }

    private static boolean checkSequence(String word, SymbolSequence sample) {
        for (Map.Entry<String, Integer> checkPair : sample.getSymbolsCount().entrySet()) {
            if (!wordContainsNumberOfSymbols(word, checkPair.getKey(), checkPair.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean wordContainsNumberOfSymbols(String word, String checkedSymbol, int symbolsCount) {
        return symbolsCount <= Arrays.stream(word.split(""))
                .filter(symbol -> symbol.equals(checkedSymbol))
                .count();
    }

    private static boolean checkTemplate(String word, Template sample) {
        return word.contains(sample.getPattern());
    }
}


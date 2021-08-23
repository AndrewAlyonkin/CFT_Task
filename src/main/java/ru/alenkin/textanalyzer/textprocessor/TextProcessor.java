package ru.alenkin.textanalyzer.textprocessor;

import java.io.IOException;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Сounts the number of words in the text that match the given patterns.
 */
public interface TextProcessor {
    String checkFileWithSamples(String samplesFileName, String sourceFileName, String destFileName) throws IOException;
}

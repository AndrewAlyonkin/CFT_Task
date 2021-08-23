package ru.alenkin.textanalyzer.textprocessor;

import ru.alenkin.textanalyzer.model.SampleCheckResult;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@FunctionalInterface
public interface TextExecutor {
    void execute(String word, List<SampleCheckResult> results);
}

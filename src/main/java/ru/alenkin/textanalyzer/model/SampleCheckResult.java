package ru.alenkin.textanalyzer.model;

import lombok.Getter;
import lombok.Setter;
import ru.alenkin.textanalyzer.model.sample.AbstractSample;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Represents the result of checking words for matching patterns.
 * Contains a pattern and its corresponding word count.
 * If sample is invalid, then wordsCount = -1!
 */
@Getter
@Setter
public class SampleCheckResult {
    private final AbstractSample sample;
    private volatile AtomicInteger wordsCount;

    public SampleCheckResult(AbstractSample sample, int wordsCount) {
        this.sample = sample;
        this.wordsCount = new AtomicInteger(wordsCount);
    }

    public void incrementWordsCount() {
        wordsCount.incrementAndGet();
    }

    @Override
    public String toString() {
        return sample + " " + wordsCount.get();
    }
}

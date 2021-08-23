package ru.alenkin.textanalyzer.model.sample;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Represents the abstract pattern that the word to check must match.
 */
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractSample {
    private final String pattern;

    protected AbstractSample(String pattern) {
        this.pattern = pattern;
    }
}

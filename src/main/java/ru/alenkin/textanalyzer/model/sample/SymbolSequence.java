package ru.alenkin.textanalyzer.model.sample;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Represents the sample with number for each character that the word should contain.
 */
@Getter
@Setter
@EqualsAndHashCode
public class SymbolSequence extends AbstractSample{
    private Map<String, Integer> symbolsCount;

    public SymbolSequence(String pattern, Map<String, Integer> symbolsCount) {
        super(pattern);
        this.symbolsCount = symbolsCount;
    }

    @Override
    public String toString() {
        return this.getPattern();
    }

}

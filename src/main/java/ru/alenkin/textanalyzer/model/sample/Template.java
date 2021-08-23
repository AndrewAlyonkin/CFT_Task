package ru.alenkin.textanalyzer.model.sample;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Represents a sequence of characters to be contained in the word.
 */
public class Template extends AbstractSample {
    public Template(String pattern) {
        super(pattern);
    }

    @Override
    public String toString() {
        return this.getPattern();
    }
}

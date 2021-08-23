package ru.alenkin.textanalyzer.textprocessor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.alenkin.textanalyzer.model.SampleCheckResult;
import ru.alenkin.textanalyzer.utils.FileUtils;
import ru.alenkin.textanalyzer.utils.SampleParser;
import ru.alenkin.textanalyzer.utils.TextAnalyzer;

import java.io.IOException;
import java.util.stream.Collectors;

import static ru.alenkin.textanalyzer.utils.FileUtils.fileExists;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Represents a class that reads templates from a file,
 * examines another file with those templates, and writes the result to the resulting file.
 */
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class TextProcessorImpl implements TextProcessor {

    @Override
    public String checkFileWithSamples(String samplesFileName, String sourceFileName, String destFileName) throws IOException {
        if (!fileExists(sourceFileName) || !fileExists(samplesFileName) || destFileName.isBlank()) {
            throw new IllegalArgumentException("One of source files is does not exist!");
        }

        log.debug("Getting samples from file");
        var sampleWords = FileUtils.readWordsFromFile(samplesFileName);
        var samples = SampleParser.parse(sampleWords);
        var results = samples.stream()
                .map(sample -> new SampleCheckResult(sample, 0))
                .collect(Collectors.toList());

        log.debug("Reading words from source file and checking it with samples");
        FileUtils.checkFileWords(sourceFileName, results, TextAnalyzer::analyzeWord);

        var analyzeResults = results.stream()
                .map(SampleCheckResult::toString)
                .collect(Collectors.toList());

        log.debug("Writing results to file");
        return FileUtils.writeToFile(destFileName, analyzeResults);
    }
}

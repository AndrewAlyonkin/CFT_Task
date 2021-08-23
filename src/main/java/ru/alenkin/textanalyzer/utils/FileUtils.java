package ru.alenkin.textanalyzer.utils;

import lombok.extern.slf4j.Slf4j;
import ru.alenkin.textanalyzer.model.SampleCheckResult;
import ru.alenkin.textanalyzer.textprocessor.TextExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Util class for for working with files.
 */
@Slf4j
public class FileUtils {

    public static boolean fileExists(String fileName) {
        return !fileName.isBlank() && Files.exists(Paths.get(fileName));
    }

    public static List<String> readWordsFromFile(String fileName) throws IOException {
        log.info("Reading samples from {}", fileName);
        try (Stream<String> lines = Files.readAllLines(Paths.get(fileName)).stream()) {
            return lines
                    .map(line -> line.split(" "))
                    .flatMap(Arrays::stream)
                    .map(String::trim)
                    .map(word -> word.replaceAll("\"", ""))
                    .filter(word -> (!word.isEmpty()))
                    .peek(log::debug)
                    .collect(Collectors.toList());
        }
    }

    public static void checkFileWords(String fileName, List<SampleCheckResult> samples, TextExecutor textExecutor) throws IOException {
        log.info("Reading and analyzing words from {}", fileName);
        Files.readAllLines(Paths.get(fileName))
                .parallelStream()
                .map(line -> line.split(" "))
                .flatMap(Arrays::stream)
                .forEach(word -> textExecutor.execute(word, samples));
    }

    public static String writeToFile(String destFileName, List<String> results) throws IOException {
        log.info("Writing results to {}", destFileName);
        return Files.write(Paths.get(destFileName), results).toAbsolutePath().toString();
    }
}

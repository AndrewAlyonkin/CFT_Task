package ru.alenkin.textanalyzer;


import lombok.extern.slf4j.Slf4j;
import ru.alenkin.textanalyzer.textprocessor.TextProcessor;
import ru.alenkin.textanalyzer.textprocessor.TextProcessorImpl;

import java.io.IOException;

import static ru.alenkin.textanalyzer.utils.FileUtils.fileExists;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Slf4j
public class MainProcessor {
    public static void main(String[] args) {

        final String samplesFileName = args[0];
        final String sourceFileName = args[1];
        final String destFileName = args[2];



        TextProcessor textProcessor = new TextProcessorImpl();
        try {
            String filePath = textProcessor.checkFileWithSamples(samplesFileName, sourceFileName, destFileName);
            System.out.println("File processing results here: " + filePath);
        } catch (IOException e) {
            log.error("File handling error! {}", e.getMessage());
        }
    }
}


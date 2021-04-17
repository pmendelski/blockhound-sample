package com.coditory.sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.stream.Collectors.joining;

class BlockingSamples {
    static private final String testFile = "src/main/resources/test.txt";
    static private final Path testFilePath = Path.of("src/main/resources/test.txt");

    static String sleepThread() {
        try {
            Thread.sleep(10);
            return "abc";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static String readFileWithBufferedReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            return reader.lines()
                    .collect(joining());
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + testFile, e);
        }
    }

    static String readFileWithFilesReadString() {
        try {
            return Files.readString(testFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + testFilePath, e);
        }
    }
}

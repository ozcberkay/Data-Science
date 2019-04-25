package com.ozcberkay.ir.util;

import com.ozcberkay.ir.model.ResultSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Writer {

    private static final String TOKENS_FILE = "out/tokens.txt";
    private static final String LEMMAS_FILE = "out/lemmas.txt";
    private static final String STEMS_FILE = "out/stems.txt";
    private static final String LOWER_CASE_FILE = "out/lower.txt";
    private static final String STOP_WORD_FILE = "out/stop_word.txt";

    public static void writeModels(ResultSet resultSet) throws IOException {

        if (!new File("out").mkdirs()) {
            return;
        }

        Files.write(Paths.get(TOKENS_FILE), resultSet.getTokens());
        Files.write(Paths.get(LEMMAS_FILE), resultSet.getLemmas());
        Files.write(Paths.get(STEMS_FILE), resultSet.getStems());
        Files.write(Paths.get(LOWER_CASE_FILE), resultSet.getLowerCasedWords());
        Files.write(Paths.get(STOP_WORD_FILE), resultSet.getStopWordElimination());
    }

}

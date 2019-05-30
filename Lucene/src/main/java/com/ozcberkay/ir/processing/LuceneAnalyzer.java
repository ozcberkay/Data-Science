package com.ozcberkay.ir.processing;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;

import java.io.IOException;

public class LuceneAnalyzer {

    private static Analyzer analyzer = null;

    public static Analyzer getAnalyzer() throws IOException {
        if (analyzer == null) {
            analyzer = CustomAnalyzer.builder()
                    .withTokenizer("whitespace")
                    .addTokenFilter("lowercase")
                    .addTokenFilter("stop")
                    .addTokenFilter("asciifolding")
                    .addTokenFilter("truncate", "prefixLength", "5")
                    .build();
        }
        return analyzer;
    }
}

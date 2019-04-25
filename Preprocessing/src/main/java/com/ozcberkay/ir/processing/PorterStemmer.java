package com.ozcberkay.ir.processing;

import org.tartarus.martin.Stemmer;

public class PorterStemmer {

    public static String stem(String source) {
        Stemmer stemmer = new Stemmer();
        stemmer.add(source.toCharArray(), source.length());
        stemmer.stem();
        return stemmer.toString();
    }
}

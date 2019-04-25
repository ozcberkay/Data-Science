package com.ozcberkay.ir.processing;

import com.ozcberkay.ir.model.ResultSet;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import intoxicant.analytics.corenlp.StopwordAnnotator;

import java.util.List;
import java.util.Properties;

public class DocumentPreprocessor {

    public static ResultSet processDocument(String content) {

        ResultSet resultSet = new ResultSet();

        List<CoreLabel> labels = analyzeWithStanfordNlp(content);

        for (CoreLabel label : labels) {
            // process tokens
            String token = label.word();
            String lemma = label.lemma();
            String stem = PorterStemmer.stem(lemma);
            String lowerCase = stem.toLowerCase();

            // add token
            resultSet.getTokens().add(token);
            resultSet.getLemmas().add(lemma);
            resultSet.getStems().add(stem);
            resultSet.getLowerCasedWords().add(lowerCase);
            if (!label.get(StopwordAnnotator.class).first) resultSet.getStopWordElimination().add(lowerCase);
        }

        return resultSet;
    }

    private static List<CoreLabel> analyzeWithStanfordNlp(String content) {
        Properties props = new Properties();

        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, stopword");
        props.setProperty("customAnnotatorClass.stopword", "intoxicant.analytics.corenlp.StopwordAnnotator");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(content);

        pipeline.annotate(document);
        return document.tokens();
    }

}

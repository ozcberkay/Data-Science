package com.ozcberkay.ir;

import com.ozcberkay.ir.model.DocumentModel;
import com.ozcberkay.ir.model.QueryModel;
import com.ozcberkay.ir.model.RelevanceModel;
import com.ozcberkay.ir.model.ResultSet;
import com.ozcberkay.ir.processing.LuceneIndexer;
import com.ozcberkay.ir.processing.LuceneSearcher;
import com.ozcberkay.ir.util.Parser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.ByteBuffersDirectory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        List<DocumentModel> documentModels = Parser.parseDocuments("cran.all.1400");
        List<QueryModel> queryModels = Parser.parseQueries("cran.qry");
        Map<String, RelevanceModel> relevanceModelMap = Parser.parseRelevance("cranqrel");

        ByteBuffersDirectory directory = new ByteBuffersDirectory();

        LuceneIndexer luceneIndexer = new LuceneIndexer();
        luceneIndexer.index(directory, documentModels);
        Files.delete(Paths.get("output.txt"));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"))) {
            for (QueryModel queryModel : queryModels) {
                writer.write(queryModel.toString());
                writer.newLine();
                try {
                    String searchText = queryModel.getQuery();
                    LuceneSearcher luceneSearcher = new LuceneSearcher(20);
                    List<ResultSet> results = luceneSearcher.search(directory, searchText);

                    for (ResultSet resultSet : results) {
                        writer.write(resultSet.toString());
                        writer.newLine();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                writer.newLine();
                writer.write("---------------------------------");
                writer.newLine();
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

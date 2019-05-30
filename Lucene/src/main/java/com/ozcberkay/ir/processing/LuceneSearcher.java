package com.ozcberkay.ir.processing;


import com.ozcberkay.ir.model.DocumentModel;
import com.ozcberkay.ir.model.ResultSet;
import com.ozcberkay.ir.util.Converter;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LuceneSearcher {

    private int topQueryCount = 0;

    public LuceneSearcher(int topQueryCount) {
        this.topQueryCount = topQueryCount;
    }

    public List<ResultSet> search(Directory directory, String searchStr) throws IOException, ParseException {

        QueryParser parser = new QueryParser("abstract_", LuceneAnalyzer.getAnalyzer());
        Query query = parser.parse(searchStr);

        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        TopDocs results = indexSearcher.search(query, this.topQueryCount);

        ScoreDoc[] hits = results.scoreDocs;
        List<ResultSet> resultSetList = new ArrayList<>();

        for (ScoreDoc hit : hits) {

            DocumentModel documentModel = Converter.toDocumentModel(indexSearcher.doc(hit.doc));
            resultSetList.add(new ResultSet(hit, documentModel));
        }

        return resultSetList;
    }

}

package com.ozcberkay.ir.processing;

import com.ozcberkay.ir.model.DocumentModel;
import com.ozcberkay.ir.util.Converter;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.ByteBuffersDirectory;

import java.io.IOException;
import java.util.List;

public class LuceneIndexer {

    public void index(ByteBuffersDirectory directory, List<DocumentModel> documentModels) throws IOException, ParseException {

        IndexWriterConfig config = new IndexWriterConfig(LuceneAnalyzer.getAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        for (DocumentModel myDoc : documentModels) {
            indexWriter.addDocument(Converter.toDocument(myDoc));
            indexWriter.commit();
        }

        indexWriter.close();

    }


}

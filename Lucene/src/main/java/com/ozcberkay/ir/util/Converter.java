package com.ozcberkay.ir.util;

import com.ozcberkay.ir.model.DocumentModel;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class Converter {

    public static DocumentModel toDocumentModel(Document document) {

        DocumentModel documentModel = new DocumentModel();

        documentModel.setId(document.get("id"));
        documentModel.setTitle(document.get("title"));
        documentModel.setAuthor(document.get("author"));
        documentModel.setBibliography(document.get("bibliography"));
        documentModel.setAbstract_(document.get("abstract_"));

        return documentModel;
    }

    public static Document toDocument(DocumentModel documentModel) {
        Document document = new Document();

        document.add(new StringField("id", documentModel.getId(), Field.Store.YES));
        document.add(new TextField("title", documentModel.getTitle(), Field.Store.YES));
        document.add(new StringField("author", documentModel.getAuthor(), Field.Store.YES));
        document.add(new StringField("bibliography", documentModel.getBibliography(), Field.Store.YES));
        document.add(new TextField("abstract_", documentModel.getAbstract_(), Field.Store.YES));
        return document;

    }
}

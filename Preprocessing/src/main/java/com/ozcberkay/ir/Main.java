package com.ozcberkay.ir;

import com.ozcberkay.ir.model.DocumentModel;
import com.ozcberkay.ir.model.ResultSet;
import com.ozcberkay.ir.processing.DocumentPreprocessor;
import com.ozcberkay.ir.util.Parser;
import com.ozcberkay.ir.util.Writer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<DocumentModel> documentModels = Parser.parse("cran.all.1400");

        ResultSet resultSet = DocumentPreprocessor.processDocument(
                documentModels.stream().map(DocumentModel::toString).collect(Collectors.joining()));

        Writer.writeModels(resultSet);
    }

}

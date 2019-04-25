package com.ozcberkay.ir.util;

import com.ozcberkay.ir.model.DocumentModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Parser {

    public static List<DocumentModel> parse(String docName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(docName));
        List<DocumentModel> documentModels = new ArrayList<>();

        DocumentModel documentModel = new DocumentModel();
        StringBuilder stringBuilder;
        ListIterator<String> iter = lines.listIterator();
        String line;
        while (iter.hasNext()) {
            line = iter.next();
            switch (line.split(" ")[0]) {
                case ".I":
                    documentModel = new DocumentModel();
                    documentModel.setId(line.split(" ")[1]);
                    break;
                case ".T":
                    stringBuilder = new StringBuilder();
                    while (iter.hasNext()) {
                        line = iter.next();
                        if (line.equals(".A")) {
                            documentModel.setTitle(stringBuilder.toString());
                            iter.previous();
                            break;
                        } else {
                            stringBuilder.append(" ").append(line);
                        }
                    }
                    break;
                case ".A":
                    documentModel.setAuthor(iter.next());
                    break;
                case ".B":
                    documentModel.setBibliography(iter.next());
                    break;
                case ".W":
                    stringBuilder = new StringBuilder();
                    while (iter.hasNext()) {
                        line = iter.next();
                        if (line.startsWith(".I")) {
                            iter.previous();
                            break;
                        } else {
                            stringBuilder.append(" ").append(line);
                        }
                    }
                    documentModel.setAbstract_(stringBuilder.toString());
                    documentModels.add(documentModel);
                    break;
                default:
                    break;
            }
        }

        return documentModels;
    }

}

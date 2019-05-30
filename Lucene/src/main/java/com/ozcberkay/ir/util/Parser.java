package com.ozcberkay.ir.util;

import com.ozcberkay.ir.model.DocumentModel;
import com.ozcberkay.ir.model.QueryModel;
import com.ozcberkay.ir.model.RelevanceModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {

    public static int convertRelevance(String relevance) {
        switch (relevance) {
            case "-1":
            case "1":
                return 4;
            case "2":
                return 3;
            case "3":
                return 2;
            case "4":
                return 1;
            default:
                return 0;
        }
    }

    public static Map<String, RelevanceModel> parseRelevance(String docName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(docName));
        Map<String, RelevanceModel> relevanceModels = new HashMap<>();
        for (String line : lines) {
            String[] splitLine = line.split(" ");
            String queryId = splitLine[0];
            String docId = splitLine[1];
            int relevance = convertRelevance(splitLine[2]);
            if (relevanceModels.containsKey(queryId)) {
                relevanceModels.get(queryId).getDocRelevanceMap().put(docId, relevance);
            } else {
                RelevanceModel relevanceModel = new RelevanceModel();
                relevanceModel.setQueryId(queryId);
                relevanceModel.getDocRelevanceMap().put(docId, relevance);
                relevanceModels.put(queryId, relevanceModel);
            }

        }
        return relevanceModels;
    }


    public static List<QueryModel> parseQueries(String docName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(docName));
        List<QueryModel> queryModels = new ArrayList<>();

        ListIterator<String> iter = lines.listIterator();
        while (iter.hasNext()) {
            QueryModel queryModel = new QueryModel();
            String line = iter.next(); // .I
            queryModel.setId(line.split(" ")[1]);
            iter.next(); //.W
            String query = "";
            line = iter.next();
            while (!line.startsWith(".I") && iter.hasNext()) {
                query += line;
                query += " ";
                line = iter.next();
            }

            if (!iter.hasNext()) {
                query += line;
            }
            queryModel.setQuery(query);
            queryModels.add(queryModel);

            if (!iter.hasNext()) {
                break;
            }

            iter.previous();
        }
        return queryModels;
    }

    public static List<DocumentModel> parseDocuments(String docName) throws IOException {
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
                    String author = iter.next();
                    if (author.equals(".B")) {
                        iter.previous();
                        documentModel.setAuthor("");
                    } else {
                        documentModel.setAuthor(author);
                    }
                    break;
                case ".B":
                    String bibliography = iter.next();
                    if (bibliography.equals(".W")) {
                        iter.previous();
                        documentModel.setBibliography("");
                    } else {
                        documentModel.setBibliography(bibliography);
                    }
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

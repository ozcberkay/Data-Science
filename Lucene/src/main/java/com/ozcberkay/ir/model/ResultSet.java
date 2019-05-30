package com.ozcberkay.ir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.lucene.search.ScoreDoc;

@Data
@AllArgsConstructor
public class ResultSet {

    ScoreDoc hit;
    DocumentModel documentModel;

    @Override
    public String toString() {
        return "Score : " + this.hit.score + " - " +
                "ID : " + this.documentModel.getId();
    }

}

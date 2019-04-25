package com.ozcberkay.ir.model;

import lombok.Data;

@Data
public class DocumentModel {

    private String id;
    private String title;
    private String author;
    private String bibliography;
    private String abstract_;

    public String toString() {
        return this.abstract_ + " ";
    }
}

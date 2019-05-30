package com.ozcberkay.ir.model;

import lombok.Data;

@Data
public class QueryModel {

    private String id;
    private String query;

    @Override
    public String toString() {
        return "QueryModel : " +
                "id='" + id + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}

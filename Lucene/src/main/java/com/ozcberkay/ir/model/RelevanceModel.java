package com.ozcberkay.ir.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class RelevanceModel {
    String queryId;
    HashMap<String, Integer> docRelevanceMap = new HashMap<>();
}

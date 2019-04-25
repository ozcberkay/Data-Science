package com.ozcberkay.ir.model;

import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class ResultSet {

    private Set<String> tokens = new LinkedHashSet<>();
    private Set<String> lemmas = new LinkedHashSet<>();
    private Set<String> stems = new LinkedHashSet<>();
    private Set<String> lowerCasedWords = new LinkedHashSet<>();
    private Set<String> stopWordElimination = new LinkedHashSet<>();

}

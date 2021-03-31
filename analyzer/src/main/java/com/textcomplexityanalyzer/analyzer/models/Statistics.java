package com.textcomplexityanalyzer.analyzer.models;

import java.util.*;

public class Statistics {
    private static Map<String, Long> counts;

    public Statistics() {
    }

    public Statistics(Map<String, Long> counts) {
        this.counts = counts;
    }



    public static Map<String, Long> fromText(Text text) {
        counts = new HashMap<>();
        counts.put("FiniteAdverbalCl", (long)0);
        counts.put("AttributiveAdjectives", (long)0);
        counts.put("Nouns", (long)0);
        counts.put("OfGenitives", (long)0);
        counts.put("PremodifyingNouns", (long)0);
        counts.put("VerbThatClauses", (long)0);
        counts.put("WhComplementClauses", (long)0);

        for (Annotation an : text.getAnnotationList()){
            counts.put(an.getName(), counts.get(an.getName()) + 1);
        }

        return counts;
    }

}

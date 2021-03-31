package com.textcomplexityanalyzer.analyzer.models;

import java.util.*;

public class Text {
    public String content;
    private List<TextNode> nodes;
    private Map<Long, Long> nodeMapping;
    private Set<String> annotationsSet = new HashSet<>();
    private List<Annotation> annotations = new ArrayList<>();
    private List<Annotation> annotationList = new ArrayList<>();
    private Map<String, Long> statistics;


    public Text(List<TextNode> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.nodeMapping = new HashMap<Long, Long>();

        for (int i = 0; i < this.nodes.size(); ++i) {
            nodeMapping.put(this.nodes.get(i).getId(), (long) i);
        }
    }

    public List<TextNode> getNodes() {
        return nodes;
    }

    public Map<Long, Long> getNodeMapping() {
        return nodeMapping;
    }

    public Set<String> getAnnotationsSet() {
        return annotationsSet;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public List<Annotation> getAnnotationList() {
        return annotationList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }

    public Text() {
    }

    public Text(String text) {
        this.content = text;
    }

    public void addAnnotation(Annotation annotation) {
        var type = annotation.getName();
        annotationList.add(annotation);

        if (!annotationsSet.contains(type)) {
            annotationsSet.add(type);
            annotations.add(annotation);
        }
        var begin = annotation.getStartNode();
        var end = annotation.getEndNode();
        int idx = Math.toIntExact(nodeMapping.get(begin));
        while (begin < end) {
            if (this.nodes.get(idx).getId().equals(begin)) {
                this.nodes.get(idx).addAnnotation(annotation);
                ++idx;
            }
            ++begin;
        }
    }

    public Map<String, Long> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<String, Long> statistics) {
        this.statistics = statistics;
    }

}

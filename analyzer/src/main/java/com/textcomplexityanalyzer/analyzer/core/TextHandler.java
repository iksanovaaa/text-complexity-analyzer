package com.textcomplexityanalyzer.analyzer.core;

import com.textcomplexityanalyzer.analyzer.models.Text;
import com.textcomplexityanalyzer.analyzer.models.TextNode;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class TextHandler extends DefaultHandler {
    private List<TextNode> nodes = new ArrayList<>();
    private Long currentId;

    private boolean working = false;

    public Text extractTextWithNodes() {  return new Text(nodes); }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (working) {
            currentId = Long.parseLong(attributes.getValue(0));
        }

        if (qName.toLowerCase().equals("textwithnodes")) {
            working = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (qName.toLowerCase().equals("textwithnodes")) {
            working = false;

            if (!nodes.get(nodes.size() - 1).getId().equals(currentId)) {
                nodes.add(new TextNode(currentId, ""));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (working) {
            var string = new String(ch, start, length);

            if (nodes.size() > 0 && nodes.get(nodes.size() - 1).getId().equals(currentId)) {
                var last = nodes.get(nodes.size() - 1);
                last.setText(last.getText() + string);
            } else {
                nodes.add(new TextNode(currentId, string));
            }
        }
    }
}

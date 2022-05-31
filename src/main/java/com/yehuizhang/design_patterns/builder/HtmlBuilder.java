package com.yehuizhang.design_patterns.builder;

import java.util.ArrayList;
import java.util.Collections;

class HtmlElement {
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        int indentSize = 2;
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement e : elements)
            sb.append(e.toStringImpl(indent + 1));

        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

public class HtmlBuilder {

    private final String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    // not fluent
    public void addChild(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
    }

    // Fluent Allows chaining of functions.
    public HtmlBuilder addChildFluent(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    // delegating
    @Override
    public String toString() {
        return root.toString();
    }


    public static void main(String[] args) {

        // we want to build a simple HTML paragraph
        System.out.println("Testing");
        StringBuilder sb = new StringBuilder();
        // now we want to build a list with 2 words
        String[] words = {"hello", "world"};
        sb.setLength(0); // clear it
        sb.append("<ul>\n");
        for (String word : words) {
            // indentation management, line breaks and other evils
            sb.append(String.format("  <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        System.out.println(sb);

        System.out.println("Using Builder");
        // ordinary non-fluent builder
        HtmlBuilder builder = new HtmlBuilder("ul");
        builder.addChild("li", "hello");
        builder.addChild("li", "world");
        System.out.println(builder);

        // fluent builder
        System.out.println("fluent builder");
        builder.clear();
        builder.addChildFluent("li", "hello")
                .addChildFluent("li", "world");
        System.out.println(builder);
    }
}

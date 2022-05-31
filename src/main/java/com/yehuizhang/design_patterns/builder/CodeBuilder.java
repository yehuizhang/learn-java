package com.yehuizhang.design_patterns.builder;

import java.util.ArrayList;

// This is the Builder Exercise
public class CodeBuilder {
    private final Class c;

    public CodeBuilder(String className) {
        this.c = new Class(className);
    }

    public CodeBuilder addField(String name, String type) {
        c.fields.add(new ClassField(name, type));
        return this;
    }

    @Override
    public String toString() {
        return c.toString();
    }

    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
        System.out.println(cb);
    }
}

class Class {
    String name;
    ArrayList<ClassField> fields = new ArrayList<>();

    public Class(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ").append(name).append("\n").append("{\n");
        for (ClassField cf : fields) {
            sb.append("  ").append(cf).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}


class ClassField {
    String name;
    String type;

    public ClassField(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("public %s %s;", type, name);
    }
}
package com.yehuizhang.design_patterns.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeBuilderTest {

    private String preprocess(String text)
    {
        return text.replace("\r\n", "\n").trim();
    }

    @Test
    void testToString() {
        CodeBuilder cb = new CodeBuilder("Foo");
        assertEquals("public class Foo\n{\n}",
                preprocess(cb.toString()));
    }

    @Test
    public void personTest()
    {
        CodeBuilder cb = new CodeBuilder("Person")
                .addField("name", "String")
                .addField("age", "int");
        assertEquals("""
                        public class Person
                        {
                          public String name;
                          public int age;
                        }""",
                preprocess(cb.toString()));
    }
}
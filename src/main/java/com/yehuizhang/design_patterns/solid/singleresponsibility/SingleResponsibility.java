package com.yehuizhang.design_patterns.solid.singleresponsibility;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Single Responsibility Principle (SRP)
 * aka Separation of concerns
 * every class should have only one responsibility.
 */
public class SingleResponsibility {
    public static void main(String[] args) {
        Journal j = new Journal();
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");
        System.out.println(j);
    }
}

class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    // This satisfies SRP
    public void addEntry(String text) {
        entries.add("" + (++count) + ": " + text);
    }

    // This satisfies SRP
    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    //This violates SRP; should be done in a separate class - Persistence.
    public void save(String filename) throws Exception {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(this);
        }
    }
}

// handles the responsibility of persisting objects
class Persistence {
    public void saveToFile(Journal journal,
                           String filename, boolean overwrite) throws Exception {
        if (overwrite || new File(filename).exists())
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
    }

    public void load(Journal journal, String filename) {
    }

    public void load(Journal journal, URL url) {
    }
}


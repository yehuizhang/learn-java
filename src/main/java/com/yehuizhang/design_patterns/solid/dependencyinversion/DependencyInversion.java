package com.yehuizhang.design_patterns.solid.dependencyinversion;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Dependency Inversion Principle (DIP)
 * Depend upon abstractions, [not] concretions
 * High-level module should not depend on low-level modules. Both should depend on abstractions.
 * Abstractions should not depend on details
 */
public class DependencyInversion {
    public static void main(String[] args)
    {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
}

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

// DIP improvement
interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

// The "Low-level" module
class Relationships implements RelationshipBrowser {
    private final List<Triplet<Person, Relationship, Person>> relations;

    public Relationships() {
        relations = new ArrayList<>();
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    // DIP improvement
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getValue0().name, name)
                        && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

// The "High-level" module
class Research {
    // DIP violation : Research depends on concretion - Relationships
    public Research(Relationships relationships) {
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
        relations.stream()
                .filter(x -> x.getValue0().name.equals("John")
                        && x.getValue1() == Relationship.PARENT)
                .forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
    }

    // DIP improvement: Research depends on abstraction now
    public Research(RelationshipBrowser browser) {
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child : children)
            System.out.println("John has a child called " + child.name);
    }
}
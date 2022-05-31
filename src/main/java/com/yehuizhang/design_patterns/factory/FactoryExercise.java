package com.yehuizhang.design_patterns.factory;


class Person {
    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class PersonFactory {
    private int id = 0;

    public Person createPerson(String name) {
        return new Person(id++, name);
    }
}


public class FactoryExercise {
    public static void main(String[] args) {
        PersonFactory pf = new PersonFactory();
        Person p1 = pf.createPerson("Person - 1");
        Person p2 = pf.createPerson("Person - 2");

        System.out.println(p1);
        System.out.println(p2);
    }
}

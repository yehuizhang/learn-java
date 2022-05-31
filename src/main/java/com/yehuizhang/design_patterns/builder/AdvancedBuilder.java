package com.yehuizhang.design_patterns.builder;

class Person {
    public String name;

    // address
    public String streetAddress, postcode, city;

    // employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

// Builder Facade
class PersonBuilder<T extends PersonBuilder<T>> {
    protected Person person = new Person();

    public T withName(String name) {
        person.name = name;
        return self();
    }

    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }

    protected T self() {
        //noinspection unchecked
        return (T) this;
    }

    public Person build() {
        return person;
    }
}

class PersonAddressBuilder extends PersonBuilder<PersonAddressBuilder> {
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress) {
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        person.city = city;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode) {
        person.postcode = postcode;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilder<PersonJobBuilder> {

    public PersonJobBuilder() {
    }

    public PersonJobBuilder(Person person) {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName) {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position) {
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome) {
        person.annualIncome = annualIncome;
        return this;
    }
}

public class AdvancedBuilder {
    public static void main(String[] args) {
        System.out.println("Fluent Builder Demo");
        PersonJobBuilder jobBuilder = new PersonJobBuilder()
                .withName("Yehui")
                .asA("Software Engineer II");
        System.out.println(jobBuilder.build());

        System.out.println("Faceted Builder Demo");
        Person person = new PersonBuilder<>()
                .withName("Yehui")
                .lives()
                .at("123 Dodge St")
                .in("Omaha")
                .withPostcode("68132")
                .works()
                .at("Tesla")
                .asA("Software Engineer")
                .earning(987654).
                build();
        System.out.println(person);
    }
}
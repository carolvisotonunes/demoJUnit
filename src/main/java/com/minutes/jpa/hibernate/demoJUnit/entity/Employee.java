package com.minutes.jpa.hibernate.demoJUnit.entity;


import javax.persistence.*;

@MappedSuperclass
//@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)// create one table with nullable fields PERFORMANCE
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)//create separated tables, each with their fields BETTER DESIGNER
//@Inheritance(strategy=InheritanceType.JOINED)//create the abstract entity Employee and the subclasses
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Employee[%s]", name);
    }
}

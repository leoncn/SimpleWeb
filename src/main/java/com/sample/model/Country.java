package com.sample.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by U0128754 on 3/22/2016.
 */
public class Country implements Serializable {

    private String name;

    private long id;

    private Set<Person> persons = new HashSet<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

package com.sample.service;

import com.sample.model.Person;

import java.util.List;

/**
 * Created by U0128754 on 3/21/2016.
 */
public interface PersonService {
    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(long id);
    public void removePerson(long id);
}

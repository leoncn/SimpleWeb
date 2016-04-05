package com.sample.dao;

import com.sample.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by U0128754 on 3/21/2016.
 */
public class PersonDaoImpl implements PersonDao {

    private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void addPerson(Person p) {
        getSession().persist(p);
    }

    @Override
    public void updatePerson(Person p) {
        this.getSession().update(p);
    }

    @Override
    public List<Person> listPersons() {
        return this.getSession().createQuery("from Person").list();
    }

    @Override
    public Person getPersonById(long id) {
        return (Person) this.getSession().load(Person.class, id);
    }

    @Override
    public void removePerson(long id) {
        this.getSession().delete(this.getPersonById(id));
    }
}

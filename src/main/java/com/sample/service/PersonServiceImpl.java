package com.sample.service;

import com.sample.dao.PersonDao;
import com.sample.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by U0128754 on 3/21/2016.
 */

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonDao dao;

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void addPerson(Person p) {
        logger.info("adding " + p);
        dao.addPerson(p);
        logger.info("add person done.");
    }

    @Override
    @Transactional
    public void updatePerson(Person p) {
        dao.updatePerson(p);
    }

    @Override
    @Transactional
    public List<Person> listPersons() {
        return dao.listPersons();
    }

    @Override
    @Transactional
    public Person getPersonById(long id) {
        return dao.getPersonById(id);
    }

    @Override
    @Transactional
    public void removePerson(long id) {
        dao.removePerson(id);
    }
}

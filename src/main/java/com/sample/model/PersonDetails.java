package com.sample.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by U0128754 on 3/23/2016.
 */
@Entity
@Table(name="person_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personId") })
public class PersonDetails implements Serializable {

    @Id
    @Column(name = "personId",  unique = true, nullable = false)
    private long personId;

    @Column(name = "passport",  nullable = true)
    private String passport;

    @Column(name = "age",  nullable = false)
    private int age;

    @OneToOne(mappedBy = "details", cascade = CascadeType.ALL)
    private Person person;

    public PersonDetails() {
    }

    public PersonDetails(String passport, int age, long personId) {
        this.passport = passport;
        this.age = age;
        this.personId = personId;
    }

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPersonId() {
        return this.personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "age=" + age +
                ", personId=" + personId +
                ", passport='" + passport + '\'' +
                '}';
    }
}

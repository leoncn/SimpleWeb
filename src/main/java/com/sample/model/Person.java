package com.sample.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Create0128n 3/21/2016.
 */

@Entity
@Table(name = "person" )

public class Person implements Serializable {
    @Id
    @Column(name ="id", nullable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="person_seq")
    @TableGenerator(
            name="person_seq",
            table="ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "LAST_ID",
            pkColumnValue="PERSON",
            allocationSize=1
    )
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="COUNTRY_ID" , nullable = false)
    private Country country;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PersonDetails details;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public PersonDetails getDetails() {
        return this.details;
    }

    public void setDetails(PersonDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details=" + details +
                '}';
    }
}

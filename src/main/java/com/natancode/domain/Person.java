package com.natancode.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public int birthDate;

    public static List<Person> findByBirthDate(int birthDate) {
        return find("birthDate", birthDate).list();
    }
}

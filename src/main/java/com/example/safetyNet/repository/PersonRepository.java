package com.example.safetyNet.repository;

import com.example.safetyNet.model.Person;

import java.util.List;

public interface PersonRepository {


    List<Person> getPersonsList();

    void setPersonsList(List<Person> personsList);

    List<Person> getPersonByAddress(String address);

    Person getPersonByFirstnameLastName(String firstname, String lastname);


    List<Person> ajouter(Person person);

    List<Person> remove(Person person);

    List<Person> getPersonByCity(String city);

    List<Person> getPersonsByFirstnameLastName(String firstname,
                                               String lastname);


}

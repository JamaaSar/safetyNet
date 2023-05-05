package com.example.safetyNet.repository;

import com.example.safetyNet.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class PersonRepositoryIpml
        implements PersonRepository {

    private List<Person> personsList;

    @Override
    public List<Person> getPersonsList() {
        return personsList;
    }

    @Override
    public void setPersonsList(List<Person> personsList) {
        this.personsList = personsList;
    }

    @Override
    public List<Person> getPersonByCity(String city) {
        return personsList.stream()
                .filter(person -> (person.getCity().toLowerCase()).equals(
                        city.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getPersonByAddress(String address) {
        return personsList.stream()
                .filter(person -> (person.getAddress().toLowerCase().replaceAll("\\s+",
                        "")).equals(address.toLowerCase().replaceAll("\\s+",
                        ""))).collect(Collectors.toList());
    }

    @Override
    public Person getPersonByFirstnameLastName(String firstname, String lastname) {
        return personsList.stream()
                .filter(person -> person.getLastName().equals(lastname) &&
                        person.getFirstName().equals(firstname)).findFirst().get();
    }

    @Override
    public List<Person> getPersonsByFirstnameLastName(String firstname,
                                                      String lastname) {
        return personsList.stream()
                .filter(person -> person.getLastName().equals(lastname) ||
                        person.getFirstName().equals(firstname)).collect(
                        Collectors.toList());
    }

    @Override
    public List<Person> ajouter(Person person) {
        personsList.add(person);
        return personsList;
    }

    @Override
    public List<Person> remove(Person person) {
        personsList.remove(person);
        return personsList;
    }


}

package com.example.safetyNet.service;

import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

   public List<Person> getAllPerson() throws IOException {
       return personRepository.getPersonList();

   }

    public List<?> recupererEmails(String param) throws IOException {
        List<Person> persons = personRepository.getPersonList();
        String ville = param.substring(0, 1).toUpperCase() + param.substring(1);
        List<String> emails = persons.stream().filter(person -> person.getCity().equals(ville)).map(Person::getEmail).collect(Collectors.toList());
        return (emails.isEmpty() ? null : emails);
    }



}

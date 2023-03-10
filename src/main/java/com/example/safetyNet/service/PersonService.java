package com.example.safetyNet.service;

import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

   public List<Person> getAllPerson(){
       return personRepository.getPersonList();

   }



}

package com.example.safetyNet.service;

import com.example.safetyNet.dto.*;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordService medicalRecordService;
    private MapperService mapper;

   public List<Person> getAllPerson() throws IOException {
       return personRepository.getPersonsList();
   }
    public List<Person> getPersonByAddresse(String addresse) throws IOException {
        return this.getAllPerson().stream().filter(person -> (person.getAddress().toLowerCase()).replaceAll("\\s+","").equals(addresse.toLowerCase())).collect(Collectors.toList());

    }
    public List<String> getAllEmails(String param) throws IOException {
        String ville = param.substring(0, 1).toUpperCase() + param.substring(1);
        List<String> emails = this.getAllPerson().stream().filter(person -> person.getCity().equals(ville)).map(Person::getEmail).collect(Collectors.toList());
        return (emails.isEmpty() ? null : emails);
    }
    public List<PersonInfoDto> getPersonInfo(String firstname, String lastname) throws IOException {
        List<Person> persons =  this.getAllPerson();
        List<Person> filteredPerson = persons.stream().filter(person -> person.getLastName().equals(lastname)).collect(Collectors.toList());
        List<PersonInfoDto> res = mapper.getPersonsInfo(filteredPerson);

        return res;
    }
    public List<ChildAlertDto> getChildAlert(String addresse) throws IOException {
        List<Person> persons = this.getPersonByAddresse(addresse);
        List<ChildAlertDto> childAlertDtos = new ArrayList<>();
        List<Person> listAdults = new ArrayList<>();
        List<Child> listChildren = new ArrayList<>();
        ChildAlertDto childAlertDto = new ChildAlertDto();

        for (PersonDTO p: mapper.getAllInfoOfPerson(persons)){
            if(p.getAge() < 18){
                Child child = new Child();
                child.setFirstName(p.getFirstName());
                child.setLastName(p.getLastName());
                child.setAge(p.getAge());
                listChildren.add(child);
            }else{
                Person person = new Person();
                person.setFirstName(p.getFirstName());
                person.setLastName(p.getLastName());
                person.setAddress(p.getAddress());
                person.setZip(p.getZip());
                person.setCity(p.getCity());
                person.setPhone(p.getPhone());
                listAdults.add(person);
            }
        }
        childAlertDto.setAdultList(listAdults);
        childAlertDto.setChildList(listChildren);
        childAlertDtos.add(childAlertDto);

        return childAlertDtos;
    }





}

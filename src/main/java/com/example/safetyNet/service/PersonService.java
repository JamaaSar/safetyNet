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
    PersonRepository personRepository;
    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    MapperService mapperService;

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
    public List<PersonGeneralDto> getPersonInfo(String firstname, String lastname) throws IOException {
        List<Person> persons =  this.getAllPerson();
        List<Person> filteredPerson = persons.stream().filter(person -> person.getLastName().equals(lastname)).collect(Collectors.toList());
        List<PersonGeneralDto> res = mapperService.getPersonsInfo(filteredPerson);

        return res;
    }
    public List<ChildAlertDto> getChildAlert(String addresse) throws IOException {
        List<Person> persons = this.getPersonByAddresse(addresse);
        List<ChildAlertDto> childAlertDtos = new ArrayList<>();
        List<Person> listAdults = new ArrayList<>();
        List<Child> listChildren = new ArrayList<>();
        ChildAlertDto childAlertDto = new ChildAlertDto();

        for (PersonDTO p: mapperService.getAllInfoOfPerson(persons)){
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


    //CRUD
    public void update(String firstname, String lastname, String address, String city, String zip,String phone, String email){

    }

    public void add(String firstname, String lastname, String address, String city, String zip,String phone, String email){
       Person person = new Person();
       person.setFirstName(firstname);
       person.setLastName(lastname);
       person.setAddress(address);
       person.setCity(city);
       person.setZip(zip);
       person.setPhone(phone);
       person.setEmail(email);
       personRepository.getPersonsList().add(person);

    }

    public void delete(String firstname, String lastname){
       for(Person p : personRepository.getPersonsList()){
           if(firstname.equals(p.getFirstName()) && lastname.equals(p.getLastName())){
               personRepository.getPersonsList().remove(p);
           }
        }

    }




}

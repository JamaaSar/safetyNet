package com.example.safetyNet.service;

import com.example.safetyNet.dto.ChildAlertDto;
import com.example.safetyNet.dto.PersonInfoDto;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.MedicalRecordRepository;
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
    private MedicalRecordRepository medicalRecordRepository;



   public List<Person> getAllPerson() throws IOException {
       return personRepository.getAllPersons();

   }
    public List<?> getAllEmails(String param) throws IOException {
        List<Person> persons = personRepository.getAllPersons();
        String ville = param.substring(0, 1).toUpperCase() + param.substring(1);
        List<String> emails = persons.stream().filter(person -> person.getCity().equals(ville)).map(Person::getEmail).collect(Collectors.toList());
        return (emails.isEmpty() ? null : emails);
    }
    public List<?> getPersonInfo(String firstname, String lastname) throws IOException {
        List<Person> persons = personRepository.getAllPersons();
        List<Person> filteredPerson = persons.stream().filter(person -> person.getLastName().equals(lastname)).collect(Collectors.toList());
        List<PersonInfoDto> res = getAllInfoOfPerson(filteredPerson);

        return res;
    }
    public Map<String, List<?>> getChild(String addresse) throws IOException {
        List<Person> persons = personRepository.getAllPersons();
        List<Person> filteredPerson = persons.stream().filter(person -> (person.getAddress().toLowerCase()).replaceAll("\\s+","").equals(addresse.toLowerCase())).collect(Collectors.toList());
        Map<String, List<?>> childAlertRes = new TreeMap<>();
        List<ChildAlertDto> childAlertDtos = new ArrayList<>();
        List<Person> listAdult = new ArrayList<>();

        for (PersonInfoDto p: getAllInfoOfPerson(filteredPerson)){
            if(p.getAge() < 18){
                ChildAlertDto childAlertDto = new ChildAlertDto();
                childAlertDto.setFirstName(p.getFirstName());
                childAlertDto.setLastName(p.getLastName());
                childAlertDto.setAge(p.getAge());
                childAlertDtos.add(childAlertDto);
            }else{
                Person person = new Person();
                person.setFirstName(p.getFirstName());
                person.setLastName(p.getLastName());
                System.out.println(person);
                listAdult.add(person);
            }
        }
        childAlertRes.put("child", childAlertDtos);
        childAlertRes.put("adults", listAdult);
       
        return childAlertRes;
    }

    public List<PersonInfoDto> getAllInfoOfPerson(List<Person> data) throws IOException {
        List<PersonInfoDto> res = new ArrayList<>();
        for (Person p : data){
            PersonInfoDto personInfoDto = new PersonInfoDto();
            MedicalRecord medicalRecord = medicalRecordRepository.getAllMedicalRecordsByName("firstName", p.getFirstName());
            personInfoDto.setFirstName(p.getFirstName());
            personInfoDto.setLastName(p.getLastName());
            personInfoDto.setAddress(p.getAddress());
            personInfoDto.setAge(CalculateAge.calculateAge(medicalRecord.getBirthdate()));
            personInfoDto.setEmail(p.getEmail());
            personInfoDto.setMedications(medicalRecord.getMedications());
            personInfoDto.setAllergies(medicalRecord.getAllergies());
            res.add(personInfoDto);
        }
        return res;
    }



}

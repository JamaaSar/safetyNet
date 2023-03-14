package com.example.safetyNet.service;

import com.example.safetyNet.dto.PersonInfoDto;
import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.FireStationRepository;
import com.example.safetyNet.repository.MedicalRecordRepository;
import com.example.safetyNet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FireStationService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    PersonService personService;

   public List<Person> getAllPerson() throws IOException {
       return personRepository.getAllPersons();

   }
    public List<Map<String, List<?>>> getFireStaionById(String param) throws IOException {
        List<FireStation> fireStations = fireStationRepository.getAllFireStation().stream().filter(fireStation -> fireStation.getStation().equals(param)).collect(Collectors.toList());
        List<Map<String, List<?>>> result = new ArrayList();
        System.out.println(fireStations);
        for(FireStation f : fireStations){
            Map<String, List<?>> informationByStation = new TreeMap<>();
            List<Person> persons= personRepository.getAllPersons().stream().filter(person -> person.getAddress().equals(f.getAddress())).collect(Collectors.toList());
            int adult = 0, child = 0;
            for(PersonInfoDto p :personService.getAllInfoOfPerson(persons) ){
                if( p.getAge() > 18){
                    adult++;
                }else{
                    child++;
                }
            }
            informationByStation.put("persons", persons);
            informationByStation.put("adults", Collections.singletonList(adult));
            informationByStation.put("childs", Collections.singletonList(child));
            result.add(informationByStation);
        }
        return result;
    }
    public List<List<?>> getFireStaion(String param) throws IOException {
        List<FireStation> fireStations = fireStationRepository.getAllFireStation().stream().filter(fireStation -> fireStation.getStation().equals(param)).collect(Collectors.toList());
        List<List<?>> result = new ArrayList();
        for(FireStation f : fireStations){
            List<String> phones= personRepository.getAllPersons().stream().filter(person -> person.getAddress().equals(f.getAddress())).map(Person::getPhone).collect(Collectors.toList());
            result.add(phones);
        }
        return result;
    }
    public List<List<?>> getFire(String param) throws IOException {
        List<Person> persons = personRepository.getAllPersons().stream().filter(person -> person.getAddress().equals(param)).collect(Collectors.toList());
        List<List<?>> result = new ArrayList();
        return result;
    }
    public List<List<?>> getFlood(List<String> stations) throws IOException {
        return null;
    }




}

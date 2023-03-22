package com.example.safetyNet.service;

import com.example.safetyNet.dto.FireDto;
import com.example.safetyNet.dto.PersonInfoDto;
import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    PersonService personService;

    @Autowired
    MapperService mapper;


    public List<FireStation> getFireStationsByStation(String param) {
        return fireStationRepository.getFireStationsList().stream().filter(fireStation -> fireStation.getStation().equals(param)).collect(Collectors.toList());
    }

    public List<Map<String, List<?>>> getFireStaionById(String param) throws IOException {
        List<Map<String, List<?>>> result = new ArrayList();
        for(FireStation f : this.getFireStationsByStation(param)){
            Map<String, List<?>> informationByStation = new TreeMap<>();
            List<Person> persons= personService.getAllPerson().stream().filter(person -> person.getAddress().equals(f.getAddress())).collect(Collectors.toList());
            int adult = 0, child = 0;
            for(PersonInfoDto p : mapper.getPersonsInfo(persons) ){
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
        List<FireStation> fireStations = fireStationRepository.getFireStationsByStation(param);
        List<List<?>> result = new ArrayList();
        for(FireStation f : fireStations){
            List<String> phones= personService.getAllPerson().stream().filter(person -> person.getAddress().equals(f.getAddress())).map(Person::getPhone).collect(Collectors.toList());
            result.add(phones);
        }
        return result;
    }
    public List<FireDto> getFire(String param) throws IOException {
        String ville = param.substring(0, 1).toLowerCase() + param.substring(1);
        List<Person> persons = personService.getAllPerson().stream().filter(person -> (person.getAddress().toLowerCase()).replaceAll("\\s+","").equals(param.toLowerCase())).collect(Collectors.toList());
        List<FireDto> res = getAllInfoOfPerson(persons);
        return res;
    }
    public List<List<?>> getFlood(List<String> stations) throws IOException {
       for(String s : stations){

       }
        return null;
    }


    public List<FireDto> getAllInfoOfPerson(List<Person> data) throws IOException {
        List<FireDto> res = new ArrayList<>();
        for (Person p : data){
            FireDto fireDto = new FireDto();
            MedicalRecord medicalRecord = medicalRecordService.getAllMedicalRecordsByName(p.getFirstName());
            fireDto.setFirstName(p.getFirstName());
            fireDto.setLastName(p.getLastName());
            fireDto.setAddress(p.getAddress());
            fireDto.setAge(CalculateAge.calculateAge(medicalRecord.getBirthdate()));
            fireDto.setEmail(p.getEmail());
            fireDto.setPhone(p.getPhone());
            fireDto.setMedications(medicalRecord.getMedications());
            fireDto.setAllergies(medicalRecord.getAllergies());
            res.add(fireDto);
        }
        return res;
    }



}

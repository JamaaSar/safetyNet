package com.example.safetyNet.service;

import com.example.safetyNet.dto.PersonGeneralDto;
import com.example.safetyNet.exception.NotFoundException;
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
    MedicalRecordService medicalRecordService;
    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    PersonService personService;
    @Autowired
    MapperService mapperService;

    public List<FireStation> getFireStationsByStation(String param) {
        return fireStationRepository.getFireStationsList().stream().filter(fireStation -> fireStation.getStation().equals(param)).collect(Collectors.toList());
    }

    public List<Map<String, List<?>>> getFireStaionById(String param) throws IOException {
        List<Map<String, List<?>>> result = new ArrayList();
        for(FireStation f : this.getFireStationsByStation(param)){
            Map<String, List<?>> informationByStation = new TreeMap<>();
            List<Person> persons= personService.getAllPerson().stream().filter(person -> person.getAddress().equals(f.getAddress())).collect(Collectors.toList());
            int adult = 0, child = 0;
            for(PersonGeneralDto p : mapperService.getPersonsInfo(persons) ){
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

    public List<List<String>> getFireStaion(String param) throws IOException {
        List<List<String>> result = new ArrayList();
        for(FireStation f : this.getFireStationsByStation(param)){
            List<String> phones= personService.getAllPerson().stream().filter(person -> person.getAddress().equals(f.getAddress())).map(Person::getPhone).collect(Collectors.toList());
            result.add(phones);
        }
        return result;
    }

    public List<PersonGeneralDto> getFire(String param) throws IOException {
        List<Person> persons = personService.getAllPerson().stream().filter(person -> (person.getAddress().toLowerCase()).replaceAll("\\s+","").equals(param.toLowerCase())).collect(Collectors.toList());
        List<PersonGeneralDto> res = mapperService.getPersonsInfo(persons);
        return res;
    }

    public List<List<?>> getFlood(List<String> stations) throws IOException {
       for(String s : stations){

       }
        return null;
    }

    public FireStation update(String address, String station){
        FireStation fireStationToUpdate = fireStationRepository.getFireStationsList().stream().filter(fireStation -> fireStation.getAddress().equals(address)).findFirst().orElseThrow(() -> new NotFoundException("Firestation with address '" + address +  "' not found"));
        if(!station.isEmpty()){
            fireStationToUpdate.setStation(station);
        }
        return fireStationToUpdate;
    }

    public FireStation add(FireStation fireStation){
        fireStationRepository.getFireStationsList().add(fireStation);
        return fireStation;
    }

    public List<FireStation> delete(String address){
        for(FireStation f : fireStationRepository.getFireStationsList()){
            if(address.equals(f.getAddress()) ){
                fireStationRepository.getFireStationsList().remove(f);
            }
        }
        return fireStationRepository.getFireStationsList();
    }
}

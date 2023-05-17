package com.example.safetyNet.service;

import com.example.safetyNet.dto.FloodDTO;
import com.example.safetyNet.dto.PersonGeneralDto;
import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.FireStation;
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
    FireStationRepository fireStationRepository;
    @Autowired
    PersonService personService;
    @Autowired
    MapperService mapperService;


    public List<Map<String, List<?>>> getFireStationById(String fireStation)
            throws IOException {
        List<Map<String, List<?>>> result = new ArrayList();
        List<FireStation> fireStationList =
                fireStationRepository.getFireStationByStation(fireStation);
        for (FireStation f : fireStationList) {
            Map<String, List<?>> informationByStation = new TreeMap<>();
            List<Person> persons = personService.getPersonByAddress(f.getAddress());
            int adult = 0, child = 0;
            for (PersonGeneralDto p : mapperService.getPersonsInfo(persons)) {
                if (p.getAge() > 18) {
                    adult++;
                } else {
                    child++;
                }
            }
            informationByStation.put("persons", persons);
            informationByStation.put("adults", Collections.singletonList(adult));
            informationByStation.put("childs", Collections.singletonList(child));
            result.add(informationByStation);
        }
        if (result.isEmpty()) {
            //log.error("error finding ")
            throw new NotFoundException("The station '" + fireStation + "' not found");
        }
        return result;
    }

    public List<List<String>> getFireStation(String param) throws IOException {
        List<List<String>> result = new ArrayList();
        for (FireStation f : fireStationRepository.getFireStationByStation(param)) {
            List<String> phones = personService.getAllPerson().stream()
                    .filter(person -> person.getAddress().equals(f.getAddress()))
                    .map(Person::getPhone).collect(Collectors.toList());
            result.add(phones);
        }
        return result;
    }

    public List<PersonGeneralDto> getFire(String param) throws IOException {
        List<Person> persons = personService.getPersonByAddress(param);
        List<PersonGeneralDto> result = mapperService.getPersonsInfo(persons);
        if (result.isEmpty()) {
            throw new NotFoundException("No data found for address " + param);
        }
        return result;
    }

    public List<FloodDTO> getFlood(List<String> stations) throws IOException {
        List<FloodDTO> floodDTOS = new ArrayList<>();
        for (String s : stations) {
            FloodDTO floodDTO = new FloodDTO();
            List<FireStation> fireStations =
                    fireStationRepository.getFireStationByStation(s);
            for (FireStation fireStation : fireStations) {
                List<Person> persons =
                        personService.getPersonByAddress(fireStation.getAddress());
                floodDTO.setAddress(fireStation.getAddress());
                floodDTO.setPersonForFloodDTOList(
                        mapperService.getPersonsInfoForFlood(persons));
            }
            floodDTOS.add(floodDTO);
        }
        return floodDTOS;
    }

    public FireStation update(String address, String station) {
        FireStation fireStationToUpdate =
                fireStationRepository.getFireStationByAddress(address);
        if (!station.isEmpty()) {
            fireStationToUpdate.setStation(station);
        }

        return fireStationToUpdate;
    }

    public List<FireStation> ajouter(FireStation fireStation) {
        return fireStationRepository.ajouter(fireStation);
    }

    public List<FireStation> delete(String address) {
        FireStation fireStationToDel =
                fireStationRepository.getFireStationByAddress(address);
        return fireStationRepository.remove(fireStationToDel);
    }

}

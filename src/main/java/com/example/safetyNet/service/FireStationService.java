package com.example.safetyNet.service;

import com.example.safetyNet.dto.FloodDTO;
import com.example.safetyNet.dto.PersonGeneralDto;
import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private static final Logger logger = LogManager.getLogger(FireStationService.class);

    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    PersonService personService;
    @Autowired
    MapperService mapperService;


    public FireStation getFireStationByAdresse(String param) {


        logger.info(
                "Doit retourner un firestation.");
        return fireStationRepository.getFireStationByAddress(param);
    }

    /***
     * Doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante
     * @param fireStation
     * @return
     * @throws IOException
     */
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
            logger.error("Aucune personne à été trouvé pour la caserne correspondant");
            throw new NotFoundException("The station '" + fireStation + "' not found");
        }
        logger.info(
                "Doit retourner une liste des personnes couvertes par la caserne de " +
                        "pompiers correspondante");
        return result;
    }

    /**
     * Doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de pompiers.
     *
     * @param param
     * @return
     * @throws IOException
     */
    public List<Set<String>> getFireStation(String param) throws IOException {
        List<Set<String>> result = new ArrayList();

        for (FireStation f : fireStationRepository.getFireStationByStation(param)) {
            List<String> phones = personService.getAllPerson().stream()
                    .filter(person -> person.getAddress().equals(f.getAddress()))
                    .map(Person::getPhone).collect(Collectors.toList());
            result.add(new HashSet(phones));
        }

        logger.info(
                "Doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de pompiers.");
        return result;
    }

    /**
     * Doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne de pompiers la desservant.
     *
     * @param param
     * @return
     * @throws IOException
     */
    public List<PersonGeneralDto> getFire(String param) throws IOException {
        List<Person> persons = personService.getPersonByAddress(param);
        List<PersonGeneralDto> result = mapperService.getPersonsInfo(persons);
        if (result.isEmpty()) {
            logger.error("Aucune personne à été trouvé pour l'adresse correspondant");
            throw new NotFoundException("No data found for address " + param);
        }
        logger.info(
                "Doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne de pompiers la desservant.");

        return result;
    }

    /**
     * Doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les personnes par adresse.
     *
     * @param stations
     * @return
     * @throws IOException
     */
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
        logger.info(
                "Doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les personnes par adresse.");

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

    public FireStation ajouter(FireStation fireStation) {
        return fireStationRepository.ajouter(fireStation);
    }

    public List<FireStation> delete(String address) {
        FireStation fireStationToDel =
                fireStationRepository.getFireStationByAddress(address);
        return fireStationRepository.remove(fireStationToDel);
    }

}

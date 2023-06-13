package com.example.safetyNet.service;

import com.example.safetyNet.dto.*;
import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PersonService {
    private static final Logger logger = LogManager.getLogger("PersonService");
    @Autowired
    PersonRepository personRepository;
    @Autowired
    MapperService mapperService;

    public List<Person> getAllPerson() {
        return personRepository.getPersonsList();
    }

    public List<Person> getPersonByAddress(String param) {
        return personRepository.getPersonByAddress(param);
    }

    /**
     * Doit retourner les adresses mail de tous les habitants de la ville.
     *
     * @param city
     * @return
     */
    public Set<String> getAllEmails(String city) {
        Set<String> emails = new HashSet<>();
        List<Person> persons = personRepository.getPersonByCity(city);

        if (persons.isEmpty()) {
            logger.error("Aucune email à été trouvé pour la city correspondant");
            throw new NotFoundException("No data found");
        } else {
            for (Person p : persons) {
                emails.add(p.getEmail());
            }
        }
        logger.info(
                "Doit retourner les adresses mail de tous les habitants de la ville.");
        return emails;
    }

    /**
     * Doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, posologie, allergies) de chaque habitant.
     *
     * @param firstname
     * @param lastname
     * @return
     * @throws IOException
     */
    public List<PersonGeneralDTO> getPersonInfo(String firstname, String lastname)
            throws IOException {
        List<Person> persons = personRepository.getPersonsByFirstnameLastName(firstname,
                lastname);

        List<PersonGeneralDTO> res = mapperService.getPersonsInfo(persons);
        if (res.isEmpty()) {
            logger.error("Aucune personne à été trouvé pour la personne correspondant");
            throw new NotFoundException("No data found");
        }
        logger.info(
                "Doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, posologie, allergies) de chaque habitant.");
        return res;
    }

    /**
     * Doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
     *
     * @param address
     * @return
     * @throws IOException
     */
    public List<ChildAlertDTO> getChildAlert(String address) throws IOException {
        List<Person> persons = personRepository.getPersonByAddress(address);
        List<ChildAlertDTO> childAlertDtos = new ArrayList<>();
        List<Person> listAdults = new ArrayList<>();
        List<ChildDTO> listChildren = new ArrayList<>();
        ChildAlertDTO childAlertDto = new ChildAlertDTO();
        List<PersonDTO> personDTOList = mapperService.getAllInfoOfPerson(persons);
        if (!personDTOList.isEmpty()) {
            for (PersonDTO p : personDTOList) {
                if (p.getAge() < 18) {
                    ChildDTO child = new ChildDTO();
                    child.setFirstName(p.getFirstName());
                    child.setLastName(p.getLastName());
                    child.setAge(p.getAge());
                    listChildren.add(child);
                } else {
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
        } else {
            logger.error("Aucune enfant à été trouvé pour l'adresse correspondant");
            throw new NotFoundException("No children found");
        }
        logger.info(
                "Doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.");

        return childAlertDtos;
    }

    public Person update(String firstname, String lastname,
                         UpdatePersonDTO updatePersonDTO) {
        Person personToUpdate =
                personRepository.getPersonByFirstnameLastName(firstname, lastname);
        if (!updatePersonDTO.getAddress().isEmpty()) {
            personToUpdate.setAddress(updatePersonDTO.getAddress());
        }
        if (!updatePersonDTO.getEmail().isEmpty()) {
            personToUpdate.setEmail(updatePersonDTO.getEmail());
        }
        if (!updatePersonDTO.getCity().isEmpty()) {
            personToUpdate.setCity(updatePersonDTO.getCity());
        }
        if (!updatePersonDTO.getZip().isEmpty()) {
            personToUpdate.setZip(updatePersonDTO.getZip());
        }
        if (!updatePersonDTO.getPhone().isEmpty()) {
            personToUpdate.setPhone(updatePersonDTO.getPhone());
        }
        return personToUpdate;
    }

    public Person getPersonByFirstnameLastName(String firstname, String lastname) {
        return personRepository.getPersonByFirstnameLastName(firstname, lastname);

    }

    public Person ajouter(Person person) {
        return personRepository.ajouter(person);
    }

    public List<Person> delete(String firstname, String lastname) {
        Person personToDel =
                personRepository.getPersonByFirstnameLastName(firstname, lastname);
        return personRepository.remove(personToDel);

    }


}

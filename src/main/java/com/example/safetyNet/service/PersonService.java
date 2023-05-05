package com.example.safetyNet.service;

import com.example.safetyNet.dto.*;
import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PersonService {
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


    public List<String> getAllEmails(String city) {
        List<String> emails = new ArrayList<>();
        List<Person> persons = personRepository.getPersonByCity(city);

        if (persons.isEmpty()) {
            throw new NotFoundException("No data found");
        } else {
            for (Person p : persons) {
                emails.add(p.getEmail());
            }
        }
        return emails;
    }

    public List<PersonGeneralDto> getPersonInfo(String firstname, String lastname)
            throws IOException {
        List<Person> persons = personRepository.getPersonsByFirstnameLastName(firstname,
                lastname);

        List<PersonGeneralDto> res = mapperService.getPersonsInfo(persons);
        if (res.isEmpty()) {
            throw new NotFoundException("No data found");
        }
        return res;
    }

    public List<ChildAlertDto> getChildAlert(String address) throws IOException {
        List<Person> persons = personRepository.getPersonByAddress(address);
        List<ChildAlertDto> childAlertDtos = new ArrayList<>();
        List<Person> listAdults = new ArrayList<>();
        List<Child> listChildren = new ArrayList<>();
        ChildAlertDto childAlertDto = new ChildAlertDto();
        List<PersonDTO> personDTOList = mapperService.getAllInfoOfPerson(persons);
        if (!personDTOList.isEmpty()) {
            for (PersonDTO p : personDTOList) {
                if (p.getAge() < 18) {
                    Child child = new Child();
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
            throw new NotFoundException("No children found");
        }

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

    public List<Person> ajouter(Person person) {
        return personRepository.ajouter(person);
    }

    public List<Person> delete(String firstname, String lastname) {
        Person personToDel =
                personRepository.getPersonByFirstnameLastName(firstname, lastname);
        return personRepository.remove(personToDel);

    }


}

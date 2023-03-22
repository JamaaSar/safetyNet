package com.example.safetyNet.service;

import com.example.safetyNet.dto.PersonDTO;
import com.example.safetyNet.dto.PersonInfoDto;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.service.CalculateAge;
import com.example.safetyNet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService {

    @Autowired
    MedicalRecordService medicalRecordService;


    public List<PersonDTO> getAllInfoOfPerson(List<Person> data) throws IOException {
        List<PersonDTO> res = new ArrayList<>();
        for (Person p : data){
            PersonDTO personDTO = new PersonDTO();
            MedicalRecord medicalRecord = medicalRecordService.getAllMedicalRecordsByName(p.getFirstName());
            personDTO.setFirstName(p.getFirstName());
            personDTO.setLastName(p.getLastName());
            personDTO.setAddress(p.getAddress());
            personDTO.setAge(CalculateAge.calculateAge(medicalRecord.getBirthdate()));
            personDTO.setEmail(p.getEmail());
            personDTO.setCity(p.getCity());
            personDTO.setZip(p.getZip());
            personDTO.setPhone(p.getPhone());
            personDTO.setMedications(medicalRecord.getMedications());
            personDTO.setAllergies(medicalRecord.getAllergies());
            res.add(personDTO);
        }
        return res;
    }
    public List<PersonInfoDto> getPersonsInfo(List<Person> data) throws IOException {
        List<PersonInfoDto> res = new ArrayList<>();
        for (Person p : data){
            PersonInfoDto personInfoDto = new PersonInfoDto();
            MedicalRecord medicalRecord = medicalRecordService.getAllMedicalRecordsByName(p.getFirstName());
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

package com.example.safetyNet.outil;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CalculateAge {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static int calculateAge(String date) throws DateTimeException {
        LocalDate birthdate = LocalDate.parse(date, formatter);
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

}

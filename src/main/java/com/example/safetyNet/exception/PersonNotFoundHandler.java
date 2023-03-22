package com.example.safetyNet.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class PersonNotFoundHandler extends ChangeSetPersister.NotFoundException {

    public PersonNotFoundHandler(){
        super();
    }

//https://zetcode.com/springboot/controlleradvice/

}

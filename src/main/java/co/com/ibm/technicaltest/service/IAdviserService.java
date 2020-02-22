package co.com.ibm.technicaltest.service;

import co.com.ibm.technicaltest.dto.Adviser;
import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.exception.NotFoundException;

import java.util.List;

public interface IAdviserService {

    List<Adviser> getAllAdvisers();

    Adviser getAdviserById(long id) throws NotFoundException;

    Adviser createOrUpdateAdviser(Adviser entity) throws NotFoundException;

    void deleteAdviserById(Long id) throws NotFoundException;
}

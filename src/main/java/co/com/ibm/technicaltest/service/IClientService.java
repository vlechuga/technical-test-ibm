package co.com.ibm.technicaltest.service;

import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;

import java.util.List;

public interface IClientService {

    List<Client> getAllClients();

    Client getClientById(long id) throws NotFoundException;

    Client createOrUpdateClient(Client entity) throws NotFoundException;

    void deleteClientById(Long id) throws NotFoundException, ConstraintException;
}

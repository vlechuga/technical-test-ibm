package co.com.ibm.technicaltest.service.impl;


import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.dto.mapper.ClientEntityMapper;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.repository.ClientRepository;
import co.com.ibm.technicaltest.entity.ClientEntity;
import co.com.ibm.technicaltest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> getAllClients()  {
        List<ClientEntity> clients = repository.findAll();

        if(clients.size() > 0) {
            return clients.stream().map(jpa -> ClientEntityMapper.toClientDto(jpa)).collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Client getClientById(long id) throws NotFoundException {
        Optional<ClientEntity> client = repository.findById(id);

        if(client.isPresent()) {
            return ClientEntityMapper.toClientDto(client.get());
        } else {
            throw new NotFoundException("No found client");
        }
    }

    @Override
    public Client createOrUpdateClient(Client entity) {
        Optional<ClientEntity> client = repository.findById(entity.getId());

        if(client.isPresent()) {
            ClientEntity newEntity = client.get();
            newEntity.setName(entity.getName());
            newEntity.setAddress(entity.getAddress());
            newEntity.setCity(entity.getCity());
            newEntity.setPhone(entity.getPhone());

            newEntity = repository.save(newEntity);

            return ClientEntityMapper.toClientDto(newEntity);
        } else {
            return ClientEntityMapper.toClientDto(repository.save(ClientEntityMapper.toClientJpa(entity)));
        }
    }

    @Override
    public void deleteClientById(Long id) throws NotFoundException {
        Optional<ClientEntity> client = repository.findById(id);

        if(client.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No found client");
        }
    }
}

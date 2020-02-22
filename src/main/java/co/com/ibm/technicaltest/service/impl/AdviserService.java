package co.com.ibm.technicaltest.service.impl;


import co.com.ibm.technicaltest.dto.Adviser;
import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.dto.mapper.AdviserEntityMapper;
import co.com.ibm.technicaltest.dto.mapper.ClientEntityMapper;
import co.com.ibm.technicaltest.entity.AdviserEntity;
import co.com.ibm.technicaltest.entity.ClientEntity;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.repository.AdviserRepository;
import co.com.ibm.technicaltest.repository.ClientRepository;
import co.com.ibm.technicaltest.service.IAdviserService;
import co.com.ibm.technicaltest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdviserService implements IAdviserService {

    @Autowired
    private AdviserRepository repository;

    @Override
    public List<Adviser> getAllAdvisers()  {
        List<AdviserEntity> advisers = repository.findAll();

        if(advisers.size() > 0) {
            return advisers.stream().map(jpa -> AdviserEntityMapper.toClientDto(jpa)).collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Adviser getAdviserById(long id) throws NotFoundException {
        Optional<AdviserEntity> adviser = repository.findById(id);

        if(adviser.isPresent()) {
            return AdviserEntityMapper.toClientDto(adviser.get());
        } else {
            throw new NotFoundException("No found Adviser");
        }
    }

    @Override
    public Adviser createOrUpdateAdviser(Adviser entity) {
        Optional<AdviserEntity> adviser = repository.findById(entity.getId());

        if(adviser.isPresent()) {
            AdviserEntity newEntity = adviser.get();
            newEntity.setName(entity.getName());
            newEntity.setSpecialty(entity.getSpecialty());

            newEntity = repository.save(newEntity);

            return AdviserEntityMapper.toClientDto(newEntity);
        } else {
            return AdviserEntityMapper.toClientDto(repository.save(AdviserEntityMapper.toClientJpa(entity)));
        }
    }

    @Override
    public void deleteAdviserById(Long id) throws NotFoundException {
        Optional<AdviserEntity> adviser = repository.findById(id);

        if(adviser.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No found adviser");
        }
    }
}

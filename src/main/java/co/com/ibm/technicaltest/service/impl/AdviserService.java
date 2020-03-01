package co.com.ibm.technicaltest.service.impl;


import co.com.ibm.technicaltest.dto.Adviser;
import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.dto.mapper.AdviserEntityMapper;
import co.com.ibm.technicaltest.dto.mapper.ClientEntityMapper;
import co.com.ibm.technicaltest.entity.AdviserEntity;
import co.com.ibm.technicaltest.entity.ClientEntity;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.repository.AdviserRepository;
import co.com.ibm.technicaltest.repository.ClientRepository;
import co.com.ibm.technicaltest.service.IAdviserService;
import co.com.ibm.technicaltest.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdviserService implements IAdviserService {

    private static final Logger logger = LoggerFactory.getLogger(AdviserService.class);

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
        Optional<AdviserEntity> adviser = Optional.empty();

        if(Objects.nonNull(entity.getId())) {
            adviser = repository.findById(entity.getId());
        }

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
    public void deleteAdviserById(Long id) throws NotFoundException, ConstraintException {
        Optional<AdviserEntity> adviser = repository.findById(id);

        if(adviser.isPresent()) {
            try {
            repository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                logger.error("deleteAdviserById", e);
                throw new ConstraintException(e.toString());
            }
        } else {
            throw new NotFoundException("No found adviser");
        }
    }
}

package co.com.ibm.technicaltest.service.impl;


import co.com.ibm.technicaltest.dto.Audit;
import co.com.ibm.technicaltest.dto.mapper.AuditEntityMapper;
import co.com.ibm.technicaltest.entity.AuditEntity;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.repository.AuditRepository;
import co.com.ibm.technicaltest.service.IAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuditService implements IAuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditRepository repository;

    @Override
    public List<Audit> getAllAudits()  {
        List<AuditEntity> audits = repository.findAll();

        if(audits.size() > 0) {
            return audits.stream().map(jpa -> AuditEntityMapper.toAuditDto(jpa)).collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Audit getAuditById(long id) throws NotFoundException {
        Optional<AuditEntity> audit = repository.findById(id);

        if(audit.isPresent()) {
            return AuditEntityMapper.toAuditDto(audit.get());
        } else {
            throw new NotFoundException("No found Audit");
        }
    }

    @Override
    public Audit createAudit(Audit entity) {
        return AuditEntityMapper.toAuditDto(repository.save(AuditEntityMapper.toAuditJpa(entity)));
    }

    @Override
    public void deleteAuditById(Long id) throws NotFoundException, ConstraintException {
        Optional<AuditEntity> audit = repository.findById(id);

        if(audit.isPresent()) {
            try {
            repository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                logger.error("deleteClientById", e);
                throw new ConstraintException(e.toString());
            }
        } else {
            throw new NotFoundException("No found Audit");
        }
    }
}

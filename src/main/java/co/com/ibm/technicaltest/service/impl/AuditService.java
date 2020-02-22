package co.com.ibm.technicaltest.service.impl;


import co.com.ibm.technicaltest.dto.Audit;
import co.com.ibm.technicaltest.dto.mapper.AuditEntityMapper;
import co.com.ibm.technicaltest.entity.AuditEntity;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.repository.AuditRepository;
import co.com.ibm.technicaltest.service.IAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuditService implements IAuditService {

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
    public void deleteAuditById(Long id) throws NotFoundException {
        Optional<AuditEntity> audit = repository.findById(id);

        if(audit.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No found Audit");
        }
    }
}

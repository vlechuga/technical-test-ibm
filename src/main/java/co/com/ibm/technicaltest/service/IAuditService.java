package co.com.ibm.technicaltest.service;

import co.com.ibm.technicaltest.dto.Audit;
import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;

import java.util.List;

public interface IAuditService {

    List<Audit> getAllAudits();

    Audit getAuditById(long id) throws NotFoundException;

    Audit createAudit(Audit entity);

    void deleteAuditById(Long id) throws NotFoundException, ConstraintException;
}

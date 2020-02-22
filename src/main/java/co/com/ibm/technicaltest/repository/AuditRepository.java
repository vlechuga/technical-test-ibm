package co.com.ibm.technicaltest.repository;

import co.com.ibm.technicaltest.entity.AuditEntity;
import co.com.ibm.technicaltest.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {

}

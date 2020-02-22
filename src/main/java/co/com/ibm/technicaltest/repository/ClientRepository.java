package co.com.ibm.technicaltest.repository;

import co.com.ibm.technicaltest.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}

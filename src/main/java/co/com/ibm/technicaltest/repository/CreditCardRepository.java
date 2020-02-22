package co.com.ibm.technicaltest.repository;

import co.com.ibm.technicaltest.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

    Optional<CreditCardEntity> findByNumber(String number);

}

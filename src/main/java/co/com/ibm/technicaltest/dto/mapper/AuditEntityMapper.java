package co.com.ibm.technicaltest.dto.mapper;

import co.com.ibm.technicaltest.dto.Audit;
import co.com.ibm.technicaltest.dto.CreditCard;
import co.com.ibm.technicaltest.entity.AuditEntity;
import co.com.ibm.technicaltest.entity.CreditCardEntity;

public class AuditEntityMapper {

    public static Audit toAuditDto(AuditEntity jpa) {
        Audit audit = new Audit();
        audit.setId(jpa.getId());
        audit.setDescription(jpa.getDescription());
        audit.setAmount(jpa.getAmount());
        audit.setCreationDate(jpa.getCreationDate());
        audit.setCard(CreditCardEntityMapper.toCreditCardDto(jpa.getCard()));
        return audit;
    }

    public static AuditEntity toAuditJpa(Audit audit) {
        AuditEntity jpa = new AuditEntity();
        jpa.setId(audit.getId());
        jpa.setDescription(audit.getDescription());
        jpa.setAmount(audit.getAmount());
        jpa.setCard(CreditCardEntityMapper.toCreditCardJpa(audit.getCard()));
        return jpa;
    }
}

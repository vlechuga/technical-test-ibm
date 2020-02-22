package co.com.ibm.technicaltest.dto.mapper;

import co.com.ibm.technicaltest.dto.CreditCard;
import co.com.ibm.technicaltest.entity.CreditCardEntity;

public class CreditCardEntityMapper {

    public static CreditCard toCreditCardDto(CreditCardEntity jpa) {
        CreditCard a = new CreditCard();
        a.setId(jpa.getId());
        a.setNumber(jpa.getNumber());
        a.setType(jpa.getType());
        a.setClient(ClientEntityMapper.toShortClientDto(jpa.getClient()));
        return a;
    }

    public static CreditCardEntity toCreditCardJpa(CreditCard cc) {
        CreditCardEntity jpa = new CreditCardEntity();
        jpa.setId(cc.getId());
        jpa.setNumber(cc.getNumber());
        jpa.setType(cc.getType());
        jpa.setClient(ClientEntityMapper.toClientJpa(cc.getClient()));
        return jpa;
    }
}

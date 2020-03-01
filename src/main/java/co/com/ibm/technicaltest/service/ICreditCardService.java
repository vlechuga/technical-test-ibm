package co.com.ibm.technicaltest.service;

import co.com.ibm.technicaltest.dto.CreditCard;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;

import java.util.List;

public interface ICreditCardService {

    List<CreditCard> getAllCreditCards();

    CreditCard getCreditCardById(long id) throws NotFoundException;

    CreditCard getCreditCardByNumber(String number) throws NotFoundException;

    CreditCard createOrUpdateCreditCard(CreditCard entity) throws NotFoundException;

    void deleteCreditCardById(Long id) throws NotFoundException, ConstraintException;


}

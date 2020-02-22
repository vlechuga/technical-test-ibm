package co.com.ibm.technicaltest.service.impl;


import co.com.ibm.technicaltest.dto.CreditCard;
import co.com.ibm.technicaltest.dto.mapper.CreditCardEntityMapper;
import co.com.ibm.technicaltest.entity.CreditCardEntity;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.repository.CreditCardRepository;
import co.com.ibm.technicaltest.service.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    private CreditCardRepository repository;

    @Override
    public List<CreditCard> getAllCreditCards()  {
        List<CreditCardEntity> creditCards = repository.findAll();

        if(creditCards.size() > 0) {
            return creditCards.stream().map(jpa -> CreditCardEntityMapper.toCreditCardDto(jpa)).collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public CreditCard getCreditCardById(long id) throws NotFoundException {
        Optional<CreditCardEntity> creditCard = repository.findById(id);

        if(creditCard.isPresent()) {
            return CreditCardEntityMapper.toCreditCardDto(creditCard.get());
        } else {
            throw new NotFoundException("No found CreditCard");
        }
    }

    @Override
    public CreditCard getCreditCardByNumber(String number) throws NotFoundException {
        Optional<CreditCardEntity> creditCard = repository.findByNumber(number);

        if(creditCard.isPresent()) {
            return CreditCardEntityMapper.toCreditCardDto(creditCard.get());
        } else {
            throw new NotFoundException("No found CreditCard");
        }
    }

    @Override
    public CreditCard createOrUpdateCreditCard(CreditCard entity) {
        Optional<CreditCardEntity> creditCard = repository.findById(entity.getId());

        if(creditCard.isPresent()) {
            return CreditCardEntityMapper.toCreditCardDto(repository.save(CreditCardEntityMapper.toCreditCardJpa(entity)));
        } else {
            return CreditCardEntityMapper.toCreditCardDto(repository.save(CreditCardEntityMapper.toCreditCardJpa(entity)));
        }
    }

    @Override
    public void deleteCreditCardById(Long id) throws NotFoundException {
        Optional<CreditCardEntity> creditCard = repository.findById(id);

        if(creditCard.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No found CreditCard");
        }
    }
}

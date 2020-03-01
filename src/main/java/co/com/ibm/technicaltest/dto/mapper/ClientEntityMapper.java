package co.com.ibm.technicaltest.dto.mapper;

import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.entity.ClientEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientEntityMapper {

    public static Client toClientDto(ClientEntity jpa) {
        Client c = new Client();
        c.setId(jpa.getId());
        c.setName(jpa.getName());
        c.setAddress(jpa.getAddress());
        c.setCity(jpa.getCity());
        c.setPhone(jpa.getPhone());

        if(Objects.nonNull(jpa.getCards())) {
            final List list = jpa.getCards().stream().map(item -> CreditCardEntityMapper.toShortCreditCardDto(item)).collect(Collectors.toList());
            c.setCards(list);
        }
        return c;
    }

    public static Client toShortClientDto(ClientEntity jpa) {
        Client c = new Client();
        c.setId(jpa.getId());
        c.setName(jpa.getName());
        return c;
    }

    public static ClientEntity toClientJpa(Client client) {
        ClientEntity jpa = new ClientEntity();
        jpa.setId(client.getId());
        jpa.setName(client.getName());
        jpa.setAddress(client.getAddress());
        jpa.setCity(client.getCity());
        jpa.setPhone(client.getPhone());
        return jpa;
    }
}

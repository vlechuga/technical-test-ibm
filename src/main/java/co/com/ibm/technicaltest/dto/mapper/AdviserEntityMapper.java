package co.com.ibm.technicaltest.dto.mapper;

import co.com.ibm.technicaltest.dto.Adviser;
import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.entity.AdviserEntity;
import co.com.ibm.technicaltest.entity.ClientEntity;

public class AdviserEntityMapper {

    public static Adviser toClientDto(AdviserEntity jpa) {
        Adviser a = new Adviser();
        a.setId(jpa.getId());
        a.setName(jpa.getName());
        a.setSpecialty(jpa.getSpecialty());
        return a;
    }

    public static AdviserEntity toClientJpa(Adviser adviser) {
        AdviserEntity jpa = new AdviserEntity();
        jpa.setId(adviser.getId());
        jpa.setName(adviser.getName());
        jpa.setSpecialty(adviser.getSpecialty());
        return jpa;
    }
}

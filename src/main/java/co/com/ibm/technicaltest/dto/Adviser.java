package co.com.ibm.technicaltest.dto;

import java.io.Serializable;

public class Adviser implements Serializable {

    private long id;
    private String name;
    private String specialty;


    public Adviser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}

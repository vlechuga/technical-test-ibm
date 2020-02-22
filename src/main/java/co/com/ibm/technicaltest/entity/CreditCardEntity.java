package co.com.ibm.technicaltest.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="card")
public class CreditCardEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Size(max = 16)
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private String number;

    @Size(max = 50)
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private ClientEntity client;


    public CreditCardEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}

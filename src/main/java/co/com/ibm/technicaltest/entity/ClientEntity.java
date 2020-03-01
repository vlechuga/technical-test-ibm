package co.com.ibm.technicaltest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="client")
@ApiModel(description = "All details about the Client.")
public class ClientEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    @ApiModelProperty(notes = "The database generated client ID")
    private Long id;

    @Size(max = 50)
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    @ApiModelProperty(notes = "The client name")
    private String name;

    @Size(max = 100)
    @Basic(optional = false)
    @NotNull
    @Column(name = "address")
    @ApiModelProperty(notes = "The client address")
    private String address;

    @Size(max = 30)
    @Basic(optional = false)
    @NotNull
    @Column(name = "city")
    @ApiModelProperty(notes = "The client city")
    private String city;

    @Size(max = 20)
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone")
    @ApiModelProperty(notes = "The client phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    private List<CreditCardEntity> cards;

    public ClientEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CreditCardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CreditCardEntity> cards) {
        this.cards = cards;
    }
}

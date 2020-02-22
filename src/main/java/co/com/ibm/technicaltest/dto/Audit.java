package co.com.ibm.technicaltest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Audit implements Serializable {

    private Long id;
    private String description;
    private BigDecimal amount;
    private Date creationDate;
    private CreditCard card;

    public Audit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }
}

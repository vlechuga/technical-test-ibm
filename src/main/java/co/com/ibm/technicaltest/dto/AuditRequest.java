package co.com.ibm.technicaltest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AuditRequest implements Serializable {

    private Long clientId;
    private String cardNumber;
    private BigDecimal amount;
    private String description;

    public AuditRequest() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

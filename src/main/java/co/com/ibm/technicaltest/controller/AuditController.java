package co.com.ibm.technicaltest.controller;


import co.com.ibm.technicaltest.dto.Audit;
import co.com.ibm.technicaltest.dto.AuditRequest;
import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.dto.CreditCard;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.service.impl.AuditService;
import co.com.ibm.technicaltest.service.impl.ClientService;
import co.com.ibm.technicaltest.service.impl.CreditCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
@Api(value="Audit Management System", description="Operations pertaining to Audit")
public class AuditController {

    @Autowired
    AuditService auditService;

    @Autowired
    ClientService clientService;

    @Autowired
    CreditCardService creditCardService;

    @GetMapping
    @ApiOperation(value = "View a list of available Audit", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public ResponseEntity<List<Audit>> getAllAudits() {
        List<Audit> list = auditService.getAllAudits();

        return new ResponseEntity<List<Audit>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a Audit by ID", response = CreditCard.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Audit> getAuditById(@PathVariable("id") Long id) throws NotFoundException {
        Audit dto = auditService.getAuditById(id);

        return new ResponseEntity<Audit>(dto, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a Audit", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 412, message = "Precondition Failed")
    })
    public ResponseEntity<Audit> createCreditCard(AuditRequest auditRequest) throws NotFoundException {

        Client client = clientService.getClientById(auditRequest.getClientId());
        CreditCard creditCard = creditCardService.getCreditCardByNumber(auditRequest.getCardNumber());

        if(creditCard.getClient().getId() != auditRequest.getClientId()) {
            return new ResponseEntity<Audit>(null, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
        }

        Audit audit = new Audit();
        audit.setCard(creditCard);
        audit.setAmount(auditRequest.getAmount());
        audit.setDescription(auditRequest.getDescription());

        Audit updated = auditService.createAudit(audit);
        return new ResponseEntity<Audit>(updated, new HttpHeaders(), HttpStatus.OK);
}

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a Audit by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public HttpStatus deleteAuditById(@PathVariable("id") Long id) throws NotFoundException {
        auditService.deleteAuditById(id);
        return HttpStatus.OK;
    }
}

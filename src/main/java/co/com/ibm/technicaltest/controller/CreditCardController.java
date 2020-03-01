package co.com.ibm.technicaltest.controller;


import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.dto.CreditCard;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;
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
@RequestMapping("/credit-cards")
@Api(value="CreditCard Management System", description="Operations pertaining to CreditCard")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @GetMapping
    @ApiOperation(value = "View a list of available CreditCards", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        List<CreditCard> list = creditCardService.getAllCreditCards();

        return new ResponseEntity<List<CreditCard>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a CreditCard by ID", response = CreditCard.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable("id") Long id) throws NotFoundException {
        CreditCard dto = creditCardService.getCreditCardById(id);

        return new ResponseEntity<CreditCard>(dto, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a CreditCard", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<CreditCard> createOrUpdateCreditCard(@RequestBody CreditCard creditCard) {
        CreditCard updated = creditCardService.createOrUpdateCreditCard(creditCard);
        return new ResponseEntity<CreditCard>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edit a CreditCard", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable("id") Long id, @RequestBody CreditCard creditCard) {
        creditCard.setId(id);
        CreditCard updated = creditCardService.createOrUpdateCreditCard(creditCard);
        return new ResponseEntity<CreditCard>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a CreditCard by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 412, message = "Precondition Failed")
    })
    public HttpStatus deleteClientById(@PathVariable("id") Long id) throws NotFoundException, ConstraintException {
        creditCardService.deleteCreditCardById(id);
        return HttpStatus.OK;
    }
}

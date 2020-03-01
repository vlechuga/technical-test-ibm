package co.com.ibm.technicaltest.controller;


import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.service.impl.ClientService;
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
@RequestMapping("/clients")
@Api(value="Client Management System", description="Operations pertaining to client in Client Management System")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    @ApiOperation(value = "View a list of available clients", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> list = clientService.getAllClients();

        return new ResponseEntity<List<Client>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a Client by ID", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) throws NotFoundException {
        Client dto = clientService.getClientById(id);

        return new ResponseEntity<Client>(dto, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a Client", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client) {
        Client updated = clientService.createOrUpdateClient(client);
        return new ResponseEntity<Client>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edit a Client", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Client> createOrUpdateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        client.setId(id);
        Client updated = clientService.createOrUpdateClient(client);
        return new ResponseEntity<Client>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a Client by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 412, message = "Precondition Failed")
    })
    public HttpStatus deleteClientById(@PathVariable("id") Long id) throws NotFoundException, ConstraintException {
        clientService.deleteClientById(id);
        return HttpStatus.OK;
    }
}

package co.com.ibm.technicaltest.controller;


import co.com.ibm.technicaltest.dto.Adviser;
import co.com.ibm.technicaltest.dto.Client;
import co.com.ibm.technicaltest.exception.ConstraintException;
import co.com.ibm.technicaltest.exception.NotFoundException;
import co.com.ibm.technicaltest.service.impl.AdviserService;
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
@RequestMapping("/advisers")
@Api(value="Adviser Management System", description="Operations pertaining to Adviser")
public class AdviserController {

    @Autowired
    AdviserService adviserService;

    @GetMapping
    @ApiOperation(value = "View a list of available Adviser", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public ResponseEntity<List<Adviser>> getAllAdvisers() {
        List<Adviser> list = adviserService.getAllAdvisers();

        return new ResponseEntity<List<Adviser>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a Adviser by ID", response = Adviser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Adviser> getEmployeeById(@PathVariable("id") Long id) throws NotFoundException {
        Adviser dto = adviserService.getAdviserById(id);

        return new ResponseEntity<Adviser>(dto, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a Adviser", response = Adviser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Adviser> createAdviser(@RequestBody Adviser adviser) {
        Adviser updated = adviserService.createOrUpdateAdviser(adviser);
        return new ResponseEntity<Adviser>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edit a Adviser", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Adviser> updateAdviser(@PathVariable("id") Long id, @RequestBody Adviser adviser) {
        adviser.setId(id);
        Adviser updated = adviserService.createOrUpdateAdviser(adviser);
        return new ResponseEntity<Adviser>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a Adviser by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 412, message = "Precondition Failed")
    })
    public HttpStatus deleteAdviserById(@PathVariable("id") Long id) throws NotFoundException, ConstraintException {
        adviserService.deleteAdviserById(id);
        return HttpStatus.OK;
    }
}

package com.uter.controller;

import com.uter.entities.Customer;
import com.uter.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@Api(tags="Customer", value = "Servicio Web RESTFul de Customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar Customers", notes="Método para listar todos los customers")
    @ApiResponses({
            @ApiResponse(code=201, message = "Customers encontrados"),
            @ApiResponse(code=404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findALL() {
        try {
            List<Customer> customers = customerService.getAll();
            if(customers.size()>0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Customer por Id", notes = "Método para encontrar un customer por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "Customer encontrado"),
            @ApiResponse(code=404, message = "Customer no encontrado")
    })
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerService.getById(id);
            if(!customer.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Customer por Username", notes = "Método para encontrar un customer por su respectivo Username")
    @ApiResponses({
            @ApiResponse(code=201, message = "Customer encontrado"),
            @ApiResponse(code=404, message = "Customer no encontrado")
    })
    public ResponseEntity<Customer> findByUsername(@PathVariable("username") String username){
        try{
            Customer customer = customerService.findByUsername(username);
            if(customer==null)
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Customer>(customer,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Customers por Name", notes = "Método para encontrar customers por su respectivo Name")
    @ApiResponses({
            @ApiResponse(code=201, message = "Customers encontrados"),
            @ApiResponse(code=404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByName(@PathVariable("name") String name){
        try{
            List<Customer> customers = customerService.findByName(name);
            if(customers.size()>0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Customer por email", notes = "Método para encontrar un customer por su respectivo email")
    @ApiResponses({
            @ApiResponse(code=201, message = "Customer encontrado"),
            @ApiResponse(code=404, message = "Customer no encontrado")
    })
    public ResponseEntity<List<Customer>> findByEmail(@PathVariable("email") String email){
        try{
            List<Customer> customers = customerService.findByEmail(email);
            if(customers.size()>0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByPhone/{phone}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Customers por el Phone", notes = "Método para encontrar customers por su respectivo phone")
    @ApiResponses({
            @ApiResponse(code=201, message = "Customers encontrados"),
            @ApiResponse(code=404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Customer>> findByPhone(@PathVariable("phone") String phone){
        try{
            List<Customer> customers = customerService.findByPhone(phone);
            if(customers.size()>0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Customers", notes ="Método que registra customers en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Customer creado"),
            @ApiResponse(code=404, message = "Customer no creado")
    })
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer){
        try{
            Customer customerNew = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerNew);
        }catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los Customers", notes = "Método que actualizar los datos de Customers")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Customer actualizados"),
            @ApiResponse(code=404, message = "Customer no encontrado")
    })
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") Long id, @Valid @RequestBody Customer customer) {
        try{
            Optional<Customer> customerUp = customerService.getById(id);
            if(!customerUp.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            customer.setId(id);
            customerService.save(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Customers", notes = "Método que elimina los datos de Customers")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Customer eliminados"),
            @ApiResponse(code=404, message = "Customer no encontrado")
    })
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")Long id){
        try{
            Optional<Customer> customerDelete = customerService.getById(id);
            if(!customerDelete.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

            customerService.delete(id);
            return new ResponseEntity<Customer>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


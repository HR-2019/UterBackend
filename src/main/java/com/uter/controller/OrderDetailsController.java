package com.uter.controller;

import com.uter.entities.OrderDetails;
import com.uter.service.IOrderDetailsService;
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
@RequestMapping("/api/orderDetailsdetails")
@Api(tags="OrderDetails", value = "Servicio Web RESTFul de OrderDetails")
public class OrderDetailsController {
    
    @Autowired
    private IOrderDetailsService orderDetailsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar OrderDetails", notes="Método para listar todos los orderDetailsDetails")
    @ApiResponses({
            @ApiResponse(code=201, message = "OrderDetails encontrados"),
            @ApiResponse(code=404, message = "OrderDetails no encontrados")
    })
    public ResponseEntity<List<OrderDetails>> findALL() {
        try {
            List<OrderDetails> orderDetailsDetails = orderDetailsService.getAll();
            if(orderDetailsDetails.size()>0)
                return new ResponseEntity<List<OrderDetails>>(orderDetailsDetails, HttpStatus.OK);
            else
                return new ResponseEntity<List<OrderDetails>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<OrderDetails>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar orderDetails por Id", notes = "Método para encontrar un orderDetails por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "orderDetails encontrado"),
            @ApiResponse(code=404, message = "orderDetails no encontrado")
    })
    public ResponseEntity<OrderDetails> findById(@PathVariable("id") Long id) {
        try {
            Optional<OrderDetails> orderDetails = orderDetailsService.getById(id);
            if(!orderDetails.isPresent())
                return new ResponseEntity<OrderDetails>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<OrderDetails>(orderDetails.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<OrderDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de OrderDetails", notes ="Método que registra orderDetails en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "orderDetails creado"),
            @ApiResponse(code=404, message = "orderDetails no creado")
    })
    public ResponseEntity<OrderDetails> insertOrderDetails(@Valid @RequestBody OrderDetails orderDetails){
        try{
            OrderDetails orderDetailsNew = orderDetailsService.save(orderDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsNew);
        }catch (Exception e){
            return new ResponseEntity<OrderDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los OrderDetails", notes = "Método para actualizar los datos de OrderDetails")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de orderDetails actualizados"),
            @ApiResponse(code=404, message = "orderDetails no encontrado")
    })
    public ResponseEntity<OrderDetails> updateOrder(
            @PathVariable("id") Long id, @Valid @RequestBody OrderDetails orderDetailsDetails) {
        try{
            Optional<OrderDetails> orderDetailsUp = orderDetailsService.getById(id);
            if(!orderDetailsUp.isPresent())
                return new ResponseEntity<OrderDetails>(HttpStatus.NOT_FOUND);
            orderDetailsDetails.setId(id);
            orderDetailsService.save(orderDetailsDetails);
            return new ResponseEntity<OrderDetails>(orderDetailsDetails, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<OrderDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de OrderDetails", notes = "Método que elimina los datos de OrderDetails")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de orderDetails eliminados"),
            @ApiResponse(code=404, message = "orderDetails no encontrado")
    })
    public ResponseEntity<OrderDetails> deleteOrder(@PathVariable("id")Long id){
        try{
            Optional<OrderDetails> orderDetailsDelete = orderDetailsService.getById(id);
            if(!orderDetailsDelete.isPresent())
                return new ResponseEntity<OrderDetails>(HttpStatus.NOT_FOUND);

            orderDetailsService.delete(id);
            return new ResponseEntity<OrderDetails>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<OrderDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

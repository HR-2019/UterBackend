package com.uter.controller;

import com.uter.entities.Orders;
import com.uter.service.IOrdersService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags="Orders", value = "Servicio Web RESTFul de Orders")
public class OrdersController {
    
    @Autowired
    private IOrdersService orderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar Orders", notes="Método para listar todos los orders")
    @ApiResponses({
            @ApiResponse(code=201, message = "Orders encontrados"),
            @ApiResponse(code=404, message = "Orders no encontrados")
    })
    public ResponseEntity<List<Orders>> findALL() {
        try {
            List<Orders> orders = orderService.getAll();
            if(orders.size()>0)
                return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
            else
                return new ResponseEntity<List<Orders>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Order por Id", notes = "Método para encontrar un order por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "Order encontrado"),
            @ApiResponse(code=404, message = "Order no encontrado")
    })
    public ResponseEntity<Orders> findById(@PathVariable("id") Long id) {
        try {
            Optional<Orders> order = orderService.getById(id);
            if(!order.isPresent())
                return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Orders>(order.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Orders>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Orders", notes ="Método que registra orders en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Order creado"),
            @ApiResponse(code=404, message = "Order no creado")
    })
    public ResponseEntity<Orders> insertOrder(@Valid @RequestBody Orders orders){
        try{
            Orders ordersNew = orderService.save(orders);
            return ResponseEntity.status(HttpStatus.CREATED).body(ordersNew);
        }catch (Exception e){
            return new ResponseEntity<Orders>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los Orders", notes = "Método para actualizar los datos de Orders")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Order actualizados"),
            @ApiResponse(code=404, message = "Order no encontrado")
    })
    public ResponseEntity<Orders> updateOrder(
            @PathVariable("id") Long id, @Valid @RequestBody Orders orders) {
        try{
            Optional<Orders> orderUp = orderService.getById(id);
            if(!orderUp.isPresent())
                return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
            orders.setId(id);
            orderService.save(orders);
            return new ResponseEntity<Orders>(orders, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Orders>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Orders", notes = "Método que elimina los datos de Orders")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Order eliminados"),
            @ApiResponse(code=404, message = "Order no encontrado")
    })
    public ResponseEntity<Orders> deleteOrder(@PathVariable("id")Long id){
        try{
            Optional<Orders> orderDelete = orderService.getById(id);
            if(!orderDelete.isPresent())
                return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);

            orderService.delete(id);
            return new ResponseEntity<Orders>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Orders>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchBetweenDates", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Orders entre fechas", notes="Método para listar orders entre fechas")
    @ApiResponses({
            @ApiResponse(code=201, message = "Orders encontrados"),
            @ApiResponse(code=404, message = "Orders no encontrados")
    })
    public ResponseEntity<List<Orders>> findOrdersBetweenDates(String fromDateString, String toDateString){
        try{
            Date fromDate = ParseDate(fromDateString);
            Date toDate = ParseDate(toDateString);
            List<Orders> orders = orderService.findBetweenDates(fromDate,toDate);
            if(orders.size() > 0)
                return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
            else
                return new ResponseEntity<List<Orders>>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception ex){
        }
        return result;
    }
    
}

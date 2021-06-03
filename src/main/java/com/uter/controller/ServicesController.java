package com.uter.controller;

import com.uter.entities.Services;
import com.uter.service.IServicesService;
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
@RequestMapping("/api/services")
@Api(tags="Services", value = "Servicio Web RESTFul de Services")
public class ServicesController {
    @Autowired
    private IServicesService servicesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar Services", notes="Método para listar todos los Services")
    @ApiResponses({
            @ApiResponse(code=201, message = "Services encontrados"),
            @ApiResponse(code=404, message = "Services no encontrados")
    })
    public ResponseEntity<List<Services>> findALL() {
        try {
            List<Services> services = servicesService.getAll();
            if(services.size()>0)
                return new ResponseEntity<List<Services>>(services, HttpStatus.OK);
            else
                return new ResponseEntity<List<Services>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Services>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="searchByDescription/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Services por description", notes = "Método para encontrar Services por su respectivo description")
    @ApiResponses({
            @ApiResponse(code=201, message = "Services encontrados"),
            @ApiResponse(code=404, message = "Services no encontrados")
    })
    public ResponseEntity<List<Services>> findDescription(@PathVariable("description") String description){
        try{
            List<Services> services = servicesService.findDescriptions(description);
            if(services.size()>0)
                return new ResponseEntity<List<Services>>(services, HttpStatus.OK);
            else
                return new ResponseEntity<List<Services>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Services>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Services por su name", notes = "Método para encontrar services por sus respectivo name")
    @ApiResponses({
            @ApiResponse(code=201, message = "Services encontrados"),
            @ApiResponse(code=404, message = "Services no encontrados")
    })
    public ResponseEntity<List<Services>> findByName(@PathVariable("name") String name){
        try{
            List<Services> services = servicesService.findByName(name);
            if(services.size()>0)
                return new ResponseEntity<List<Services>>(services, HttpStatus.OK);
            else
                return new ResponseEntity<List<Services>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Services>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Services", notes ="Método que registra Services en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Services creado"),
            @ApiResponse(code=404, message = "Services no creado")
    })
    public ResponseEntity<Services> insertServices(@Valid @RequestBody Services services){
        try{
            Services servicesNew = servicesService.save(services);
            return ResponseEntity.status(HttpStatus.CREATED).body(servicesNew);
        }catch (Exception e){
            return new ResponseEntity<Services>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los Services", notes = "Método que actualizar los datos de los Services")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de los Services actualizados"),
            @ApiResponse(code=404, message = "Services no encontrado")
    })
    public ResponseEntity<Services> updateServices(
            @PathVariable("id") Long id, @Valid @RequestBody Services services) {
        try{
            Optional<Services> servicesUp = servicesService.getById(id);
            if(!servicesUp.isPresent())
                return new ResponseEntity<Services>(HttpStatus.NOT_FOUND);
            services.setId(id);
            servicesService.save(services);
            return new ResponseEntity<Services>(services, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Services>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Services", notes = "Método que elimina los datos de los Services")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de los Services eliminados"),
            @ApiResponse(code=404, message = "Services no encontrado")
    })
    public ResponseEntity<Services> deleteServices(@PathVariable("id")Long id){
        try{
            Optional<Services> servicesDelete = servicesService.getById(id);
            if(!servicesDelete.isPresent())
                return new ResponseEntity<Services>(HttpStatus.NOT_FOUND);

            servicesService.delete(id);
            return new ResponseEntity<Services>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Services>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

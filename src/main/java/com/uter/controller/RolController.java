package com.uter.controller;

import com.uter.entities.Rol;
import com.uter.service.IRolService;
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
@RequestMapping("/api/rol")
@Api(tags="Rol", value = "Servicio Web RESTFul de Rol")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar Rol", notes="Método para listar todos los Rol")
    @ApiResponses({
            @ApiResponse(code=201, message = "Rol encontrados"),
            @ApiResponse(code=404, message = "Rol no encontrados")
    })
    public ResponseEntity<List<Rol>> findALL() {
        try {
            List<Rol> rol = rolService.getAll();
            if(rol.size()>0)
                return new ResponseEntity<List<Rol>>(rol, HttpStatus.OK);
            else
                return new ResponseEntity<List<Rol>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Rol>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar rol por Id", notes = "Método para encontrar un rol por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "rol encontrado"),
            @ApiResponse(code=404, message = "rol no encontrado")
    })
    public ResponseEntity<Rol> findById(@PathVariable("id") Long id) {
        try {
            Optional<Rol> rol = rolService.getById(id);
            if(!rol.isPresent())
                return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Rol>(rol.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Rol>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(value="searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Customers por Name", notes = "Método para encontrar customers por su respectivo Name")
    @ApiResponses({
            @ApiResponse(code=201, message = "Customers encontrados"),
            @ApiResponse(code=404, message = "Customers no encontrados")
    })
    public ResponseEntity<List<Rol>> findByName(@PathVariable("name") String name){
        try{
            List<Rol> rol = rolService.findByName(name);
            if(rol.size()>0)
                return new ResponseEntity<List<Rol>>(rol, HttpStatus.OK);
            else
                return new ResponseEntity<List<Rol>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Rol>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Rol", notes ="Método que registra rol en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "rol creado"),
            @ApiResponse(code=404, message = "rol no creado")
    })
    public ResponseEntity<Rol> insertRol(@Valid @RequestBody Rol rol){
        try{
            Rol rolNew = rolService.save(rol);
            return ResponseEntity.status(HttpStatus.CREATED).body(rolNew);
        }catch (Exception e){
            return new ResponseEntity<Rol>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de Rol", notes = "Método que actualizar los datos de Rol")
    @ApiResponses({
            @ApiResponse(code=200, message = "Rol actualizados"),
            @ApiResponse(code=404, message = "Rol no encontrado")
    })
    public ResponseEntity<Rol> updateRol(
            @PathVariable("id") Long id, @Valid @RequestBody Rol rol) {
        try{
            Optional<Rol> rolUp = rolService.getById(id);
            if(!rolUp.isPresent())
                return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
            rol.setId(id);
            rolService.save(rol);
            return new ResponseEntity<Rol>(rol, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Rol>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Rol", notes = "Método que elimina los datos de Rol")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Rol eliminados"),
            @ApiResponse(code=404, message = "Rol no encontrado")
    })
    public ResponseEntity<Rol> deleteCustomer(@PathVariable("id")Long id){
        try{
            Optional<Rol> rolDelete = rolService.getById(id);
            if(!rolDelete.isPresent())
                return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);

            rolService.delete(id);
            return new ResponseEntity<Rol>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Rol>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
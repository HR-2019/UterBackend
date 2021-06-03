package com.uter.controller;

import com.uter.entities.Customer;
import com.uter.entities.Reviews;
import com.uter.service.ICustomerService;
import com.uter.service.IReviewsService;
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
@RequestMapping("/api/reviews")
@Api(tags="Reviews", value = "Servicio Web RESTFul de Reviews")
public class ReviewsController {
    @Autowired
    private IReviewsService reviewsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar Reviews", notes="Método para listar todos los Reviews")
    @ApiResponses({
            @ApiResponse(code=201, message = "Reviews encontrados"),
            @ApiResponse(code=404, message = "Reviews no encontrados")
    })
    public ResponseEntity<List<Reviews>> findALL() {
        try {
            List<Reviews> reviews = reviewsService.getAll();
            if(reviews.size()>0)
                return new ResponseEntity<List<Reviews>>(reviews, HttpStatus.OK);
            else
                return new ResponseEntity<List<Reviews>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Reviews>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="searchByDescription/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Reviews por description", notes = "Método para encontrar Reviews por su respectivo description")
    @ApiResponses({
            @ApiResponse(code=201, message = "Reviews encontrados"),
            @ApiResponse(code=404, message = "Reviews no encontrados")
    })
    public ResponseEntity<List<Reviews>> findDescription(@PathVariable("description") String description){
        try{
            List<Reviews> reviews = reviewsService.findDescription(description);
            if(reviews.size()>0)
                return new ResponseEntity<List<Reviews>>(reviews, HttpStatus.OK);
            else
                return new ResponseEntity<List<Reviews>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Reviews>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="searchByStars/{stars}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Reviews por sus stars", notes = "Método para encontrar Reviews por sus respectivas stars")
    @ApiResponses({
            @ApiResponse(code=201, message = "Reviews encontrados"),
            @ApiResponse(code=404, message = "Reviews no encontrados")
    })
    public ResponseEntity<List<Reviews>> findStars(@PathVariable("stars") int stars){
        try{
            List<Reviews> reviews = reviewsService.findStars(stars);
            if(reviews.size()>0)
                return new ResponseEntity<List<Reviews>>(reviews, HttpStatus.OK);
            else
                return new ResponseEntity<List<Reviews>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Reviews>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Reviews", notes ="Método que registra Reviews en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Reviews creado"),
            @ApiResponse(code=404, message = "Reviews no creado")
    })
    public ResponseEntity<Reviews> insertReviews(@Valid @RequestBody Reviews reviews){
        try{
            Reviews reviewsNew = reviewsService.save(reviews);
            return ResponseEntity.status(HttpStatus.CREATED).body(reviewsNew);
        }catch (Exception e){
            return new ResponseEntity<Reviews>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los Reviews", notes = "Método que actualizar los datos de los Reviews")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de los Reviews actualizados"),
            @ApiResponse(code=404, message = "Reviews no encontrado")
    })
    public ResponseEntity<Reviews> updateReviews(
            @PathVariable("id") Long id, @Valid @RequestBody Reviews reviews) {
        try{
            Optional<Reviews> reviewsUp = reviewsService.getById(id);
            if(!reviewsUp.isPresent())
                return new ResponseEntity<Reviews>(HttpStatus.NOT_FOUND);
            reviews.setId(id);
            reviewsService.save(reviews);
            return new ResponseEntity<Reviews>(reviews, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Reviews>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Reviews", notes = "Método que elimina los datos de los Reviews")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de los Reviews eliminados"),
            @ApiResponse(code=404, message = "Reviews no encontrado")
    })
    public ResponseEntity<Reviews> deleteCustomer(@PathVariable("id")Long id){
        try{
            Optional<Reviews> reviewsDelete = reviewsService.getById(id);
            if(!reviewsDelete.isPresent())
                return new ResponseEntity<Reviews>(HttpStatus.NOT_FOUND);

            reviewsService.delete(id);
            return new ResponseEntity<Reviews>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Reviews>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

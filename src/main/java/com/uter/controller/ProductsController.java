package com.uter.controller;

import com.uter.entities.Products;
import com.uter.service.IProductsService;
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
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags="Products", value = "Servicio Web RESTFul de Products")
public class ProductsController {
    
    @Autowired
    private IProductsService productsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar products", notes="Método para listar todos los products")
    @ApiResponses({
            @ApiResponse(code=201, message = "Products encontrados"),
            @ApiResponse(code=404, message = "Products no encontrados")
    })
    public ResponseEntity<List<Products>> findALL() {
        try {
            List<Products> products = productsService.getAll();
            if(products.size()>0)
                return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
            else
                return new ResponseEntity<List<Products>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Products>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Products por Id", notes = "Método para encontrar un products por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "Products encontrado"),
            @ApiResponse(code=404, message = "Products no encontrado")
    })
    public ResponseEntity<Products> findById(@PathVariable("id") Long id) {
        try {
            Optional<Products> products = productsService.getById(id);
            if(!products.isPresent())
                return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Products>(products.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Products>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar products por Name", notes = "Método para encontrar products por su respectivo Name")
    @ApiResponses({
            @ApiResponse(code=201, message = "Products encontrados"),
            @ApiResponse(code=404, message = "Products no encontrados")
    })
    public ResponseEntity<List<Products>> findByName(@PathVariable("name") String name){
        try{
            List<Products> products = productsService.findByName(name);
            if(products.size()>0)
                return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
            else
                return new ResponseEntity<List<Products>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Products>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Products", notes ="Método que registra Products en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Products creado"),
            @ApiResponse(code=404, message = "Products no creado")
    })
    public ResponseEntity<Products> insertProducts(@Valid @RequestBody Products products){
        try{
            Products productsNew = productsService.save(products);
            return ResponseEntity.status(HttpStatus.CREATED).body(productsNew);
        }catch (Exception e){
            return new ResponseEntity<Products>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los Products", notes = "Método que actualiza los datos de Products")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Products actualizados"),
            @ApiResponse(code=404, message = "Products no encontrado")
    })
    public ResponseEntity<Products> updateProducts(
            @PathVariable("id") Long id, @Valid @RequestBody Products products) {
        try{
            Optional<Products> productsUp = productsService.getById(id);
            if(!productsUp.isPresent())
                return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
            products.setId(id);
            productsService.save(products);
            return new ResponseEntity<Products>(products, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Products>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Products", notes = "Método que elimina los datos de Products")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Products eliminados"),
            @ApiResponse(code=404, message = "Products no encontrado")
    })
    public ResponseEntity<Products> deleteProducts(@PathVariable("id")Long id){
        try{
            Optional<Products> productsDelete = productsService.getById(id);
            if(!productsDelete.isPresent())
                return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);

            productsService.delete(id);
            return new ResponseEntity<Products>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Products>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

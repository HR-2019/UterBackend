package com.uter.controller;

import com.uter.entities.Category;
import com.uter.entities.Customer;
import com.uter.service.ICategoryService;
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
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags="Category", value = "Servicio Web RESTFul de Categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar Categories", notes="Método para listar todos los categories")
    @ApiResponses({
            @ApiResponse(code=201, message = "Categories encontrados"),
            @ApiResponse(code=404, message = "Categories no encontrados")
    })
    public ResponseEntity<List<Category>> findALL() {
        try {
            List<Category> categories = categoryService.getAll();
            if(categories.size()>0)
                return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
            else
                return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Category>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Category por Id", notes = "Método para encontrar un category por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "Category encontrado"),
            @ApiResponse(code=404, message = "Category no encontrado")
    })
    public ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        try {
            Optional<Category> category = categoryService.getById(id);
            if(!category.isPresent())
                return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Category>(category.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Categories por Name", notes = "Método para encontrar categories por su respectivo Name")
    @ApiResponses({
            @ApiResponse(code=201, message = "Categories encontrados"),
            @ApiResponse(code=404, message = "Categories no encontrados")
    })
    public ResponseEntity<List<Category>> findByName(@PathVariable("name") String name){
        try{
            List<Category> categories = categoryService.findByName(name);
            if(categories.size()>0)
                return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
            else
                return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Category>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Categories", notes ="Método que registra categories en BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Category creado"),
            @ApiResponse(code=404, message = "Category no creado")
    })
    public ResponseEntity<Category> insertCategory(@Valid @RequestBody Category category){
        try{
            Category categoryNew = categoryService.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryNew);
        }catch (Exception e){
            return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los Categories", notes = "Método que actualiza los datos de Categories")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Category actualizados"),
            @ApiResponse(code=404, message = "Category no encontrado")
    })
    public ResponseEntity<Category> updateCategory(
            @PathVariable("id") Long id, @Valid @RequestBody Category category) {
        try{
            Optional<Category> categoryUp = categoryService.getById(id);
            if(!categoryUp.isPresent())
                return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
            category.setId(id);
            categoryService.save(category);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Categories", notes = "Método que elimina los datos de Categories")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Category eliminados"),
            @ApiResponse(code=404, message = "Category no encontrado")
    })
    public ResponseEntity<Category> deleteCategory(@PathVariable("id")Long id){
        try{
            Optional<Category> categoryDelete = categoryService.getById(id);
            if(!categoryDelete.isPresent())
                return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);

            categoryService.delete(id);
            return new ResponseEntity<Category>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.uter.controller;

import com.uter.entities.Seller;
import com.uter.service.ISellerService;
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
@RequestMapping("/api/sellers")
@Api(tags = "Seller", value = "Servicio Web RESTFul de Sellers")
public class SellerController {

    @Autowired
    private ISellerService sellerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Sellers", notes = "Método para listar todos los sellers")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sellers encontrados"),
            @ApiResponse(code = 404, message = "Sellers no encontrados")
    })
    public ResponseEntity<List<Seller>> findAll(){

        try{
            List<Seller> sellers = sellerService.getAll();
            if(sellers.size() > 0)
                return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Seller>>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<List<Seller>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Seller por Id", notes = "Método para encontrar un seller por su respectivo id")
    @ApiResponses({
            @ApiResponse(code=201, message = "Seller encontrado"),
            @ApiResponse(code=404, message = "Seller no encontrado")
    })
    public ResponseEntity<Seller> findById(@PathVariable("id") Long id) {
        try {
            Optional<Seller> seller = sellerService.getById(id);
            if(!seller.isPresent())
                return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Seller>(seller.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Seller>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Seller por Username", notes = "Método para encontrar un seller por su respectivo username")
    @ApiResponses({
            @ApiResponse(code=201, message = "Seller encontrado"),
            @ApiResponse(code=404, message = "Seller no encontrado")
    })
    public ResponseEntity<Seller> findByUsername(@PathVariable("username") String username){
        try{
            Seller seller = sellerService.findByUsername(username);
            if(seller==null)
                return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Seller>(seller,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Seller>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Sellers por Name", notes = "Método para encontrar sellers por su respectivo Name")
    @ApiResponses({
            @ApiResponse(code=201, message = "Sellers encontrados"),
            @ApiResponse(code=404, message = "Sellers no encontrados")
    })
    public ResponseEntity<List<Seller>> findByName(@PathVariable("name") String name){
        try{
            List<Seller> sellers = sellerService.findByName(name);
            if(sellers.size() > 0)
                return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Seller>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Seller>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Seller por email", notes = "Método para encontrar un seller por su respectivo email")
    @ApiResponses({
            @ApiResponse(code=201, message = "Seller encontrado"),
            @ApiResponse(code=404, message = "Seller no encontrado")
    })
    public ResponseEntity<List<Seller>> findByEmail(@PathVariable("email") String email){
        try{
            List<Seller> sellers = sellerService.findByEmail(email);
            if(sellers.size() > 0)
                return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Seller>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Seller>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByPhone/{phone}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Sellers por el Phone", notes = "Método para encontrar sellers por su respectivo phone")
    @ApiResponses({
            @ApiResponse(code=201, message = "Sellers encontrados"),
            @ApiResponse(code=404, message = "Sellers no encontrados")
    })
    public ResponseEntity<List<Seller>> findByPhone(@PathVariable("phone") String phone){
        try{
            List<Seller> sellers = sellerService.findByPhone(phone);
            if(sellers.size()>0)
                return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Seller>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<Seller>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Sellers", notes ="Método que registra sellers en base de datos" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Seller creado"),
            @ApiResponse(code=404, message = "Seller no creado")
    })
    public ResponseEntity<Seller> insertSeller(@Valid @RequestBody Seller seller){
        try{
            Seller sellerNew = sellerService.save(seller);
            return ResponseEntity.status(HttpStatus.CREATED).body(sellerNew);
        }catch (Exception e){
            return new ResponseEntity<Seller>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de los Sellers", notes = "Método que actualiza los datos de Sellers")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Seller actualizados"),
            @ApiResponse(code=404, message = "Seller no encontrado")
    })
    public ResponseEntity<Seller> updateSeller(
            @PathVariable("id") Long id, @Valid @RequestBody Seller seller) {
        try{
            Optional<Seller> sellerUp = sellerService.getById(id);
            if(!sellerUp.isPresent())
                return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
            seller.setId(id);
            sellerService.save(seller);
            return new ResponseEntity<Seller>(seller, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Seller>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de Sellers", notes = "Método que elimina los datos de Sellers")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Seller eliminados"),
            @ApiResponse(code=404, message = "Seller no encontrado")
    })
    public ResponseEntity<Seller> deleteSeller(@PathVariable("id")Long id){
        try{
            Optional<Seller> sellerDelete = sellerService.getById(id);
            if(!sellerDelete.isPresent())
                return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);

            sellerService.delete(id);
            return new ResponseEntity<Seller>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Seller>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.repositorys.ProductsRepository;
import com.example.crud.dtos.RequestProductDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductsRepository repository;

    @GetMapping
    public ResponseEntity<?> getProducts() {

        var allProducts = repository.findAllByActiveTrue();

        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody @Valid RequestProductDto dado) {

        var newProduct = new Product(dado);

        repository.save(newProduct);


        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateProduct(@RequestBody @Valid RequestProductDto dado) {

        Optional<Product> optionalProduct = repository.findById(dado.id());

        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();

            product.setName(dado.name());
            product.setPrince_in_cents(dado.prince_in_cents());

            return ResponseEntity.ok(product);
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {

        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();

            product.setActive(false);

            return ResponseEntity.noContent().build();
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> activeProduct(@PathVariable String id) {

        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();

            product.setActive(true);

            return ResponseEntity.ok().build();
        }
        else{
            throw new EntityNotFoundException();
        }
    }
}

package com.test.spring.security.login.controllers;

import com.test.spring.security.login.models.Product;
import com.test.spring.security.login.payload.response.MessageResponse;
import com.test.spring.security.login.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Product add successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @Valid @RequestBody Product product) {
        if (id == null || id == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Product not found"));
        }
        product.setId(id);
        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Product update successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(required = true) Long id){
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return ResponseEntity.ok(new MessageResponse("Product delete successfully!"));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERVISOR')")
    public ResponseEntity<?> getAlProduct(){
        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok().body(productList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(required = true) Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Product is not found."));;
        return ResponseEntity.ok().body(product);
    }
}

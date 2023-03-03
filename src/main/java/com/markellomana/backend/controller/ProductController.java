package com.markellomana.backend.controller;

import lombok.RequiredArgsConstructor;
import com.markellomana.backend.dbservices.*;
import com.markellomana.backend.domainobject.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endpoints")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  private final ProductService productService;

  @GetMapping(path = "/products")
  public ResponseEntity<?> getAllProducts() {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(productService.getAllProducts());
    } catch (Exception e) {
      logger.error("Error trying to get products: {}", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error trying to get products: {}" + e);
    }
  }

  @PostMapping(path = "/products")
  public ResponseEntity<?> createProduct(
      @RequestBody Product product) {
    try {
      return ResponseEntity.ok(productService.createProduct(product));
    } catch (Exception e) {
      logger.error("Error trying to create product: {}", e.toString());
      return ResponseEntity.status(500).body(e.getMessage());
    }
  }

  @PutMapping(path = "/products")
  public ResponseEntity<?> updateProduct(
      @RequestBody Product product) {
    try {
      return ResponseEntity.ok(productService.updateProduct(product));
    } catch (Exception e) {
      logger.error("Error trying to update product: {}", e.toString());
      return ResponseEntity.status(500).body(e.getMessage());
    }
  }

  @DeleteMapping(path = "/products/{id}")
  public ResponseEntity<?> deleteProduct(
      @PathVariable Long id) {
    try {
      Product product = productService.getProductById(id);
      if (product == null) {
        return ResponseEntity.ok(productService.deleteProduct(id));
      } else {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            "Cant be deleted, there are associated tools types: " + product.getCode());
      }
    } catch (Exception e) {
      logger.error("Error trying to delete product: {}", e.toString());
      return ResponseEntity.status(500).body(e.getMessage());
    }
  }
}

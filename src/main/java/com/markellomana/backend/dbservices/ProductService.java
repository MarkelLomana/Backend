package com.markellomana.backend.dbservices;

import lombok.RequiredArgsConstructor;
import com.markellomana.backend.dbservices.repository.ProductRepository;
import com.markellomana.backend.domainobject.Product;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  private final ProductRepository productRepository;

  @Transactional
  public List<Product> getAllProducts() {
    List<Product> result = null;
    try {
      result = productRepository.findAllByActive(true);
    } catch (Exception e) {
      logger.error("Error finding tool groups by specified technology id");
    }
    return result;
  }

  @Transactional
  public Product getProductById(Long id) {
    Product result = null;
    try {
      result = productRepository.findByIdAndActive(id, true);
    } catch (Exception e) {
      logger.error("Error finding active's product");
    }
    return result;
  }

  public Product createProduct(Product product) {
    Product result = null;
    try {
      product.setActive(true);
      result = productRepository.save(product);
    } catch (Exception e) {
      logger.error("Error saving product {}", e.toString());
    }
    return result;
  }

  public Product updateProduct(Product product) {
    Product result = null;
    if (productRepository.existsById(product.getId())) {
      try {
        result = productRepository.save(product);
      } catch (Exception e) {
        logger.error("Error updating product {}", e.toString());
      }
    }
    return result;
  }

  @Transactional
  public boolean deleteProduct(Long id) {
    boolean result = false;
    try {
      Product product = productRepository.findById(id).orElse(null);
      if (product != null) {
        product.setActive(false);
        productRepository.save(product);
        result = true;
      }
    } catch (Exception e) {
      logger.error("Error setting inactive product", id);
    }
    return result;
  }

}

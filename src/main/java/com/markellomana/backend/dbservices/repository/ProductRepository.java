package com.markellomana.backend.dbservices.repository;

import com.markellomana.backend.domainobject.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends RevisionRepository<Product, Long, Integer>, CrudRepository<Product, Long> {

  List<Product> findAll();

  List<Product> findAllByActive(boolean b);

  Product findByIdAndActive(Long id, boolean b);
}

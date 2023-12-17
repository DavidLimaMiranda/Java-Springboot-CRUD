package com.example.crud.repositorys;

import com.example.crud.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, String> {
    List<Product> findAllByActiveTrue();
}

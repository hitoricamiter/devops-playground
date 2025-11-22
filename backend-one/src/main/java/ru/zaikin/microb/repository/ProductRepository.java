package ru.zaikin.microb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaikin.microb.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}

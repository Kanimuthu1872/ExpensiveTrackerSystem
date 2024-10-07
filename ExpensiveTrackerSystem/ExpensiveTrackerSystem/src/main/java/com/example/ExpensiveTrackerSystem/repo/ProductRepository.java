package com.example.ExpensiveTrackerSystem.repo;

import com.example.ExpensiveTrackerSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCreateDate(LocalDate date);
    List<Product> findByCreateDateAndPaymentCategory(LocalDate date, String category);
}

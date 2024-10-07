package com.example.ExpensiveTrackerSystem.controller;

import com.example.ExpensiveTrackerSystem.dto.ProductDto;
import com.example.ExpensiveTrackerSystem.entity.Product;
import com.example.ExpensiveTrackerSystem.exception.ExpensiveNotFoundException;
import com.example.ExpensiveTrackerSystem.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product addProduct(@RequestBody @Valid ProductDto p) {
        return productService.addProduct(p);
    }


    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) throws ExpensiveNotFoundException {
        return productService.getProduct(id);
    }


    @GetMapping("/amount/date/{date}")
    public double getTotalAmountByDate(@PathVariable String date) {
        return productService.getTotalAmountByDate(date);
    }

    @GetMapping("/amount/date/{date}/category/{category}")
    public double getTotalAmountByDateAndCategory(@PathVariable String date, @PathVariable String category) {
        return productService.getTotalAmountByDateAndCategory(date, category);
    }


    @GetMapping("/expenses/month/{yearMonth}")
    public List<Product> getExpensesByMonth(@PathVariable String yearMonth) {
        return productService.getExpensesByMonth(yearMonth);
    }

    @GetMapping("/income/date/{date}")
    public double getTotalIncomeOnDate(@PathVariable String date) {
        return productService.getTotalIncomeOnDate(date);
    }
}

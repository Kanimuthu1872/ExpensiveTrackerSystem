package com.example.ExpensiveTrackerSystem.service;

import com.example.ExpensiveTrackerSystem.dto.ProductDto;
import com.example.ExpensiveTrackerSystem.entity.Product;
import com.example.ExpensiveTrackerSystem.exception.ExpensiveNotFoundException;
import com.example.ExpensiveTrackerSystem.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(ProductDto p) {
        Product pro = new Product();
        pro.setItemName(p.getItemName());
        pro.setAmount(p.getAmount());
        pro.setDescription(p.getDescription());
        pro.setPaymentCategory(p.getPaymentCategory());
        return productRepository.save(pro);
    }


    public Product getProduct(Long id) throws ExpensiveNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ExpensiveNotFoundException("Product not found with the indicated ID: " + id));
    }

    public double getTotalAmountByDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<Product> products = productRepository.findByCreateDate(localDate);
        return products.stream().mapToDouble(Product::getAmount).sum();
    }
    public double getTotalAmountByDateAndCategory(String date, String category) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<Product> products = productRepository.findByCreateDateAndPaymentCategory(localDate, category);
        return products.stream().mapToDouble(Product::getAmount).sum();
    }

    public List<Product> getExpensesByMonth(String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();
        return productRepository.findAll().stream()
                .filter(product -> !product.getCreateDate().isBefore(startDate) && !product.getCreateDate().isAfter(endDate))
                .toList();
    }


    public double getTotalIncomeOnDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<Product> products = productRepository.findByCreateDate(localDate);
        return products.stream().mapToDouble(Product::getAmount).sum();
    }
}

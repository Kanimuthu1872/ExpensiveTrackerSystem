package com.example.ExpensiveTrackerSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotNull
    private String itemName;

    @NotBlank(message = "Please enter the transaction type and it shouldn't be blank")
    private String paymentCategory;

    @Min(value = 1, message = "Amount shouldn't be negative")
    private double amount;

    @Size(min = 5, max = 300)
    private String description;
}

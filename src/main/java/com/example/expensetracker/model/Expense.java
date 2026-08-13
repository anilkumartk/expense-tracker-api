package com.example.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    Long id;
    String title;
    BigDecimal amount;
    String category;
    LocalDate date;
}

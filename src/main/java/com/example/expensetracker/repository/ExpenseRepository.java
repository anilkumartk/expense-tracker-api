package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();

    private long nextId = 1;

    public Expense save(Expense expense) {

        expense.setId(nextId++);

        expenses.add(expense);

        return expense;
    }

    public List<Expense> findAll() {

        return new ArrayList<>(expenses);
    }

    public Optional<Expense> findById(Long id) {

        return expenses.stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst();
    }

    public List<Expense> findByCategory(String category) {

        return expenses.stream()
                .filter(expense ->
                        expense.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public void deleteById(Long id) {

        expenses.removeIf(expense ->
                expense.getId().equals(id));
    }
}
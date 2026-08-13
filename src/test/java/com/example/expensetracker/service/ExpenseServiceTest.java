package com.example.expensetracker.service;

import com.example.expensetracker.dto.ExpenseRequest;
import com.example.expensetracker.exception.ResourceNotFoundException;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense expense;

    @BeforeEach
    void setUp() {

        expense = new Expense(
                1L,
                "Pizza",
                BigDecimal.valueOf(450),
                "Food",
                LocalDate.of(2026, 8, 13)
        );
    }

    @Test
    void shouldAddExpense() {

        ExpenseRequest request = new ExpenseRequest(
                "Pizza",
                BigDecimal.valueOf(450),
                "Food",
                LocalDate.of(2026, 8, 13)
        );

        when(expenseRepository.save(any(Expense.class)))
                .thenReturn(expense);

        Expense result =
                expenseService.addExpense(request);

        assertNotNull(result);
        assertEquals("Pizza", result.getTitle());
        assertEquals(
                BigDecimal.valueOf(450),
                result.getAmount()
        );

        verify(expenseRepository).save(any(Expense.class));
    }

    @Test
    void shouldGetAllExpenses() {

        when(expenseRepository.findAll())
                .thenReturn(List.of(expense));

        List<Expense> result =
                expenseService.getAllExpenses();

        assertEquals(1, result.size());
        assertEquals("Pizza", result.get(0).getTitle());
    }

    @Test
    void shouldGetExpensesByCategory() {

        when(expenseRepository.findByCategory("Food"))
                .thenReturn(List.of(expense));

        List<Expense> result =
                expenseService.getExpensesByCategory("Food");

        assertEquals(1, result.size());
        assertEquals("Food", result.get(0).getCategory());
    }

    @Test
    void shouldCalculateTotalExpenses() {

        Expense expense2 = new Expense(
                2L,
                "Burger",
                BigDecimal.valueOf(300),
                "Food",
                LocalDate.of(2026, 8, 13)
        );

        when(expenseRepository.findAll())
                .thenReturn(List.of(expense, expense2));

        BigDecimal total =
                expenseService.getTotalExpenses();

        assertEquals(
                BigDecimal.valueOf(750),
                total
        );
    }

    @Test
    void shouldCalculateCategoryTotal() {

        Expense expense2 = new Expense(
                2L,
                "Burger",
                BigDecimal.valueOf(300),
                "Food",
                LocalDate.of(2026, 8, 13)
        );

        when(expenseRepository.findByCategory("Food"))
                .thenReturn(List.of(expense, expense2));

        BigDecimal total =
                expenseService.getTotalByCategory("Food");

        assertEquals(
                BigDecimal.valueOf(750),
                total
        );
    }

    @Test
    void shouldDeleteExpense() {

        when(expenseRepository.findById(1L))
                .thenReturn(Optional.of(expense));

        expenseService.deleteExpense(1L);

        verify(expenseRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenExpenseDoesNotExist() {

        when(expenseRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> expenseService.deleteExpense(999L)
        );

        verify(expenseRepository, never())
                .deleteById(999L);
    }
}
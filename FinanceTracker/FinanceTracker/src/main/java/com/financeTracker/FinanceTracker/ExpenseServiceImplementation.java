package com.financeTracker.FinanceTracker;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImplementation implements ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseServiceImplementation(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void saveExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setAmount(expenseDto.getAmount());
        expense.setTitle(expenseDto.getTitle());
        expense.setDate(expenseDto.getDate());
        expenseRepository.save(expense);
    }

    @Override
    public Expense findExpenseByTitle(String title) {return expenseRepository.findByTitle(title); }

    @Override
    public List<ExpenseDto> findAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map((Expense) -> mapToExpenseDto(Expense))
                .collect(Collectors.toList());
    }

    private ExpenseDto mapToExpenseDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setAmount(expense.getAmount());
        expenseDto.setTitle(expense.getTitle());
        expenseDto.setDate(expense.getDate());

        return expenseDto;
    }
}

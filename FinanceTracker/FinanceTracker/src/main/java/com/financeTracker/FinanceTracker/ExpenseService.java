package com.financeTracker.FinanceTracker;

import java.util.List;

public interface ExpenseService {
    void saveExpense(ExpenseDto expenseDto);

    Expense findExpenseByTitle(String title);

    List<ExpenseDto> findAllExpenses();
}

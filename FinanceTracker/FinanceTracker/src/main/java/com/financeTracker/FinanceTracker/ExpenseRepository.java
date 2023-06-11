package com.financeTracker.FinanceTracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense findByTitle(String title);
}

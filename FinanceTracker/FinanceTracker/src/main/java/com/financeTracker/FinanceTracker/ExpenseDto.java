package com.financeTracker.FinanceTracker;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private long id;

    @NotEmpty
    private long amount;

    @NotEmpty
    private String title;

    @NotEmpty
    private String date;
}

package com.peerlender.profile.application.model;

import com.peerlender.profile.domain.model.User;

import java.time.Duration;
import java.util.Objects;

public class LoanRequest {
    private final int amount;
    private final long borrowerId;
    private final Duration daysToRepay;
    private final double interestRate;

    public LoanRequest(int amount, long borrowerId, Duration daysToRepay, double interestRate) {
        this.amount = amount;
        this.borrowerId = borrowerId;
        this.daysToRepay = daysToRepay;
        this.interestRate = interestRate;
    }

    public int getAmount() {
        return amount;
    }

    public long getBorrowerId() {
        return borrowerId;
    }

    public Duration getDaysToRepay() {
        return daysToRepay;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return amount == that.amount &&
                borrowerId == that.borrowerId &&
                Double.compare(that.interestRate, interestRate) == 0 &&
                Objects.equals(daysToRepay, that.daysToRepay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, borrowerId, daysToRepay, interestRate);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", borrowerId=" + borrowerId +
                ", daysToRepay=" + daysToRepay +
                ", interestRate=" + interestRate +
                '}';
    }
}
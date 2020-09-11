package com.peerlender.profile.domain.repository;

import com.peerlender.profile.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}

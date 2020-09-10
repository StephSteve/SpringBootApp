package com.peerlender.profile.domain.repository;

import com.peerlender.profile.domain.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {


}

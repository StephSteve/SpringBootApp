package com.peerlender.profile.domain.repository;

import com.peerlender.profile.domain.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRequestRepository extends JpaRepository<LoanApplication, Long> {


}

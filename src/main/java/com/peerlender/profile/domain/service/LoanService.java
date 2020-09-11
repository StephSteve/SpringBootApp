package com.peerlender.profile.domain.service;

import com.peerlender.profile.domain.exception.LoanApplicationNotFoundException;
import com.peerlender.profile.domain.exception.UserNotFoundException;
import com.peerlender.profile.domain.model.Loan;
import com.peerlender.profile.domain.model.LoanApplication;
import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.LoanApplicationRepository;
import com.peerlender.profile.domain.repository.LoanRepository;
import com.peerlender.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanApplicationRepository loanApplicationRepository,
                       UserRepository userRepository, LoanRepository loanRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    private final LoanRepository loanRepository;

    public void acceptLoan(final long loanApplicationId, final long lenderId){
        User lender = userRepository.findById(lenderId).orElseThrow(() -> new UserNotFoundException(lenderId));
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
                                            .orElseThrow(() -> new LoanApplicationNotFoundException(loanApplicationId));
        loanRepository.save(new Loan(lender, loanApplication));
    }

    public List<Loan> getLoans(){
        return loanRepository.findAll();
    }
}

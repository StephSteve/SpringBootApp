package com.peerlender.profile.application;

import com.peerlender.profile.application.model.LoanRequest;
import com.peerlender.profile.application.service.TokenValidationService;
import com.peerlender.profile.domain.model.Loan;
import com.peerlender.profile.domain.model.LoanApplication;
import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.LoanApplicationRepository;
import com.peerlender.profile.domain.repository.UserRepository;
import com.peerlender.profile.domain.service.LoanApplicationAdapter;
import com.peerlender.profile.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoanController {

    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final LoanApplicationAdapter loanApplicationAdapter;
    private final LoanService loanService;
    private final TokenValidationService tokenValidationService;

    @Autowired
    public LoanController(LoanApplicationRepository loanApplicationRepository,
                          UserRepository userRepository, LoanApplicationAdapter loanApplicationAdapter,
                          LoanService loanService, TokenValidationService tokenValidationService) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanApplicationAdapter = loanApplicationAdapter;
        this.loanService = loanService;
        this.tokenValidationService = tokenValidationService;
    }

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody final LoanRequest loanRequest, HttpServletRequest request) {
       User borrower = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        LoanApplication loanApplication = loanApplicationAdapter.transform(loanRequest, borrower);
        loanApplicationRepository.save(loanApplication);
    }

    @GetMapping(value = "/loan/requests")
    public List<LoanApplication> findAllLoanApplications(HttpServletRequest request) {
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanApplicationRepository.findAll();
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(HttpServletRequest request) {
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findAll();
    }

    @PostMapping(value = "/loan/accept/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String loanApplicationId, HttpServletRequest request) {
      User lender = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanService.acceptLoan(Long.parseLong(loanApplicationId), lender.getUsername());

    }

    @GetMapping(value = "/loans")
    public List<Loan> getLoans(){
        return loanService.getLoans();
    }

}

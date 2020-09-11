package com.peerlender.profile.application;

import com.peerlender.profile.application.model.LoanRequest;
import com.peerlender.profile.domain.model.LoanApplication;
import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.LoanApplicationRepository;
import com.peerlender.profile.domain.repository.UserRepository;
import com.peerlender.profile.domain.service.LoanApplicationAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final LoanApplicationAdapter loanApplicationAdapter;

    @Autowired
    public LoanController(LoanApplicationRepository loanApplicationRepository, UserRepository userRepository, LoanApplicationAdapter loanApplicationAdapter) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanApplicationAdapter = loanApplicationAdapter;
    }

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody final LoanRequest loanRequest){
        LoanApplication loanApplication = loanApplicationAdapter.transform(loanRequest);
        loanApplicationRepository.save(loanApplication);
    }

    @GetMapping(value = "/loan/requests")
    public List<LoanApplication> findAllLoanApplications(){
        return loanApplicationRepository.findAll();
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/loan/accept/{lenderId}/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String lenderId,
                           @PathVariable final String LoanApplicationId){

    }
}

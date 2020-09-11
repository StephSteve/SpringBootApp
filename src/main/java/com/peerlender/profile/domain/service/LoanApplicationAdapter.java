package com.peerlender.profile.domain.service;

import com.peerlender.profile.application.model.LoanRequest;
import com.peerlender.profile.domain.exception.UserNotFoundException;
import com.peerlender.profile.domain.model.LoanApplication;
import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class LoanApplicationAdapter {

    private final UserRepository userRepository;

    @Autowired
    public LoanApplicationAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoanApplication transform(LoanRequest req){
        Optional<User> userOptional = userRepository.findById(req.getBorrowerId());

        if (userOptional.isPresent()){
            LoanApplication loanApplication = new LoanApplication(req.getAmount(), userOptional.get(),
                    req.getDaysToRepay(), req.getInterestRate());
            return loanApplication;
        }else{
                throw new UserNotFoundException(req.getBorrowerId());
        }

    }
}

package com.peerlender.profile.application.service;


import com.peerlender.profile.domain.exception.UserNotFoundException;
import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenValidationService {

    private UserRepository userRepository;
    private final RestTemplate restTemplate= new RestTemplate();
    private final String securityContextBasedUrl;

    @Autowired
    public TokenValidationService(UserRepository userRepository,
                                  @Value("${security.baseurl}") final String securityContextBasedUrl) {
        this.userRepository = userRepository;
        this.securityContextBasedUrl = securityContextBasedUrl;
    }

    public User validateTokenAndGetUser(final String token){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange(securityContextBasedUrl + "/user/validate", HttpMethod.POST, httpEntity, String.class);

        if (response.getStatusCode().equals(HttpStatus.OK)){
            return userRepository.findById(response.getBody())
                    .orElseThrow(() -> new UserNotFoundException(response.getBody()));
        }
        throw new RuntimeException("Invalid token");
    }
}

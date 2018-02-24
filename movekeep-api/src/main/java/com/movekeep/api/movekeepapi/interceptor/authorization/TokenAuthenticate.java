package com.movekeep.api.movekeepapi.interceptor.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenAuthenticate implements Authenticate {

    private final String verifyUri = "http://localhost:3000/verify-token";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean isAuthenticated(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("authorization");

        if (null == authorizationHeader) return false;

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", authorizationHeader);

        HttpEntity<String> authRequest = new HttpEntity<>("", headers);

        try {

            ResponseEntity<String> responseEntity = this.restTemplate.exchange(this.verifyUri, HttpMethod.POST, authRequest, String.class);

            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) return true;

        } catch (HttpClientErrorException error) {

            return false;
        }

        return true;
    }
}

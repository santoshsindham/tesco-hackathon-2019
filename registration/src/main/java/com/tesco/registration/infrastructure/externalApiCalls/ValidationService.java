package com.tesco.registration.infrastructure.externalApiCalls;

import com.tesco.registration.domain.entity.User;
import com.tesco.registration.domain.entity.UserValidationResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidationService {

    @Value("${uk.id.verification.service.base.url}")
    private String ID_VERIFICATION_BASE_URL;

    public UserValidationResult validateUserDetails(User user){

        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForObject(ID_VERIFICATION_BASE_URL+buildVerficationQuery(user), UserValidationResult.class);
    }

    public String buildVerficationQuery(User user){

        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("?utr="+user.getVerificationDetails().getIdNumber());
        stringBuilder.append("&licenseNo="+user.getVerificationDetails().getLicenceNumber());
        return stringBuilder.toString();
    }
}

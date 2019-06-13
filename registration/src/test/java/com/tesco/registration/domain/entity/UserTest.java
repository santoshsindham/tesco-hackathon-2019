package com.tesco.registration.domain.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

class UserTest {

    @Test
    public void user_getting_created() throws JsonProcessingException {


        User user = User.builder().id(UUID.randomUUID().toString())
                .firstName("Santosh")
                .lastName("Sindham")
                .verificationDetails(User.VerificationDetails.builder().idNumber("8978629040").idType("Unique Tax Reference Number").licenceNumber("8978629326").isVerified(false).build())
                .phoneDetails(User.PhoneDetails.builder().mobileNumber("9538045556").model("Poco F1 Xiaomi").imeiNumber("123456789").isGpsEnabled(true).build())
                .addressDetails(User.Address.builder().city("Hyderabad").country("India").doorNumber("B2-817").postalCode("560066").state("Karnataka").streetName("Nallurahalli").build())
                .vehicleDetails(User.Vehicle.builder().model("Sedan").registrationNumber("AP09 CP 8585").type("Hyundai Verna").bootSpaceDetails(User.BootSpaceDetails.builder().lengthInMtrs(Long.valueOf(20)).breadthInMtrs(Long.valueOf(15)).heightInMtrs(Long.valueOf(10)).build()).build())
                .deliveryPreferenceDetails(User.DeliveryPreference.builder().fromTime("17:00").toTime("19:00").preferredDays(new ArrayList<>(List.of(User.Days.MONDAY, User.Days.TUESDAY, User.Days.WEDNESDAY, User.Days.THURSDAY, User.Days.FRIDAY))).build())
                .fromLocation(User.Location.builder().latitude("12.937562").longitude("77.670097").preferredRadiusInMtrs(Long.valueOf(200)).build())
                .toLocation(User.Location.builder().latitude("12.971389").longitude("77.750130").preferredRadiusInMtrs(Long.valueOf(200)).build())
                .remunerationDetails(User.Remuneration.builder().accountDetails(User.Account.builder().accountNumber("015201533330").accountType("Savings").build()).clubCardDetails(User.ClubCard.builder().clubCardNumber("9590695925").build()).isClubCardPreferred(true).build()).build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        System.out.println(objectMapper.writeValueAsString(user));

    }
}
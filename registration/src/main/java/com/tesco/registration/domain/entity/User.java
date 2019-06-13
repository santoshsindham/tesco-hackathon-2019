package com.tesco.registration.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Document
public class User {

    @Id
    private String id;
    private VerificationDetails verificationDetails;
    private PhoneDetails phoneDetails;
    private Address addressDetails;
    private Vehicle vehicleDetails;
    private DeliveryPreference deliveryPreferenceDetails;
    private Location fromLocation;
    private Location toLocation;
    private Remuneration remunerationDetails;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class VerificationDetails {

        private String idType;
        private String idNumber;
        private Boolean isVerified;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PhoneDetails {

        private String model;
        private String mobileNumber;
        private String imeiNumber;
        private Boolean isGpsEnabled;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Address {

        private String doorNumber;
        private String streetName;
        private String city;
        private String state;
        private String country;
        private String postalCode;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Vehicle {

        private String type;
        private String model;
        private String registrationNumber;
        private BootSpaceDetails bootSpaceDetails;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class BootSpaceDetails {

        private Long lengthInMtrs;
        private Long breadthInMtrs;
        private Long heightInMtrs;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class DeliveryPreference {

        private String fromTime;
        private String toTime;
        private List<Days> preferredDays;
    }

    public enum Days {

        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Location {

        private String longitude;
        private String latitude;
        private Long preferredRadiusInMtrs;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Remuneration {

        private Account accountDetails;
        private ClubCard clubCardDetails;
        private Boolean isClubCardPreferred;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Account {

        private String accountNumber;
        private String accountType;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ClubCard {

        private String clubCardNumber;
    }
}

package pl.coderslab.medicalcheckupssender.HealthFacility;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class HealthFacilityDto {
    private Long id;
    @NotBlank
    private String nameOfHealthFacility;
    @NotBlank
    private String streetName;
    @NotBlank
    private String houseNumber;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;
    @NotBlank
    private String phoneNumber;

}

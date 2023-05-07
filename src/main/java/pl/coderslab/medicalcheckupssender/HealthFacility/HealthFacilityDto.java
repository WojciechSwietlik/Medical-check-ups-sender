package pl.coderslab.medicalcheckupssender.HealthFacility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class HealthFacilityDto {
    private Long id;
    @NotBlank
    @Schema(description = "Health facility name", example = "LuxMed")
    private String nameOfHealthFacility;
    @NotBlank
    @Schema(description = "Street name", example = "Bora-Komorowskiego")
    private String streetName;
    @NotBlank
    @Schema(description = "House number and apartment number if applicable", example = "11/12")
    private String houseNumber;
    @NotBlank
    @Schema(description = "Zip code", example = "31-000")
    private String zipCode;
    @NotBlank
    @Schema(description = "City name", example = "Cracow")
    private String city;
    @NotBlank
    @Schema(description = "Phone number of health facility", example = "12 311 21 21")
    private String phoneNumber;
}
package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EmployeeAddressDto {
    @Schema(description = "EmployeeAddress id", example = "1")
    private Long id;
    @Schema(description = "Employee id", example = "1")
    private Long employeeId;
    @NotBlank
    @Schema(description = "Street name", example = "Grodzka")
    private String streetName;
    @NotBlank
    @Schema(description = "House number", example = "1A")
    private String houseNumber;
    @Schema(description = "Apartment number - if not applicable, leave blank", example = "11")
    private String apartmentNumber;
    @NotBlank
    @Schema(description = "Zip code", example = "31-664")
    private String zipCode;
    @NotBlank
    @Schema(description = "City", example = "Warszawa")
    private String city;
}

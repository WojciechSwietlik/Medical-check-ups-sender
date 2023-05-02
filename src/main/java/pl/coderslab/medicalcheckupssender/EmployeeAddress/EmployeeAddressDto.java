package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EmployeeAddressDto {
    private Long id;
    private Long employeeId;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;
}

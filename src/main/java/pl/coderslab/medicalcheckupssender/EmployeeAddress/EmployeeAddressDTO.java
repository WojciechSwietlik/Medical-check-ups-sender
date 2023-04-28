package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmployeeAddressDTO {
    private Long ID;
    private Long EmployeeID;
    @NotNull
    private String StreetName;
    @NotNull
    private Integer HouseNumber;
    @NotNull
    private Integer ApartmentNumber;
    @NotNull
    private Integer ZipCode;
    @NotNull
    private String City;
}

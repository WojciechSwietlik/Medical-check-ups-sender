package pl.coderslab.medicalcheckupssender.HealthFacility;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class HealthFacilityDTO {
    private Long ID;
    private Long HealthFacilityID;
    @NotNull
    private String Name;
    @NotNull
    private String StreetName;
    @NotNull
    private Integer HouseNumber;
    @NotNull
    private Integer ZipCode;
    @NotNull
    private String City;
    @NotNull
    private Integer PhoneNumber;

}

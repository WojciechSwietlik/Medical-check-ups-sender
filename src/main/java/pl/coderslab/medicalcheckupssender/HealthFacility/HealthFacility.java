package pl.coderslab.medicalcheckupssender.HealthFacility;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "HealthFacility")
public class HealthFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull
    private String Name;
    @NotNull
    private String StreetName;
    @NotNull
    private Integer HouseNumber;
    @NotNull
    private Integer ZipCode;
    @NotNull
    private String City; //referencja do WorkPlace Employee
    @NotNull
    private Integer PhoneNumber;

}

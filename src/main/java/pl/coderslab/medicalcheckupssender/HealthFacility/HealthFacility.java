package pl.coderslab.medicalcheckupssender.HealthFacility;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "healthFacility")
public class HealthFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

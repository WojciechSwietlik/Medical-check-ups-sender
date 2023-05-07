package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import lombok.*;
import pl.coderslab.medicalcheckupssender.Employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employeeAddresses")
public class EmployeeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Employee employee;
    @NotBlank
    private String streetName;
    @NotBlank
    private String houseNumber;
    private String apartmentNumber;
    @NotBlank
    @Pattern(regexp = "[0-9][0-9]-[0-9][0-9][0-9]")
    private String zipCode;
    @NotBlank
    private String city;
}

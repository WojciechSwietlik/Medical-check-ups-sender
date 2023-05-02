package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import lombok.*;
import pl.coderslab.medicalcheckupssender.Employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    Employee employee;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;

}

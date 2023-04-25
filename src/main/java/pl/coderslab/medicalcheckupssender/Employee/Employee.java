package pl.coderslab.medicalcheckupssender.Employee;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank
    private String Surname;
    @NotBlank
    private String Name;
    @DateTimeFormat
    private LocalDate DateOfBirth;
    @PESEL
    private Integer PESEL;
    @NotBlank
    private String JobTitle;
    @Email
    private String Email;
    @NotBlank
    private Integer PhoneNumber;

}

package pl.coderslab.medicalcheckupssender.Employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull
    private String Surname;
    @NotNull
    private String Name;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate DateOfBirth;
    @PESEL
    private Integer PESEL;
    @NotNull
    private String JobTitle; //tutaj referencje do RefferalType
    @Email
    private String Email;
    @NotNull
    private Integer PhoneNumber;
    @NotNull
    private String WorkPlace; //tutaj referencje do HealthFacility

}

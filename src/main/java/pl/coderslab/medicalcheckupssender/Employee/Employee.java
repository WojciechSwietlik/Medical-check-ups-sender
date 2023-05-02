package pl.coderslab.medicalcheckupssender.Employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 30)
    private String surname;
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
   @Past
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @PESEL
    private String pesel;
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String jobTitle; //tutaj referencje do RefferalType
    @NotBlank
    private String workPlace; //tutaj referencje do HealthFacility

}

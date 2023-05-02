package pl.coderslab.medicalcheckupssender.Employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Getter
@Setter
public class EmployeeDto {
    @Schema(description = "Employee id", example = "2")
    private Long id;

    @Schema(description = "Employee surname", example = "Kowalski", required = true)
    @NotBlank
    private String surname;

    @Schema(description = "Employee name", example = "Marek", required = true)
    @NotBlank
    private String name;

  @Schema(description = "Employee date of birth", example = "1990-01-01", required = true)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @PESEL
    private String pesel;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @Schema(description = "Job title", example = "Director", required = true)
    @NotBlank
    private String jobTitle;

    @NotBlank
    private String workPlace;
}

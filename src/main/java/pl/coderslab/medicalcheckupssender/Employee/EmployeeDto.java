package pl.coderslab.medicalcheckupssender.Employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.coderslab.medicalcheckupssender.RefferalType.ReferralType;

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

    @Schema(description = "Employee pesel number", example = "95112646299", required = true)
    @PESEL
    private String pesel;

    @Email
    @Schema(description = "Employee email", example = "employee@gmail.com", required = true)
    private String email;

    @NotBlank
    @Schema(description = "Employee phone number", example = "500 600 700", required = true)
    private String phoneNumber;

    @Schema(description = "Job title", example = "Director", required = true)
    @NotBlank
    private String jobTitle;

    @Schema(description = "City where employee works", example = "Warsaw", required = true)
    @NotBlank
    private String workPlace;

    @Schema(description = "Referral Id", example = "1 - office worker, 2 - callcenter worker, 3 - supervisor, 4 - company car", required = true)
    private Long referralTypeId;
}

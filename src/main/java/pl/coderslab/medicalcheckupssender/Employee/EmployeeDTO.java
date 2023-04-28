package pl.coderslab.medicalcheckupssender.Employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeDTO {
    @Schema(description = "Employee id", example = "2")
    private Long ID;

    @Schema(description = "Employee name", example = "Marek", required = true)
    @NotBlank
    private String Name;

    @Schema(description = "Employee surname", example = "Kowalski", required = true)
    @NotBlank
    private String Surname;

    @Schema(description = "Employee date of birth", example = "1990.01.01", required = true)
    @DateTimeFormat
    private LocalDateTime DateOfBirth;

    @Schema(description = "Job title", example = "Director", required = true)
    @NotBlank
    private String JobTitle;
}

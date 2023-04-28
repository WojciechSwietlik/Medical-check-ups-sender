package pl.coderslab.medicalcheckupssender.RefferalType;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReferralTypeDTO {
    private Long ID;
    @NotNull
    private String Type;
    @NotBlank
    private String InjuryFactors1;
    @NotBlank
    private String InjuryFactors2;
    @NotBlank
    private String InjuryFactors3;
    @NotBlank
    private String InjuryFactors4;
}

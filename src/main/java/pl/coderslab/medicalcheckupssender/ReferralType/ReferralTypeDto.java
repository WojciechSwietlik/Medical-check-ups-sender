package pl.coderslab.medicalcheckupssender.ReferralType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class ReferralTypeDto {
    @Schema(description = "Id of referral type", example = "1")
    private Long id;
    @NotBlank
    @Schema(description = "Description of referral type", example = "1 - office worker, 2 - callcenter worker, 3 - supervisor, 4 - company car")
    private String description;
    @NotBlank
    @Schema(description = "Description of harmful factors", example = "Stress")
    private String harmfulFactors1;
    @NotBlank
    @Schema(description = "Description of harmful factors", example = "Work in a forced position")
    private String harmfulFactors2;
    @NotBlank
    @Schema(description = "Description of harmful factors", example = "Working with headphones")
    private String harmfulFactors3;
    @NotBlank
    @Schema(description = "Description of harmful factors", example = "Decision-making and accountability position")
    private String harmfulFactors4;
    @NotBlank
    @Schema(description = "Description of harmful factors", example = "Driving a company car")
    private String harmfulFactors5;
}
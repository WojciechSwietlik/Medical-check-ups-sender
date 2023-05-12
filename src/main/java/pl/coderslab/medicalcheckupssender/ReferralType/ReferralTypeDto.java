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
    @Schema(description = "Description of referral type", example = "1 - office worker: stress, working in a forced position, 2 - callcenter worker: stress, working in a forced position, working with headphones" +
            " 3 - supervisor: stress, working in a forced position, decision-making position, 4 - company car: stress, working in a forced position, driving a company car")
    private String description;

}
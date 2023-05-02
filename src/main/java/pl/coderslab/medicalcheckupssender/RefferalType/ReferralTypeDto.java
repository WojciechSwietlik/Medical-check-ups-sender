package pl.coderslab.medicalcheckupssender.RefferalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReferralTypeDto {

    private Long id;

    private String type;

    private String injuryFactors1;

    private String injuryFactors2;

    private String injuryFactors3;

    private String injuryFactors4;

    private String injuryFactors5;
}

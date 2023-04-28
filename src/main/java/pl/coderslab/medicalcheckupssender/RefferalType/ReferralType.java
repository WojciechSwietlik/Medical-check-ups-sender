package pl.coderslab.medicalcheckupssender.RefferalType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "Referraltype")
public class ReferralType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull
    private String Type; // referancja do JobTitle
    @NotBlank
    private String InjuryFactors1;
    @NotBlank
    private String InjuryFactors2;
    @NotBlank
    private String InjuryFactors3;
    @NotBlank
    private String InjuryFactors4;


}

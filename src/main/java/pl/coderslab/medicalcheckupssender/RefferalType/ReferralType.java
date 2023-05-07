package pl.coderslab.medicalcheckupssender.RefferalType;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "referral")
public class ReferralType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String description;
    @NotBlank
    private String harmfulFactors1;
    @NotBlank
    private String harmfulFactors2;
    @NotBlank
    private String harmfulFactors3;
    @NotBlank
    private String harmfulFactors4;
    @NotNull
    private String harmfulFactors5;
}

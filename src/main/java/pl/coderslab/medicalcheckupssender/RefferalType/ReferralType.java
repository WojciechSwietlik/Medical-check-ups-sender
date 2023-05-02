package pl.coderslab.medicalcheckupssender.RefferalType;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "referralType")
public class ReferralType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String injuryFactors1;

    private String injuryFactors2;

    private String injuryFactors3;

    private String injuryFactors4;

    private String injuryFactors5;


}

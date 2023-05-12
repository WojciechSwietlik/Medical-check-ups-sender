package pl.coderslab.medicalcheckupssender.ReferralType;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;



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

}

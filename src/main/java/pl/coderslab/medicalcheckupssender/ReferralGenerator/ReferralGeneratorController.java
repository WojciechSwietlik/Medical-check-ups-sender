package pl.coderslab.medicalcheckupssender.ReferralGenerator;

import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReferralGeneratorController {

    private final ReferralGeneratorService referralGeneratorService;

    public ReferralGeneratorController(ReferralGeneratorService referralGeneratorService) {
        this.referralGeneratorService = referralGeneratorService;
    }

    @GetMapping("/api/referral/generate")
    public void generateReferral( @RequestBody ReferralRequest referralRequest) throws IOException, DocumentException {
   referralGeneratorService.generateReferral(referralRequest.getEmployeeId());
    }
}

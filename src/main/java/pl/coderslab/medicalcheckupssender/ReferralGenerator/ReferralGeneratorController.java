package pl.coderslab.medicalcheckupssender.ReferralGenerator;

import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ReferralGeneratorController {

    private final ReferralGeneratorService referralGeneratorService;

    public ReferralGeneratorController(ReferralGeneratorService referralGeneratorService) {
        this.referralGeneratorService = referralGeneratorService;
    }

    @Operation(summary = "Generate referral", description = "Generate referral by employee id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto[].class))),
            @ApiResponse(responseCode = "404", description = "Id Employee doesnt exist")
    })
    @GetMapping("/api/referral/generate/{employeeId}")
    public void generateReferral(@PathVariable Long employeeId, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=Referral_EmployeeId_" + employeeId + "_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        referralGeneratorService.generateReferral(employeeId, response);
    }
}

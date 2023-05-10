package pl.coderslab.medicalcheckupssender.ReferralGenerator;


import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeDto;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeService;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressDto;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressService;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityDto;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityService;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ReferralGeneratorService {
    private final EmployeeService employeeService;
    private final EmployeeAddressService employeeAddressService;
    private final HealthFacilityService healthFacilityService;

    public ReferralGeneratorService(EmployeeService employeeService, EmployeeAddressService employeeAddressService, HealthFacilityService healthFacilityService) {
        this.employeeService = employeeService;
        this.employeeAddressService = employeeAddressService;
        this.healthFacilityService = healthFacilityService;
    }

    @Transactional(readOnly = true)
    public void generateReferral(Long employeeId) throws IOException, DocumentException {
        Document document = new Document();
        EmployeeDto employeeDto = employeeService.getById(employeeId);
        EmployeeAddressDto employeeAddress = employeeAddressService.getByEmployeeId(employeeId);
        List<HealthFacilityDto> healthFacilityDtos = healthFacilityService.findByCityName(employeeAddress.getCity());
        PdfWriter.getInstance(document, new FileOutputStream("Labor Medicine Referral"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER,16, BaseColor.BLACK);
        Chunk text = new Chunk("%s, %s, %s, %s, typ skierowania : %s, placówki w twoim mieście: %s".formatted(
                employeeDto.getName(), employeeDto.getSurname(), employeeDto.getJobTitle(), employeeAddress.toString(),
                employeeDto.getReferralTypeDescription(), healthFacilityDtos.stream().map(HealthFacilityDto::toString), font));
        document.add(text);
        document.close();
    }
}

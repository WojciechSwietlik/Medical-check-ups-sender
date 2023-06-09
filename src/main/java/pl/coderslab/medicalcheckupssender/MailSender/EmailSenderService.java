package pl.coderslab.medicalcheckupssender.MailSender;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeDto;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeService;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressDto;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressService;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityDto;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityService;

import java.util.List;

@Service
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private final EmployeeService employeeService;
    private final EmployeeAddressService employeeAddressService;
    private final HealthFacilityService healthFacilityService;

    public EmailSenderService(JavaMailSender mailSender, EmployeeService employeeService,
                              EmployeeAddressService employeeAddressService, HealthFacilityService healthFacilityService) {
        this.mailSender = mailSender;
        this.employeeService = employeeService;
        this.employeeAddressService = employeeAddressService;
        this.healthFacilityService = healthFacilityService;
    }

    @Transactional(readOnly = true)
    public void sendEmail(Long employeeId) {
        EmployeeDto employeeDto = employeeService.getById(employeeId);
        EmployeeAddressDto employeeAddress = employeeAddressService.getByEmployeeId(employeeId);
        List<HealthFacilityDto> healthFacilityDtos = healthFacilityService.findByCityName(employeeAddress.getCity());
        String message = "%s, %s, %s, %s, typ skierowania : %s, placówki w twoim mieście: %s".formatted(
                employeeDto.getName(), employeeDto.getSurname(), employeeDto.getJobTitle(), employeeAddress.toString(),
                employeeDto.getReferralTypeDescription(), healthFacilityDtos.stream().map(HealthFacilityDto::toString)
        );

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("medicalcheckupssender@op.pl");
        simpleMailMessage.setTo("medicalcheckupssender@op.pl"); //(employeeDto.getEmail());
        simpleMailMessage.setSubject("Referral to medical checkups");
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);
    }
}
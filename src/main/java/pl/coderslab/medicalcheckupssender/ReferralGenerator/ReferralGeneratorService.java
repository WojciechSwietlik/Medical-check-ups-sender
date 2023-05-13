package pl.coderslab.medicalcheckupssender.ReferralGenerator;


import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeDto;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeService;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressDto;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class ReferralGeneratorService {
    private final EmployeeService employeeService;
    private final EmployeeAddressService employeeAddressService;


    public ReferralGeneratorService(EmployeeService employeeService, EmployeeAddressService employeeAddressService) {
        this.employeeService = employeeService;
        this.employeeAddressService = employeeAddressService;
    }

    @Transactional(readOnly = true)
    public void generateReferral(Long employeeId, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        EmployeeDto employeeDto = employeeService.getById(employeeId);
        EmployeeAddressDto employeeAddress = employeeAddressService.getByEmployeeId(employeeId);

        LocalDate localDate = LocalDate.now();

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);

        Paragraph paragraph = new Paragraph("Labor Medicine Referral", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
        Paragraph text = new Paragraph("Surname: %s, name: %s".formatted(
                employeeDto.getSurname(), employeeDto.getName(), font));
        Paragraph text5 = new Paragraph("Date of birth: %s".formatted(employeeDto.getDateOfBirth(), font));
        Paragraph text6 = new Paragraph("Employee PESEL: %s".formatted(employeeDto.getPesel(), font));
        Paragraph text4 = new Paragraph("Employee address:");
        Paragraph text2 = new Paragraph("%s".formatted(
                employeeAddress.toString(), font));
        Paragraph text3 = new Paragraph("Referral type and harmful factors: %s".formatted(
                employeeDto.getReferralTypeDescription(), font));
        Paragraph dateTime = new Paragraph("Date of generate referral: %s".formatted(String.valueOf(localDate), font));
        dateTime.setAlignment(Paragraph.ALIGN_BOTTOM);

        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        document.add(text);
        document.add(Chunk.NEWLINE);
        document.add(text5);
        document.add(Chunk.NEWLINE);
        document.add(text6);
        document.add(Chunk.NEWLINE);
        document.add(text4);
        document.add(Chunk.NEWLINE);
        document.add(text2);
        document.add(Chunk.NEWLINE);
        document.add(text3);
        document.add(Chunk.NEWLINE);
        document.add(dateTime);
        document.close();
    }
}

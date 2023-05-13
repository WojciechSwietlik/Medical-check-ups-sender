package pl.coderslab.medicalcheckupssender.ServiceTest;

import pl.coderslab.medicalcheckupssender.EmployeeTest.EmployeeTest;

import java.util.List;

public interface EmployeeTestService {
    List<EmployeeTest> listAllEmployees();
    EmployeeTest findByName(String name);
    EmployeeTest addEmployeeTest(EmployeeTest employeeTest);
}

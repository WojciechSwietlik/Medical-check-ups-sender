package pl.coderslab.medicalcheckupssender.ServiceTest;

import pl.coderslab.medicalcheckupssender.EmployeeTest.EmployeeTest;
import pl.coderslab.medicalcheckupssender.RepositoryTest.EmployeeTestRepository;

import java.util.List;

public class EmployeeTestServiceImpl implements EmployeeTestService{

    private final EmployeeTestRepository repository;

    public EmployeeTestServiceImpl(EmployeeTestRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<EmployeeTest> listAllEmployees() {
        return null;
    }

    @Override
    public EmployeeTest findByName(String name) {
        return null;
    }

    @Override
    public EmployeeTest addEmployeeTest(EmployeeTest employeeTest) {
        return null;
    }
}

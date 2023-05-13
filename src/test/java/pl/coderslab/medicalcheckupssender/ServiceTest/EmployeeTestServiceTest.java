package pl.coderslab.medicalcheckupssender.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.medicalcheckupssender.EmployeeTest.EmployeeTest;
import pl.coderslab.medicalcheckupssender.RepositoryTest.EmployeeTestRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeTestServiceTest {

    private EmployeeTestService service;
    private EmployeeTestRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeTestRepository.class);
        service = new EmployeeTestServiceImpl(repository);
    }

    @Test
    public void when_searching_john_then_return_object() {
        //given
        EmployeeTest john = new EmployeeTest("John");
        when(repository.findByName("John")).thenReturn(john);
        //when
        EmployeeTest employeeTest = service.findByName("John");
        //then
        assertEquals("John", employeeTest.getName());
    }

    @Test
    public void when_save_student_that_it_is_returned_correctly(){
        //given
        EmployeeTest employeeTest = new EmployeeTest("John");
        when(repository.save(employeeTest)).thenReturn(employeeTest);
        //when
        EmployeeTest result = service.addEmployeeTest(employeeTest);
        //then
        assertNotNull(result);
        assertEquals(employeeTest.getName(), result.getName());
    }

    @Test
    public void when_finding_all_students_they_should_be_returned() {
        //given
        EmployeeTest mat = new EmployeeTest("mat");
        EmployeeTest kate = new EmployeeTest("kate");
        when(repository.findAll()).thenReturn(List.of(mat,kate));
        //when
        List<EmployeeTest> employeeTests = service.listAllEmployees();
        //then
        assertThat(employeeTests).containsExactly(mat,kate);
    }

}

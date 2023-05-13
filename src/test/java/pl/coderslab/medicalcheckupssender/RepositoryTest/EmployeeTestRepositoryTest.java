package pl.coderslab.medicalcheckupssender.RepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.medicalcheckupssender.EmployeeTest.EmployeeTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EmployeeTestRepositoryTest {

    @Autowired
    private final TestEntityManager entityManager;

    @Autowired
    private EmployeeTestRepository employeeTestRepository;

    public EmployeeTestRepositoryTest(TestEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Test
    public void find_by_first_name_then_return_employee() {
        //given
        EmployeeTest employeeTest = new EmployeeTest();
        employeeTest.setName("John");
        entityManager.persist(employeeTest);
        //when
        EmployeeTest result = employeeTestRepository.findByName("John");
        //then
        assertEquals(employeeTest.getName(), result.getName());
    }

    @Test
    public void given_mark_then_john_should_be_null() {
        //given
        EmployeeTest employeeTest = new EmployeeTest();
        employeeTest.setName("Mark");
        entityManager.persist(employeeTest);
        //when
        EmployeeTest result = employeeTestRepository.findByName("John");
        //then
        assertNull(result);
    }

    @Test
    public void given_jo_and_john_then_find_jo_should_return_two_elements() {
        //given
        EmployeeTest jo = entityManager.persistAndFlush(new EmployeeTest("jo"));
        EmployeeTest john = entityManager.persistAndFlush(new EmployeeTest("john"));
        //when
        List<EmployeeTest> result = employeeTestRepository.findBySome("jo");
        //then
        assertThat(result).containsExactly(jo, john);
    }

    @Test
    public void given_mark_and_mo_then_find_jo_returns_no_result() {
        //given
        entityManager.persistAndFlush(new EmployeeTest("mo"));
        entityManager.persistAndFlush(new EmployeeTest("mark"));
        //when
        List<EmployeeTest> result = EmployeeTestRepository.findBySome("jo");
        //then
        assertThat(result).isEmpty();
    }


}


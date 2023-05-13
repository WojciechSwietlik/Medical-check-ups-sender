package pl.coderslab.medicalcheckupssender.RepositoryTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.medicalcheckupssender.EmployeeTest.EmployeeTest;

import java.util.List;


public interface EmployeeTestRepository extends JpaRepository<EmployeeTest, Long> {
    EmployeeTest findByName(String name);

    @Query("select e from Employee e where e.name like ?1%")
    List<EmployeeTest> findBySome(String some);
}

package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {
    Optional<EmployeeAddress> findByEmployee_Id(Long id);
}

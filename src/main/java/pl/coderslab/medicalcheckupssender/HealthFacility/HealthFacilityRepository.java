package pl.coderslab.medicalcheckupssender.HealthFacility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HealthFacilityRepository extends JpaRepository <HealthFacility, Long>{
    List<HealthFacility> findHealthFacilityByCity(String city);
}

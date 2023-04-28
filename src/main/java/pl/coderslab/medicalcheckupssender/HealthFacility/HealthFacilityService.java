package pl.coderslab.medicalcheckupssender.HealthFacility;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeRepository;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;
import pl.coderslab.medicalcheckupssender.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class HealthFacilityService {

    private final HealthFacilityRepository healthFacilityRepository;
    private final EmployeeRepository employeeRepository;
    private final HealthFacilityMapper healthFacilityMapper;

    public HealthFacilityService(HealthFacilityRepository healthFacilityRepository, EmployeeRepository employeeRepository,
                                 HealthFacilityMapper healthFacilityMapper) {
        this.healthFacilityRepository = healthFacilityRepository;
        this.employeeRepository = employeeRepository;
        this.healthFacilityMapper = healthFacilityMapper;
    }

    public List<HealthFacilityDTO> getAll() {
        return healthFacilityMapper.mapToDto(healthFacilityRepository.findAll());
    }

    public HealthFacilityDTO getById(Long id) {
        return healthFacilityMapper.mapToDto(healthFacilityRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        healthFacilityRepository.deleteById(id);
    }

    public HealthFacilityDTO addHealthFacility(HealthFacilityDTO dto) {
        HealthFacility healthFacility = healthFacilityMapper.mapToEntity(dto);
        Assert.isNull(HealthFacility.class, "ID has to be null");
        HealthFacility healthFacility1 = null;
        try {
            healthFacility = healthFacilityRepository.findById(dto.getHealthFacilityID()).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        healthFacility.setName(String.valueOf(healthFacility));
        healthFacilityRepository.save(healthFacility);
        return healthFacilityMapper.mapToDto(healthFacility);
    }

    public HealthFacilityDTO updateHealthFacility(Long id, HealthFacilityDTO dto) {
        Assert.notNull(dto.getHealthFacilityID(), "ID cannot be null");
        if (!dto.getHealthFacilityID().equals(id)) {
            try {
                throw new IdMismatchException("ID's mismatch");
            } catch (IdMismatchException e) {
                throw new RuntimeException(e);
            }
        }
        if (!healthFacilityRepository.existsById(id)) {
            try {
                throw new ResourceNotFoundException("That health facility doesn't exist");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        HealthFacility entity = healthFacilityMapper.mapToEntity(dto);
        HealthFacility healthFacility = null;
        try {
            healthFacility = healthFacilityRepository.findById(dto.getHealthFacilityID()).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        entity.setName(String.valueOf(healthFacility));
        healthFacilityRepository.save(entity);
        return healthFacilityMapper.mapToDto(entity);
    }
}

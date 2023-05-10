package pl.coderslab.medicalcheckupssender.HealthFacility;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class HealthFacilityService {

    private final HealthFacilityRepository healthFacilityRepository;
    private final HealthFacilityMapper healthFacilityMapper;

    public HealthFacilityService(HealthFacilityRepository healthFacilityRepository, HealthFacilityMapper healthFacilityMapper) {
        this.healthFacilityRepository = healthFacilityRepository;
        this.healthFacilityMapper = healthFacilityMapper;
    }

    public HealthFacilityDto addHealthFacility(HealthFacilityDto dto) {
        HealthFacility healthFacility = healthFacilityMapper.mapToEntity(dto);
        Assert.isNull(healthFacility.getId(), "Id has to be null");
        healthFacilityRepository.save(healthFacility);
        return healthFacilityMapper.mapToDto(healthFacility);
    }

    public HealthFacilityDto updateHealthFacility(Long id, HealthFacilityDto dto) throws IdMismatchException {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id)) {
            throw new IdMismatchException("Id's mismatch");
        }
        if (!healthFacilityRepository.existsById(id)) {
            throw new EntityNotFoundException("Health facility doesn't exist");
        }
        HealthFacility entity = healthFacilityMapper.mapToEntity(dto);
        healthFacilityRepository.save(entity);
        return healthFacilityMapper.mapToDto(entity);
    }

    public List<HealthFacilityDto> getAll() {
        return healthFacilityMapper.mapToDto(healthFacilityRepository.findAll());
    }

    public List<HealthFacilityDto> findByCityName(String cityName) {
        return healthFacilityMapper.mapToDto(healthFacilityRepository.findHealthFacilityByCity(cityName));
    }

    public HealthFacilityDto getById(Long id) {
        return healthFacilityMapper.mapToDto(healthFacilityRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        healthFacilityRepository.deleteById(id);
    }

}
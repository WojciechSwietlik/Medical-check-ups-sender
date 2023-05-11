package pl.coderslab.medicalcheckupssender.ReferralType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ReferralTypeService {

    private final ReferralTypeRepository referralTypeRepository;
    private final ReferralTypeMapper referralTypeMapper;

    public ReferralTypeService(ReferralTypeRepository referralTypeRepository, ReferralTypeMapper referralTypeMapper) {
        this.referralTypeRepository = referralTypeRepository;
        this.referralTypeMapper = referralTypeMapper;
    }

    public ReferralTypeDto addReferralType(ReferralTypeDto dto) {
        ReferralType referral = referralTypeMapper.mapToEntity(dto);
        Assert.isNull(referral.getId(), "Id has to be null");
        referralTypeRepository.save(referral);
        return referralTypeMapper.mapToDto(referral);
    }

    public ReferralTypeDto updateRefferalType(Long id, ReferralTypeDto dto) throws IdMismatchException {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id)) {
            throw new IdMismatchException("Id's mismatch");
        }
        if (!referralTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Refferal type doesn't exist");
        }
        ReferralType entity = referralTypeMapper.mapToEntity(dto);
        referralTypeRepository.save(entity);
        return referralTypeMapper.mapToDto(entity);
    }
    public List<ReferralTypeDto> getAll() {
        return referralTypeMapper.mapToDto(referralTypeRepository.findAll());
    }

    public ReferralTypeDto getById(Long id) {
        return referralTypeMapper.mapToDto(referralTypeRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        referralTypeRepository.deleteById(id);
    }

    public List<ReferralTypeDto> findByCityName(String cityName) {
        return healthFacilityMapper.mapToDto(healthFacilityRepository.findHealthFacilityByCity(cityName));
    }

}
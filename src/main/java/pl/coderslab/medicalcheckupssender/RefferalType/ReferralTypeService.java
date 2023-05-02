package pl.coderslab.medicalcheckupssender.RefferalType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


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

    public List<ReferralTypeDto> getAll() {
        return referralTypeMapper.mapToDto(referralTypeRepository.findAll());
    }

    public ReferralTypeDto getById(Long id) {
        return referralTypeMapper.mapToDto(referralTypeRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        referralTypeRepository.deleteById(id);
    }

    public ReferralTypeDto addReferralType(ReferralTypeDto dto) {
        ReferralType referralType = referralTypeMapper.mapToEntity(dto);
        Assert.isNull(referralType.getId(), "Id has to be null");
        referralTypeRepository.save(referralType);
        return referralTypeMapper.mapToDto(referralType);
    }

    public ReferralTypeDto updateRefferalType(Long id, ReferralTypeDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (dto.getId().equals(id)) {
            throw new IllegalArgumentException("Id's mismatch");
        }
        if (!referralTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Refferal type doesn't exist");
        }
        ReferralType entity = referralTypeMapper.mapToEntity(dto);
        referralTypeRepository.save(entity);
        return referralTypeMapper.mapToDto(entity);
    }
}
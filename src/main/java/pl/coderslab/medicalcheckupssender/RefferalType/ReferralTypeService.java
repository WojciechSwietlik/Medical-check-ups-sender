package pl.coderslab.medicalcheckupssender.RefferalType;

import org.springframework.util.Assert;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;
import pl.coderslab.medicalcheckupssender.Exception.ResourceNotFoundException;

import java.util.List;

public class ReferralTypeService {

    private final ReferralTypeRepository referralTypeRepository;
    ;
    private final ReferralTypeMapper referralTypeMapper;

    public ReferralTypeService(ReferralTypeRepository referralTypeRepository, ReferralTypeMapper referralTypeMapper) {
        this.referralTypeRepository = referralTypeRepository;
        this.referralTypeMapper = referralTypeMapper;
    }

    public List<ReferralTypeDTO> getAll() {
        return (List<ReferralTypeDTO>) referralTypeMapper.mapToDto(referralTypeRepository.findAll());
    }

    public ReferralTypeDTO getById(Long id) {
        return referralTypeMapper.mapToDto((List<ReferralType>) referralTypeRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        referralTypeRepository.deleteById(id);
    }

    public ReferralTypeDTO addRefferalType(ReferralTypeDTO dto) {
        ReferralType referralType = referralTypeMapper.mapToEntity(dto);
        Assert.isNull(referralType.getID(), "ID has to be null");
        try {
            ReferralType referralType1 = referralTypeRepository.findById(dto.getID()).orElseThrow(() -> new ResourceNotFoundException("Refferal type doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        referralType.setType(String.valueOf(dto));
        referralTypeRepository.save(referralType);
        return referralTypeMapper.mapToDto((List<ReferralType>) dto);
    }

    public ReferralTypeDTO updateRefferalType(Long id, ReferralTypeDTO dto) {
        Assert.notNull(dto.getID(), "ID cannot be empty");
        if (!dto.getID().equals(id)) {
            try {
                throw new IdMismatchException("ID's mismatch");
            } catch (IdMismatchException e) {
                throw new RuntimeException(e);
            }
        }
        if (!referralTypeRepository.existsById(id)) {
            try {
                throw new ResourceNotFoundException("RefferalType doesn't exist");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        ReferralType entity = referralTypeMapper.mapToEntity(dto);
        ReferralType referralType = null;
        try {
            referralType = referralTypeRepository.findById(dto.getID()).orElseThrow(() -> new ResourceNotFoundException("Movie doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        entity.setType(String.valueOf(referralType));
        referralTypeRepository.save(entity);
        return referralTypeMapper.mapToDto((List<ReferralType>) entity);
    }
}

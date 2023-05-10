package pl.coderslab.medicalcheckupssender.ReferralType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReferralTypeMapper {

    ReferralType mapToEntity(ReferralTypeDto dto);

    ReferralTypeDto mapToDto(ReferralType referralType);

    List<ReferralTypeDto> mapToDto(List<ReferralType> referralTypes);
}
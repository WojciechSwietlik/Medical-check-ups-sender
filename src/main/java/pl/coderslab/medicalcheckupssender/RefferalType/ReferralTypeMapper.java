package pl.coderslab.medicalcheckupssender.RefferalType;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ReferralTypeMapper {
    ReferralType mapToEntity(ReferralTypeDTO dto);

    @Mapping(source = "refferaltype.type", target = "employee.jobtitle")
    ReferralTypeDTO mapToDto(List<ReferralType> entity);

}

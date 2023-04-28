package pl.coderslab.medicalcheckupssender.HealthFacility;

import org.apache.coyote.http11.HeadersTooLargeException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HealthFacilityMapper {
    HealthFacility mapToEntity(HealthFacilityDTO dto);

    @Mapping(source = "healthfacility.city", target = "employeeworkplace")
    HealthFacilityDTO mapToDto(HealthFacilityDTO entity);

    List<HealthFacilityDTO> mapToDto(List<HealthFacility> all);

    HealthFacilityDTO mapToDto(HealthFacility orElse);
}

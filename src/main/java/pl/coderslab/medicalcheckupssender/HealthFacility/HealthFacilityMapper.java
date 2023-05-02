package pl.coderslab.medicalcheckupssender.HealthFacility;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HealthFacilityMapper {

    HealthFacility mapToEntity(HealthFacilityDto dto);

    HealthFacilityDto mapToDto(HealthFacility healthFacility);

    List<HealthFacilityDto> mapToDto(List<HealthFacility> healthFacilities);
}
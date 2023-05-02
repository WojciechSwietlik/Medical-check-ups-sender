package pl.coderslab.medicalcheckupssender.EmployeeAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeAddressMapper {

    EmployeeAddress mapToEntity(EmployeeAddressDto dto);

    @Mapping(source = "employee.id", target = "employeeId")
    EmployeeAddressDto mapToDto(EmployeeAddress entity);

    List<EmployeeAddressDto> mapToDto(List<EmployeeAddress> addresses);
}
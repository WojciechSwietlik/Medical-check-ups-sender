package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeAddressMapper {
    EmployeeAddress mapToEntity(EmployeeAddressDTO dto);

    @Mapping(source = "employee.id", target = "employeeId")
    EmployeeAddressDTO mapToDto(EmployeeAddress entity);

    List<EmployeeAddressDTO> mapToDto(List<EmployeeAddress> all);
}

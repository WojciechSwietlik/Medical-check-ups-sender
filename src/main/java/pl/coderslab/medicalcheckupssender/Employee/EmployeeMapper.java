package pl.coderslab.medicalcheckupssender.Employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapToEntity(EmployeeDto dto);

    @Mapping(source = "referralType.id", target = "referralTypeId")
    EmployeeDto mapToDto(Employee entity);

    List<EmployeeDto> mapToDto(List<Employee> employees);
}
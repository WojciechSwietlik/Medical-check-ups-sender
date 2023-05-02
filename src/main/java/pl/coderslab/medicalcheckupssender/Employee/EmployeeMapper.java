package pl.coderslab.medicalcheckupssender.Employee;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapToEntity(EmployeeDto dto);

    EmployeeDto mapToDto(Employee employee);

    List<EmployeeDto> mapToDto(List<Employee> employees);
}
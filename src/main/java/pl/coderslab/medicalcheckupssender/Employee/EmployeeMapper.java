package pl.coderslab.medicalcheckupssender.Employee;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee mapToEntity(EmployeeDTO employeeDTO);

    EmployeeDTO mapToDto(Employee employee);

    List<Employee> mapToDto(List<Employee> employees);
}

package pl.coderslab.medicalcheckupssender.Employee;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;
import pl.coderslab.medicalcheckupssender.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDto> getAll() {
        return employeeMapper.mapToDto(employeeRepository.findAll());
    }

    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = employeeMapper.mapToEntity(dto);
        Assert.isNull(employee.getId(), "Id has to be null");
        employeeRepository.save(employee);
        return employeeMapper.mapToDto(employee);
    }

    public EmployeeDto getById(Long id) {
        return employeeMapper.mapToDto(employeeRepository.findById(id).orElse(null));
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) throws IdMismatchException, ResourceNotFoundException {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id)) {
            throw new IdMismatchException("Id's mismatch");
        }
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee doesn't exist");
        }
        Employee entity = employeeMapper.mapToEntity(dto);
        employeeRepository.save(entity);
        return employeeMapper.mapToDto(entity);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
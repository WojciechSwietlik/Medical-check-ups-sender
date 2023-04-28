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

    public List<Employee> getAll() {
        return employeeMapper.mapToDto(employeeRepository.findAll());
    }

    public EmployeeDTO getById(Long id) {
        return employeeMapper.mapToDto(employeeRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.mapToEntity(employeeDTO);
        Assert.isNull(employee.getID(), "ID cannot be null");
        employeeRepository.save(employee);
        return employeeMapper.mapToDto(employee);
    }


    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) throws IdMismatchException {
        Assert.notNull(employeeDTO.getID(), "ID cannot be null");
        if (!employeeDTO.getID().equals(id)) {
            throw new IdMismatchException("ID's mismatch");
        }
        if (!employeeRepository.existsById(id)) {
            try {
                throw new ResourceNotFoundException("Employee doesn't exist");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        Employee entity = employeeMapper.mapToEntity(employeeDTO);
        employeeRepository.save(entity);
        return employeeMapper.mapToDto(entity);
    }
}

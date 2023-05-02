package pl.coderslab.medicalcheckupssender.EmployeeAddress;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.coderslab.medicalcheckupssender.Employee.Employee;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeRepository;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;
import pl.coderslab.medicalcheckupssender.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class EmployeeAddressService {

    private final EmployeeAddressRepository employeeAddressRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeAddressMapper employeeAddressMapper;

    public EmployeeAddressService(EmployeeAddressRepository employeeAddressRepository, EmployeeRepository employeeRepository, EmployeeAddressMapper employeeAddressMapper) {
        this.employeeAddressRepository = employeeAddressRepository;
        this.employeeRepository = employeeRepository;
        this.employeeAddressMapper = employeeAddressMapper;
    }

    public List<EmployeeAddressDto> getAll() {
        return employeeAddressMapper.mapToDto(employeeAddressRepository.findAll());
    }

    public EmployeeAddressDto getById(Long id) {
        return employeeAddressMapper.mapToDto(employeeAddressRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        employeeAddressRepository.deleteById(id);
    }

    public EmployeeAddressDto addEmployeeAddress(EmployeeAddressDto dto) {
        EmployeeAddress address = employeeAddressMapper.mapToEntity(dto);
        Assert.isNull(address.getId(), "Id has to be null");
        Employee employee;
        try {
            employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow(() -> new ResourceNotFoundException("Address doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        address.setEmployee(employee);
        employeeAddressRepository.save(address);
        return employeeAddressMapper.mapToDto(address);
    }

    public EmployeeAddressDto updateEmployeeAddress(Long id, EmployeeAddressDto dto) {
        Assert.notNull(dto.getId(), "Id cannot be empty");
        if (!dto.getId().equals(id)) {
            try {
                throw new IdMismatchException("Id's mismatch");
            } catch (IdMismatchException e) {
                throw new RuntimeException(e);
            }
        }
        if (!employeeAddressRepository.existsById(id)) {
            try {
                throw new ResourceNotFoundException("Employee address doesn't exist");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        EmployeeAddress entity = employeeAddressMapper.mapToEntity(dto);
        Employee employee;
        try {
            employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        entity.setEmployee(employee);
        employeeAddressRepository.save(entity);
        return employeeAddressMapper.mapToDto(entity);
    }
}
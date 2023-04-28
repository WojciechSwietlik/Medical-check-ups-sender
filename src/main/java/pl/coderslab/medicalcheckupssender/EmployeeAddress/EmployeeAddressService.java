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

    public EmployeeAddressService(EmployeeAddressRepository employeeAddressRepository, EmployeeRepository employeeRepository,
                                  EmployeeAddressMapper employeeAddressMapper) {
        this.employeeAddressRepository = employeeAddressRepository;
        this.employeeRepository = employeeRepository;
        this.employeeAddressMapper = employeeAddressMapper;
    }

    public List<EmployeeAddressDTO> getAll() {
        return employeeAddressMapper.mapToDto(employeeAddressRepository.findAll());
    }

    public EmployeeAddressDTO getById(Long id) {
        return employeeAddressMapper.mapToDto(employeeAddressRepository.findById(id).orElse(null));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeAddressDTO addEmployeeAddress(EmployeeAddressDTO dto) {
        EmployeeAddress employeeAddress = employeeAddressMapper.mapToEntity(dto);
        Assert.isNull(employeeAddress.getID(), "Id has to be null");
        Employee employee = null;
        try {
            employee = employeeRepository.findById(dto.getEmployeeID()).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        employeeAddress.setEmployee(employee);
        employeeAddressRepository.save(employeeAddress);
        return employeeAddressMapper.mapToDto(employeeAddress);
    }

    public EmployeeAddressDTO updateEmplyeeAddress(Long id, EmployeeAddressDTO dto) {
        Assert.notNull(dto.getEmployeeID(), "ID cannot be null");
        if (!dto.getEmployeeID().equals(id)) {
            try {
                throw new IdMismatchException("ID's mismatch");
            } catch (IdMismatchException e) {
                throw new RuntimeException(e);
            }
        }
        if (!employeeAddressRepository.existsById(id)) {
            try {
                throw new ResourceNotFoundException("That emloyee address doesn't exist");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        EmployeeAddress entity = employeeAddressMapper.mapToEntity(dto);
        Employee employee = null;
        try {
            employee = employeeRepository.findById(dto.getEmployeeID()).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        entity.setEmployee(employee);
        employeeAddressRepository.save(entity);
        return employeeAddressMapper.mapToDto(entity);
    }
}
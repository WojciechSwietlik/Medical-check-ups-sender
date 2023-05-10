package pl.coderslab.medicalcheckupssender.Employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.medicalcheckupssender.ReferralType.ReferralTypeRepository;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Autowired
    ReferralTypeRepository referralTypeRepository;

    @Mappings({@Mapping(target = "referralType", expression = "java(referralTypeRepository.findById(dto.getReferralTypeId()).get())")})

    public abstract Employee mapToEntity(EmployeeDto dto);

    @Mapping(source = "referralType.id", target = "referralTypeId")
    @Mapping(source = "referralType.description", target = "referralTypeDescription")
    public abstract EmployeeDto mapToDto(Employee entity);

    public abstract List<EmployeeDto> mapToDto(List<Employee> employees);
}
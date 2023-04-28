package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.medicalcheckupssender.Employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "EmployeeAddress")
public class EmployeeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @OneToOne
    @NotNull
    Employee employee;
    @NotNull
    private String StreetName;
    @NotNull
    private Integer HouseNumber;
    @NotNull
    private Integer ApartmentNumber;
    @NotNull
    private Integer ZipCode;
    @NotNull
    private String City;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EmployeeAddress ea) {
            return Objects.equals(ID, ea.ID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }
}

package pl.coderslab.medicalcheckupssender.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;
import pl.coderslab.medicalcheckupssender.Exception.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Add new employee", description = "Fill data about employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto[].class))),
            @ApiResponse(responseCode = "404", description = "Datas are incomplete")
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody @Valid EmployeeDto employee) {
        EmployeeDto dto = employeeService.addEmployee(employee);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Gets all employees", description = "Gets list of all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto[].class))),
            @ApiResponse(responseCode = "404", description = "Employees cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee() {
        List<EmployeeDto> employee = employeeService.getAll();
        if (employee.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employee);
        }
    }

    @Operation(summary = "Gets employee by id", description = "Gets employee data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto[].class))),
            @ApiResponse(responseCode = "404", description = "Employee cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        EmployeeDto dto = employeeService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update employee data by id", description = "Update employee data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto[].class))),
            @ApiResponse(responseCode = "404", description = "Employee cannot be found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employee) throws IdMismatchException, ResourceNotFoundException {
        EmployeeDto dto = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete employee data by id", description = "Delete employee data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto[].class))),
            @ApiResponse(responseCode = "404", description = "Employee cannot be found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
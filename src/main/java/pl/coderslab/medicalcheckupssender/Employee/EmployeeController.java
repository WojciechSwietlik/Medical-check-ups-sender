package pl.coderslab.medicalcheckupssender.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employee's", description = "Get list of all employee's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDTO[].class))),
            @ApiResponse(responseCode = "404", description = "Employee's cannot be found")
    })

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employee) {
        EmployeeDTO employeeDTO = employeeService.addEmployee(employee);
        return ResponseEntity.ok(employeeDTO);
    }

    @Operation(summary = "Get employee by id", description = "Get employee based on it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Employee cannot be found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getById(id);
        return employeeDTO != null ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employee) throws IdMismatchException {
        EmployeeDTO employeeDTO = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
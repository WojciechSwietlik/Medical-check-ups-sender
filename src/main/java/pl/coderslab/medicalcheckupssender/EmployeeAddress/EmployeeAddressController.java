package pl.coderslab.medicalcheckupssender.EmployeeAddress;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees/address")
public class EmployeeAddressController {

    private final EmployeeAddressService employeeAddressService;

    public EmployeeAddressController(EmployeeAddressService employeeAddressService) {
        this.employeeAddressService = employeeAddressService;
    }

    @Operation(summary = "Gets all employees addresses", description = "Gets list of all employees addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto[].class))),
            @ApiResponse(responseCode = "404", description = "Movie displays cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<EmployeeAddressDto>> getEmployeeAddress() {
        List<EmployeeAddressDto> employeeAddressDtos = employeeAddressService.getAll();
        if (employeeAddressDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeAddressDtos);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<EmployeeAddressDto> addEmployeeAddress(@RequestBody @Valid EmployeeAddressDto addressDto) {
        EmployeeAddressDto employeeDto = employeeAddressService.addEmployeeAddress(addressDto);
        return ResponseEntity.ok(employeeDto);
    }

    @Operation(summary = "Gets employee address by id", description = "Get employee address based on it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "404", description = "Employee address cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAddressDto> getEmployeeAddress(@Parameter(description = "Employee address to be saved") @PathVariable Long id) {
        EmployeeAddressDto dto = employeeAddressService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAddressDto> updateEmployeeAddress(@PathVariable Long id, @RequestBody @Valid EmployeeAddressDto employeeAddressDto) {
        EmployeeAddressDto dto = employeeAddressService.updateEmployeeAddress(id, employeeAddressDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeAddress(@PathVariable Long id) {
        employeeAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
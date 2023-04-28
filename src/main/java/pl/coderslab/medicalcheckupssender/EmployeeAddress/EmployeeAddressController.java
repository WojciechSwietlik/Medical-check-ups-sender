package pl.coderslab.medicalcheckupssender.EmployeeAddress;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.medicalcheckupssender.Employee.EmployeeDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/EmployeeAddress")
public class EmployeeAddressController {

    private final EmployeeAddressService employeeAddressService;

    public EmployeeAddressController(EmployeeAddressService employeeAddressService) {
        this.employeeAddressService = employeeAddressService;
    }

/*  @Operation(summary = "Get all employee address", description = "Get list of all employee address")
    @ApiResponse{
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(schema = @Schema(implementation = EmployeeDTO[].class))),
            @ApiResponse(responseCode = "404", description = "Employee address cannot be found")
    })*/

    @GetMapping
    public ResponseEntity<List<EmployeeAddressDTO>> getEmployeeAddress() {
        List<EmployeeAddressDTO> employeeAddress = employeeAddressService.getAll();
        if (employeeAddress.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeAddress);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeAddressDTO> addEmployeeAddress(@RequestBody @Valid EmployeeAddressDTO addressDTO) {
        EmployeeAddressDTO employeeAddressDTO = employeeAddressService.addEmployeeAddress(addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    @Operation(summary = "Gets employee address by id", description = "Get employee address based on it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Employee address cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAddressDTO> getEmployeeAddress(@PathVariable Long id) {
        EmployeeAddressDTO dto = employeeAddressService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAddressDTO> updateMovieDisplay(@PathVariable Long id, @RequestBody @Valid EmployeeAddressDTO addressDTO) {
        EmployeeAddressDTO dto = employeeAddressService.updateEmplyeeAddress(id, addressDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeAddress(@PathVariable Long id) {
        employeeAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
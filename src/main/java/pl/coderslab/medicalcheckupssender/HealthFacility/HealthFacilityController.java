package pl.coderslab.medicalcheckupssender.HealthFacility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressDto;
import pl.coderslab.medicalcheckupssender.Exception.IdMismatchException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/healthFacility")
public class HealthFacilityController {
    private final HealthFacilityService healthFacilityService;

    public HealthFacilityController(HealthFacilityService healthFacilityService) {
        this.healthFacilityService = healthFacilityService;
    }

    @Operation(summary = "Add health facility", description = "Fill data about health facility")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Datas are incomplited")
    })
    @PostMapping
    public ResponseEntity<HealthFacilityDto> addHealthFacility(@RequestBody @Valid HealthFacilityDto healthFacility) {
        HealthFacilityDto dto = healthFacilityService.addHealthFacility(healthFacility);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Gets list all health facilities", description = "Gets list od all health facilities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Health facilities cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<HealthFacilityDto>> getHealthFacilities() {
        List<HealthFacilityDto> healthFacility = healthFacilityService.getAll();
        if (healthFacility.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(healthFacility);
        }
    }

    @Operation(summary = "Gets health facility by id", description = "Gets health facility by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Health facility cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HealthFacilityDto> getHealthFacility(@PathVariable Long id) {
        HealthFacilityDto dto = healthFacilityService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update data of health facility by id", description = "Update health facility data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Health facility cannot be found")
    })

    @PutMapping("/{id}")
    public ResponseEntity<HealthFacilityDto> updateHealthFacility(@PathVariable Long id, @RequestBody @Valid HealthFacilityDto healthFacility) throws IdMismatchException {
        HealthFacilityDto dto = healthFacilityService.updateHealthFacility(id, healthFacility);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete data of health facility by id", description = "Delete health facility by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Health facility cannot be found")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHealthFacility(@PathVariable Long id) {
        healthFacilityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
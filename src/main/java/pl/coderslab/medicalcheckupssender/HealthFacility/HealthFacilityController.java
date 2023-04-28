package pl.coderslab.medicalcheckupssender.HealthFacility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/HealthFacility")
public class HealthFacilityController {

    private final HealthFacilityService healthFacilityService;

    public HealthFacilityController(HealthFacilityService healthFacilityService) {
        this.healthFacilityService = healthFacilityService;
    }

    @Operation(summary = "Gets all health facilities list", description = "Gets list of all health facilities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = HealthFacilityDTO[].class))),
            @ApiResponse(responseCode = "404", description = "Health facilities cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<HealthFacilityDTO>> getHealthFacilities() {
        List<HealthFacilityDTO> healthFacilityDTOS = healthFacilityService.getAll();
        if (healthFacilityDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(healthFacilityDTOS);
        }
    }

    @PostMapping
    public ResponseEntity<HealthFacilityDTO> addHealthFacilities(@RequestBody @Valid HealthFacilityDTO healthFacilityDTO) {
        HealthFacilityDTO healthFacilityDTO1 = healthFacilityService.addHealthFacility(healthFacilityDTO);
        return ResponseEntity.ok(healthFacilityDTO1);
    }

    @Operation(summary = "Gets health facility by ID", description = "Get health facility based on it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = HealthFacilityDTO.class))),
            @ApiResponse(responseCode = "404", description = "Health facility cannot be found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<HealthFacilityDTO> getHealthFacility(@PathVariable Long id) {
        HealthFacilityDTO dto = healthFacilityService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthFacilityDTO> updateHealthFacility(@PathVariable Long id, @RequestBody @Valid HealthFacilityDTO healthFacilityDTO) {
        HealthFacilityDTO dto = healthFacilityService.updateHealthFacility(id, healthFacilityDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHealthFacility(@PathVariable Long id) {
        healthFacilityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

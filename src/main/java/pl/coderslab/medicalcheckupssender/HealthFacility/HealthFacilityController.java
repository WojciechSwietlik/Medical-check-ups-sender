package pl.coderslab.medicalcheckupssender.HealthFacility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/healthFacility")
public class HealthFacilityController {
    private final HealthFacilityService healthFacilityService;

    public HealthFacilityController(HealthFacilityService healthFacilityService) {
        this.healthFacilityService = healthFacilityService;
    }

    @GetMapping
    public ResponseEntity<List<HealthFacilityDto>> getHealthFacilities() {
        List<HealthFacilityDto> healthFacility = healthFacilityService.getAll();
        if (healthFacility.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(healthFacility);
        }
    }

    @PostMapping
    public ResponseEntity<HealthFacilityDto> addHealthFacility(@RequestBody @Valid HealthFacilityDto healthFacility) {
        HealthFacilityDto dto = healthFacilityService.addHealthFacility(healthFacility);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthFacilityDto> getHealthFacility(@PathVariable Long id) {
        HealthFacilityDto dto = healthFacilityService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthFacilityDto> updateHealthFacility(@PathVariable Long id, @RequestBody @Valid HealthFacilityDto healthFacility) {
        HealthFacilityDto dto = healthFacilityService.updateHealthFacility(id, healthFacility);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHealthFacility(@PathVariable Long id) {
        healthFacilityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
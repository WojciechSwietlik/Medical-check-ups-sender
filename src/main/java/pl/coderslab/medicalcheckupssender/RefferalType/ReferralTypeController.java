package pl.coderslab.medicalcheckupssender.RefferalType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class ReferralTypeController {

    private final ReferralTypeService referralTypeService;

    public ReferralTypeController(ReferralTypeService referralTypeService) {
        this.referralTypeService = referralTypeService;
    }

    @Operation(summary = "Gets refferal type", description = "Gets list of all refferal type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = ReferralTypeDTO[].class))),
            @ApiResponse(responseCode = "404", description = "Referral cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<ReferralTypeDTO>> getReferralType() {
        List<ReferralTypeDTO> referralTypeDTOS = referralTypeService.getAll();
        if (referralTypeDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(referralTypeDTOS);
        }
    }

    @PostMapping
    public ResponseEntity<ReferralTypeDTO> addReferralType(@RequestBody @Valid ReferralTypeDTO referralTypeDTO) {
        ReferralTypeDTO referral = referralTypeService.addRefferalType(referralTypeDTO);
        return ResponseEntity.ok(referral);
    }

    @Operation(summary = "Gets referral by ID", description = "Get referral on it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = ReferralTypeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Refferal type cannot be found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReferralTypeDTO> getReferraltype(@PathVariable Long id) {
        ReferralTypeDTO dto = referralTypeService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReferralTypeDTO> updateReferralType(@PathVariable Long id, @RequestBody @Valid ReferralTypeDTO referralTypeDTO) {
        ReferralTypeDTO dto = referralTypeService.updateRefferalType(id, referralTypeDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReferralTYpe(@PathVariable Long id) {
        referralTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

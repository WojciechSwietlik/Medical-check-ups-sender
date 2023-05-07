package pl.coderslab.medicalcheckupssender.RefferalType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.medicalcheckupssender.EmployeeAddress.EmployeeAddressDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/referral")
public class ReferralTypeController {
    private final ReferralTypeService referralTypeService;

    public ReferralTypeController(ReferralTypeService referralTypeService) {
        this.referralTypeService = referralTypeService;
    }

    @Operation(summary = "Add new referral type", description = "Fill data about referral type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Datas are incomplited")
    })
    @PostMapping
    public ResponseEntity<ReferralTypeDto> addReferralType(@RequestBody @Valid ReferralTypeDto refferal) {
        ReferralTypeDto dto = referralTypeService.addReferralType(refferal);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Gets list all referral type", description = "Gets list of all referral type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Referral types cannot be found")
    })
    @GetMapping
    public ResponseEntity<List<ReferralTypeDto>> getReferralType() {
        List<ReferralTypeDto> referralType = referralTypeService.getAll();
        if (referralType.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(referralType);
        }
    }

    @Operation(summary = "Gets referral type by id", description = "Gets referral type by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Referral type cannot be found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<ReferralTypeDto> getRefferalType(@PathVariable Long id) {
        ReferralTypeDto dto = referralTypeService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Update referral type by id", description = "Update referral type by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Referral type cannot be found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReferralTypeDto> updateReferralType(@PathVariable Long id, @RequestBody @Valid ReferralTypeDto referralType) {
        ReferralTypeDto dto = referralTypeService.updateRefferalType(id, referralType);
        return ResponseEntity.ok(dto);
    }
    @Operation(summary = "Delete referral type by id", description = "Delete referral type by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = EmployeeAddressDto[].class))),
            @ApiResponse(responseCode = "404", description = "Referral type cannot be found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReferralType(@PathVariable Long id) {
        referralTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package pl.coderslab.medicalcheckupssender.RefferalType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/referralTypes")
public class ReferralTypeController {
    private final ReferralTypeService referralTypeService;

    public ReferralTypeController(ReferralTypeService referralTypeService) {
        this.referralTypeService = referralTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ReferralTypeDto>> getReferralType() {
        List<ReferralTypeDto> referralType = referralTypeService.getAll();
        if (referralType.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(referralType);
        }
    }

    @PostMapping
    public ResponseEntity<ReferralTypeDto> addReferralType(@RequestBody @Valid ReferralTypeDto referralType) {
        ReferralTypeDto dto = referralTypeService.addReferralType(referralType);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferralTypeDto> getRefferalType(@PathVariable Long id) {
        ReferralTypeDto dto = referralTypeService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReferralTypeDto> updateReferralType(@PathVariable Long id, @RequestBody @Valid ReferralTypeDto referralType) {
        ReferralTypeDto dto = referralTypeService.updateRefferalType(id, referralType);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReferralType(@PathVariable Long id) {
        referralTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
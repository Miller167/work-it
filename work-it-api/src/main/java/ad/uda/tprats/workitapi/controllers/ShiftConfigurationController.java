package ad.uda.tprats.workitapi.controllers;

import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.services.ShiftConfigurationService;
import ad.uda.tprats.workitdata.entities.ShiftConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shiftConfigurations")
public class ShiftConfigurationController {

    @Autowired
    private ShiftConfigurationService shiftConfigurationService;

    @GetMapping()
    public List<ShiftConfiguration> getAllShiftConfigurations() {
        try {
            return shiftConfigurationService.getShiftConfigurations();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{shiftConfigurationId}")
    public ShiftConfiguration getShiftConfigurationById(@PathVariable Long shiftConfigurationId) {
        try {
            return shiftConfigurationService.getShiftConfigurationById(shiftConfigurationId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public ShiftConfiguration createShiftConfiguration(@RequestBody ShiftConfiguration shiftConfiguration) {
        try {
            return shiftConfigurationService.createShiftConfiguration(shiftConfiguration);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{shiftConfigurationId}")
    public ResponseEntity<ShiftConfiguration> updateShiftConfiguration(@PathVariable Long shiftConfigurationId, @RequestBody ShiftConfiguration shiftConfiguration) {
        try {
            return ResponseEntity.ok(shiftConfigurationService.updateShiftConfiguration(shiftConfigurationId, shiftConfiguration));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{shiftConfigurationId}")
    public ResponseEntity<?> deleteShiftConfiguration(@PathVariable Long shiftConfigurationId) {
        try {
            ShiftConfiguration shiftConfiguration = shiftConfigurationService.getShiftConfigurationById(shiftConfigurationId);
            if (shiftConfiguration == null) {
                throw new CustomErrorException("ShiftConfiguration does not exist");
            }
            shiftConfigurationService.deleteShiftConfiguration(shiftConfigurationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}

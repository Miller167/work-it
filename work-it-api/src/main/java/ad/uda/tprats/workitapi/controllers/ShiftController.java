package ad.uda.tprats.workitapi.controllers;

import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.services.ShiftService;
import ad.uda.tprats.workitdata.entities.Shift;
import ad.uda.tprats.workitdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Shift> getAllShifts() {
        try {
            return shiftService.getShifts();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{shiftId}")
    public Shift getShiftById(@PathVariable Long shiftId) {
        try {
            return shiftService.getShiftById(shiftId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public Shift createShift(@RequestBody Shift shift) {
        try {
            return shiftService.createShift(shift);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{shiftId}")
    public ResponseEntity<Shift> updateShift(@PathVariable Long shiftId, @RequestBody Shift shift) {
        try {
            return ResponseEntity.ok(shiftService.updateShift(shiftId, shift));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{shiftId}")
    public ResponseEntity<?> deleteShift(@PathVariable Long shiftId) {
        try {
            Shift shift = shiftService.getShiftById(shiftId);
            if (shift == null) {
                throw new CustomErrorException("Shift does not exist");
            }
            shiftService.deleteShift(shiftId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

   /* @GetMapping("/request/{userId}")
    public String requestShift(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new CustomErrorException("User does not exist");
            }
            return shiftService.requestShift(user);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }*/

    @GetMapping("/request/{userId}")
    public ResponseEntity<?> request(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new CustomErrorException("User does not exist");
            }
            shiftService.request(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }
}

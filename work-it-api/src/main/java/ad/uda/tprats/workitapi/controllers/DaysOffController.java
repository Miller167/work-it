package ad.uda.tprats.workitapi.controllers;

import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.services.DaysOffService;
import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.entities.DaysOff;
import ad.uda.tprats.workitdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daysoff")
public class DaysOffController {

    @Autowired
    private DaysOffService daysOffService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<DaysOff> getAllDaysOff() {
        try {
            return daysOffService.getDaysOff();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/userId/{userId}")
    public List<DaysOff> getDaysOffByUser(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new CustomErrorException("User does not exist");
            }
            return daysOffService.getDaysOffByUser(user);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{daysOffId}")
    public DaysOff getDaysOffById(@PathVariable Long daysOffId) {
        try {
            return daysOffService.getDaysOffById(daysOffId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public DaysOff createDaysOff(@RequestBody DaysOff daysOff) {
        try {
            return daysOffService.createDaysOff(daysOff);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{daysOffId}")
    public ResponseEntity<DaysOff> updateDaysOff(@PathVariable Long daysOffId, @RequestBody DaysOff daysOff) {
        try {
            return ResponseEntity.ok(daysOffService.updateDaysOff(daysOffId, daysOff));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{daysOffId}")
    public ResponseEntity<?> deleteDaysOff(@PathVariable Long daysOffId) {
        try {
            DaysOff daysOff = daysOffService.getDaysOffById(daysOffId);
            if (daysOff == null) {
                throw new CustomErrorException("DaysOff does not exist");
            }
            daysOffService.deleteDaysOff(daysOffId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}

package ad.uda.tprats.workit.workitapi.controllers;

import ad.uda.tprats.workit.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.entities.WorkPeriod;
import ad.uda.tprats.workitdata.services.WorkPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workPeriods")
public class WorkPeriodController {

    @Autowired
    private WorkPeriodService workPeriodService;

    @GetMapping()
    public List<WorkPeriod> getAllWorkPeriods() {
        try {
            return workPeriodService.getWorkPeriods();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{workPeriodId}")
    public WorkPeriod getWorkPeriodById(@PathVariable Long workPeriodId) {
        try {
            return workPeriodService.getWorkPeriodById(workPeriodId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public WorkPeriod createWorkPeriod(@RequestBody WorkPeriod workPeriod) {
        try {
            return workPeriodService.createWorkPeriod(workPeriod);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{workPeriodId}")
    public ResponseEntity<WorkPeriod> updateWorkPeriod(@PathVariable Long workPeriodId, @RequestBody WorkPeriod workPeriod) {
        try {
            return ResponseEntity.ok(workPeriodService.updateWorkPeriod(workPeriodId, workPeriod));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{workPeriodId}")
    public ResponseEntity<?> deleteWorkPeriod(@PathVariable Long workPeriodId) {
        try {
            WorkPeriod workPeriod = workPeriodService.getWorkPeriodById(workPeriodId);
            if (workPeriod == null) {
                throw new CustomErrorException("WorkPeriod does not exist");
            }
            workPeriodService.deleteWorkPeriod(workPeriodId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}

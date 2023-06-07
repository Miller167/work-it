package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.WorkPeriod;
import ad.uda.tprats.workitdata.repositories.WorkPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPeriodService {

    @Autowired
    private WorkPeriodRepository workPeriodRepository;

    // CREATE
    public WorkPeriod createWorkPeriod(WorkPeriod workPeriod) {
        return workPeriodRepository.save(workPeriod);
    }

    // READ
    public List<WorkPeriod> getWorkPeriods() {
        return workPeriodRepository.findAll();
    }

    // READ
    public WorkPeriod getWorkPeriodById(Long workPeriodId) {
        return workPeriodRepository.getById(workPeriodId);
    }

    // DELETE
    public void deleteWorkPeriod(Long workPeriodId) {
        workPeriodRepository.deleteById(workPeriodId);
    }

    // UPDATE
    public WorkPeriod updateWorkPeriod(Long workPeriodId, WorkPeriod details) {
        WorkPeriod workPeriod = workPeriodRepository.findById(workPeriodId).get();
        workPeriod.setEndDatetime(details.getEndDatetime());
        workPeriod.setStartDatetime(details.getStartDatetime());
        workPeriod.setArea(details.getArea());
        workPeriod.setShift(details.getShift());
        return workPeriodRepository.save(workPeriod);
    }
}

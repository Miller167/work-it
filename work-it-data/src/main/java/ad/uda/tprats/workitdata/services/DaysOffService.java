package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.DaysOff;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.repositories.DaysOffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaysOffService {

    @Autowired
    private DaysOffRepository daysOffRepository;

    // CREATE
    public DaysOff createDaysOff(DaysOff daysOff) {
        return daysOffRepository.save(daysOff);
    }

    // READ
    public List<DaysOff> getDaysOff() {
        return daysOffRepository.findAll();
    }

    // READ
    public DaysOff getDaysOffById(Long daysOffId) {
        return daysOffRepository.getById(daysOffId);
    }

    public List<DaysOff> getDaysOffByUser(User user) {
        return daysOffRepository.getDaysOffsByUser(user);
    }

    // DELETE
    public void deleteDaysOff(Long daysOffId) {
        daysOffRepository.deleteById(daysOffId);
    }

    // UPDATE
    public DaysOff updateDaysOff(Long daysOffId, DaysOff details) {
        DaysOff daysOff = daysOffRepository.findById(daysOffId).get();
        daysOff.setStartDatetime(details.getStartDatetime());
        daysOff.setEndDatetime(details.getEndDatetime());
        daysOff.setConcept(details.getConcept());
        daysOff.setTotalDays(details.getTotalDays());
        return daysOffRepository.save(daysOff);
    }
}

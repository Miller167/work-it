package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.ShiftConfiguration;
import ad.uda.tprats.workitdata.repositories.ShiftConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftConfigurationService {

    @Autowired
    private ShiftConfigurationRepository shiftConfigurationRepository;

    // CREATE
    public ShiftConfiguration createShiftConfiguration(ShiftConfiguration shiftConfiguration) {
        return shiftConfigurationRepository.save(shiftConfiguration);
    }

    // READ
    public List<ShiftConfiguration> getShiftConfigurations() {
        return shiftConfigurationRepository.findAll();
    }

    // READ
    public ShiftConfiguration getShiftConfigurationById(Long shiftConfigurationId) {
        return shiftConfigurationRepository.getById(shiftConfigurationId);
    }

    // DELETE
    public void deleteShiftConfiguration(Long shiftConfigurationId) {
        shiftConfigurationRepository.deleteById(shiftConfigurationId);
    }

    // UPDATE
    public ShiftConfiguration updateShiftConfiguration(Long shiftConfigurationId, ShiftConfiguration details) {
        ShiftConfiguration shiftConfiguration = shiftConfigurationRepository.findById(shiftConfigurationId).get();
        shiftConfiguration.setStartDatetime(details.getStartDatetime());
        shiftConfiguration.setEndDatetime(details.getEndDatetime());
        shiftConfiguration.setUsers(details.getUsers());
        return shiftConfigurationRepository.save(shiftConfiguration);
    }
}

package ad.uda.tprats.backend.services;

import ad.uda.tprats.backend.filter.FilterShift;
import ad.uda.tprats.backend.repositories.ShiftWebRepository;
import ad.uda.tprats.backend.repositories.ShiftWebRepository;
import ad.uda.tprats.workitdata.entities.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class ShiftWebService {

	@Autowired
    ShiftWebRepository shiftWebRepository;

	public List<Shift> findAll() {
		return shiftWebRepository.findAll();
	}

    public Shift findById(Long id) {
        return shiftWebRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found: " + id.toString()));
    }
    
    public void saveShift(Shift shift) {
    	
    	Long shiftId = shift.getId();
    	
    	if(shiftId == null || shiftId == 0L) {
    		//GET NEW ID
    		//Long maxId = shiftRepository.getMaxId();
    		//long newId = (maxId == null || maxId == 0) ? 1: maxId+1;
    		//shift.setId(newId);
    	}

        shiftWebRepository.save(shift);
    }
    
    public DataTablesOutput<Shift> findAll(@Valid DataTablesInput input){

        return shiftWebRepository.findAll(input, new FilterShift(input));
    }

    /*public DataTablesOutput<Shift> findAllByDate(@Valid DataTablesInput input) {

        return shiftWebRepository.getShiftsByStartDatetimeDateEquals(input);
    }*/
    
    public boolean deleteShift(Long id) {

        Shift shift = findById(id);
        if (shift != null) {
            shiftWebRepository.delete(shift);
            return true;
        }
        return false;
    }
}

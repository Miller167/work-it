package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.WorkPeriod;
import ad.uda.tprats.workitdata.repositories.WorkPeriodRepository;
import ad.uda.tprats.workitdata.entities.Shift;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.repositories.ShiftRepository;
import ad.uda.tprats.workitdata.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.*;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkPeriodRepository workPeriodRepository;


    // CREATE
    public Shift createShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    // READ
    public List<Shift> getShifts() {
        return shiftRepository.findAll();
    }

    // READ
    public Shift getShiftById(Long shiftId) {
        return shiftRepository.getById(shiftId);
    }

    // DELETE
    public void deleteShift(Long shiftId) {
        shiftRepository.deleteById(shiftId);
    }

    // UPDATE
    public Shift updateShift(Long shiftId, Shift details) {
        Shift shift = shiftRepository.findById(shiftId).get();
        shift.setStartDatetime(details.getStartDatetime());
        shift.setEndDatetime(details.getEndDatetime());
        shift.setWorkPeriods(details.getWorkPeriods());
        return shiftRepository.save(shift);
    }

    public String requestShift(User user) {
        // Get Shifts under user
        List<Shift> userShifts = shiftRepository.findShiftByUser(user);

        // Create day timestamp
        Date today = new Date();

        DateTime dt = new DateTime();
        LocalDate d = dt.toLocalDate();


        // Variable where shift with today's date will be saved
        Shift todaysShift = null;

        // For all shifts from user
        for(Shift shift : userShifts){
            DateTime shiftStart = new DateTime(shift.getStartDatetime());
            if(d.equals(shiftStart.toLocalDate())){
                todaysShift = shift; // If they do, shift is saved
                break;
            }
        }

        // Generate code and expiration date
        String generatedString = RandomStringUtils.random(16, true, true);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(today);
        calendar.add(Calendar.MINUTE, 5);
        Date expirationDate = calendar.getTime();


        if(todaysShift == null){// If there is no saved shift
            WorkPeriod workPeriod = new WorkPeriod();
            workPeriod.setRequestCode(generatedString);
            workPeriod.setCodeExpirationDate(expirationDate);


            Shift shift = new Shift();
            shift.setUser(user);
            workPeriod.setShift(shift);

            List<WorkPeriod> workPeriodList = new ArrayList<>();
            workPeriodList.add(workPeriod);
            shift.setWorkPeriods(workPeriodList);
            List<Shift> shiftList = new ArrayList<>();
            shiftList.add(shift);
            user.setShifts(shiftList);

            shiftRepository.save(shift);
            workPeriodRepository.save(workPeriod);

        }else {
            List<WorkPeriod> periods = todaysShift.getWorkPeriods();
            if(periods.isEmpty() || (periods.get(periods.size()- 1).getEndDatetime() == null)){ //if there are no work periods for today or all of them are finished
                WorkPeriod workPeriod = new WorkPeriod();
                workPeriod.setRequestCode(generatedString);
                workPeriod.setCodeExpirationDate(expirationDate);

                todaysShift.getWorkPeriods().add(workPeriod);
                shiftRepository.save(todaysShift);
                workPeriodRepository.save(workPeriod);

            }else{//if there are work periods and one left to end
                WorkPeriod workPeriod = periods.get(periods.size()- 1);
                workPeriod.setRequestCode(generatedString);
                workPeriod.setCodeExpirationDate(expirationDate);
                workPeriodRepository.save(workPeriod);
            }
        }
        userRepository.save(user);
        return generatedString;
    }

    public void request(User user) {
        // Get Shifts under user
        List<Shift> userShifts = shiftRepository.findShiftByUser(user);

        // Create day timestamp
        Date today = new Date();

        DateTime dt = new DateTime();
        LocalDate d = dt.toLocalDate();


        // Variable where shift with today's date will be saved
        Shift todaysShift = null;

        // For all shifts from user
        for(Shift shift : userShifts){
            DateTime shiftStart = new DateTime(shift.getStartDatetime());
            if(d.equals(shiftStart.toLocalDate())){
                todaysShift = shift; // If they do, shift is saved
                break;
            }
        }

        /*// Generate code and expiration date
        String generatedString = RandomStringUtils.random(16, true, true);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(today);
        calendar.add(Calendar.MINUTE, 5);
        Date expirationDate = calendar.getTime();*/


        if(todaysShift == null){// If there is no saved shift
            WorkPeriod workPeriod = new WorkPeriod();
            /*workPeriod.setRequestCode(generatedString);
            workPeriod.setCodeExpirationDate(expirationDate);*/


            Shift shift = new Shift();
            shift.setUser(user);
            workPeriod.setShift(shift);

            List<WorkPeriod> workPeriodList = new ArrayList<>();
            workPeriodList.add(workPeriod);
            shift.setWorkPeriods(workPeriodList);
            List<Shift> shiftList = new ArrayList<>();
            shiftList.add(shift);
            user.setShifts(shiftList);

            shiftRepository.save(shift);
            workPeriodRepository.save(workPeriod);

        }else {
            List<WorkPeriod> periods = todaysShift.getWorkPeriods();
            if(periods.isEmpty() || (periods.get(periods.size()- 1).getEndDatetime() == null)){ //if there are no work periods for today or all of them are finished
                WorkPeriod workPeriod = new WorkPeriod();
                /*workPeriod.setRequestCode(generatedString);
                workPeriod.setCodeExpirationDate(expirationDate);*/

                todaysShift.getWorkPeriods().add(workPeriod);
                shiftRepository.save(todaysShift);
                workPeriodRepository.save(workPeriod);

            }else{//if there are work periods and one left to end
                WorkPeriod workPeriod = periods.get(periods.size()- 1);
                /*workPeriod.setRequestCode(generatedString);
                workPeriod.setCodeExpirationDate(expirationDate);*/
                workPeriodRepository.save(workPeriod);
            }
        }
        userRepository.save(user);
    }
}

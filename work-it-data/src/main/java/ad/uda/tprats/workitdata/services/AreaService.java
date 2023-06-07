package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.Area;
import ad.uda.tprats.workitdata.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    // CREATE
    public Area createArea(Area area) {
        return areaRepository.save(area);
    }

    // READ
    public List<Area> getAreas() {
        return areaRepository.findAll();
    }

    // READ
    public Area getAreaById(Long areaId) {
        return areaRepository.getById(areaId);
    }

    // DELETE
    public void deleteArea(Long areaId) {
        areaRepository.deleteById(areaId);
    }

    // UPDATE
    public Area updateArea(Long areaId, Area details) {
        Area area = areaRepository.findById(areaId).get();
        area.setTitle(details.getTitle());
        area.setDescription(details.getDescription());
        area.setTerminals(details.getTerminals());
        area.setWorkPeriods(details.getWorkPeriods());
        return areaRepository.save(area);
    }
}

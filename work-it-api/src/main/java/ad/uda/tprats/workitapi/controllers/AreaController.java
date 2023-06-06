package ad.uda.tprats.workit.workitapi.controllers;

import ad.uda.tprats.workitdata.entities.Area;
import ad.uda.tprats.workitdata.services.AreaService;
import ad.uda.tprats.workit.workitapi.helpers.CustomErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping()
    public List<Area> getAllAreas() {
        try {
            return areaService.getAreas();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{areaId}")
    public Area getAreaById(@PathVariable Long areaId) {
        try {
            return areaService.getAreaById(areaId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public Area createArea(@RequestBody Area area) {
        try {
            return areaService.createArea(area);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{areaId}")
    public ResponseEntity<Area> updateArea(@PathVariable Long areaId, @RequestBody Area area) {
        try {
            return ResponseEntity.ok(areaService.updateArea(areaId, area));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{areaId}")
    public ResponseEntity<?> deleteArea(@PathVariable Long areaId) {
        try {
            Area area = areaService.getAreaById(areaId);
            if (area == null) {
                throw new CustomErrorException("User does not exist");
            }
            areaService.deleteArea(areaId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}

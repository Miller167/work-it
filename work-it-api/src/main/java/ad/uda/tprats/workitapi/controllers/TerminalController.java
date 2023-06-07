package ad.uda.tprats.workitapi.controllers;

import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.entities.Terminal;
import ad.uda.tprats.workitdata.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terminals")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @GetMapping()
    public List<Terminal> getAllTerminals() {
        try {
            return terminalService.getTerminals();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{terminalId}")
    public Terminal getTerminalById(@PathVariable Long terminalId) {
        try {
            return terminalService.getTerminalById(terminalId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public Terminal createTerminal(@RequestBody Terminal terminal) {
        try {
            return terminalService.createTerminal(terminal);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{terminalId}")
    public ResponseEntity<Terminal> updateTerminal(@PathVariable Long terminalId, @RequestBody Terminal terminal) {
        try {
            return ResponseEntity.ok(terminalService.updateTerminal(terminalId, terminal));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{terminalId}")
    public ResponseEntity<?> deleteTerminal(@PathVariable Long terminalId) {
        try {
            Terminal terminal = terminalService.getTerminalById(terminalId);
            if (terminal == null) {
                throw new CustomErrorException("Terminal does not exist");
            }
            terminalService.deleteTerminal(terminalId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}

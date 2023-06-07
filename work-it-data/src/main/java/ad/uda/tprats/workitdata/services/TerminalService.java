package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.Terminal;
import ad.uda.tprats.workitdata.repositories.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;

    // CREATE
    public Terminal createTerminal(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    // READ
    public List<Terminal> getTerminals() {
        return terminalRepository.findAll();
    }

    // READ
    public Terminal getTerminalById(Long terminalId) {
        return terminalRepository.getById(terminalId);
    }

    // DELETE
    public void deleteTerminal(Long terminalId) {
        terminalRepository.deleteById(terminalId);
    }

    // UPDATE
    public Terminal updateTerminal(Long terminalId, Terminal details) {
        Terminal terminal = terminalRepository.findById(terminalId).get();
        terminal.setActive(details.isActive());
        terminal.setArea(details.getArea());
        terminal.setName(terminal.getName());
        return terminalRepository.save(terminal);
    }
}

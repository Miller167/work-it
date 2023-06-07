package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> { }

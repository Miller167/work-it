package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> { }

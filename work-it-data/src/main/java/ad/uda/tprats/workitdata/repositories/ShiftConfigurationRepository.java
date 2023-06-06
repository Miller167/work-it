package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.ShiftConfiguration;
import ad.uda.tprats.workitdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftConfigurationRepository extends JpaRepository<ShiftConfiguration, Long> { }

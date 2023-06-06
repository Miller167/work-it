package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "area")
@Data
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull(message = "Title is mandatory")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    private List<Terminal> terminals;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("area")
    private List<WorkPeriod> workPeriods;
}

package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shift")
@Data
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_datetime")
    private Date startDatetime;

    @Column(name = "end_datetime")
    private Date endDatetime;

    @OneToMany(mappedBy = "shift", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("shift")
    private List<WorkPeriod> workPeriods;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("shifts")
    //@NotNull(message = "User is mandatory")
    private User user;

}

package ad.uda.tprats.workitdata.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shift_configuration")
@Data
public class ShiftConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_datetime")
    @NotNull(message = "Start time is mandatory")
    private Date startDatetime;

    @Column(name = "end_datetime")
    @NotNull(message = "End time is mandatory")
    private Date endDatetime;

    @OneToMany(mappedBy = "shiftConfiguration", fetch = FetchType.LAZY)
    private List<User> users;

}

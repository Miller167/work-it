package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "days_off")
@Data
public class DaysOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_datetime")
    private Date startDatetime;

    @Column(name = "end_datetime")
    private Date endDatetime;

    @Column(name = "concept")
    private String concept;

    @Column(name = "total_days")
    private int totalDays;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is mandatory")
    @JsonIgnoreProperties("daysOffDone")
    private User user;
}

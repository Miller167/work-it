package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "work_period")
@Data
public class WorkPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_datetime")
    private Date startDatetime;

    @Column(name = "end_datetime")
    private Date endDatetime;

    @Column(name = "request_code")
    private String requestCode;

    @Column(name = "code_expiration_date")
    private Date codeExpirationDate;

    @ManyToOne
    @JoinColumn(name = "area_id")
    @JsonIgnoreProperties("workPeriods")
    //@NotNull(message = "Area is mandatory")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    @JsonIgnoreProperties("workPeriods")
    //@NotNull(message = "Shift is mandatory")
    private Shift shift;
}

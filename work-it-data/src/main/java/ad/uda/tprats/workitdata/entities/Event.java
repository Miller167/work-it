package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull(message = "Title is mandatory")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_datetime")
    @NotNull(message = "Start time is mandatory")
    private Date startDatetime;

    @Column(name = "end_datetime")
    private Date endDatetime;

    @Column(name = "all_day")
    private boolean allDay;

    @ManyToOne
    @JoinColumn(name = "project_id")
    //@NotNull(message = "Project is mandatory")  // should it be mandatory???
    private Project project;

    //@JsonIgnoreProperties("createdEvents")
    //@NotNull(message = "Manager is mandatory")
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

   /* @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDatetime=" + startDatetime +
                ", endDatetime=" + endDatetime +
                ", allDay=" + allDay +
                ", project=" + project +
                ", user=" + user +
                '}';
    }*/
}

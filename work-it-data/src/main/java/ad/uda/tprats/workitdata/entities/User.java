package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user")
@Data
@ToString
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "uid")
    private String uid;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "administrator")
    private boolean administrator;

    @Column(name = "total_days_off")
    private double totalDaysOff;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    @JsonIgnore
    //@JsonIgnoreProperties("manager")
    private List<Project> managedProjects;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("user")
    @JsonIgnore
    private List<Event> createdEvents;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<Shift> shifts;

    @ManyToMany(mappedBy = "collaborators", fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("collaborators")
    @JsonIgnore
    private List<Project> collaboratedProjects;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<Task> createdTasks;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<Todo> createdTodos;

    @ManyToOne
    @JoinColumn(name = "shift_configuration_id")
    //@NotNull(message = "Shift Configuration is mandatory")
    private ShiftConfiguration shiftConfiguration;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<DaysOff> daysOffDone;
}

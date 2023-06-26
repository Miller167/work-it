package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "project")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull(message = "Title is mandatory")
    private String title;

    @Column(name = "description")
    @NotNull(message = "Description is mandatory")
    private String description;

    @Column(name = "estimated_hours")
    private double estimatedHours;

    @Column(name = "total_hours")
    private double totalHours;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    //@JsonIgnoreProperties("managedProjects")
    @JsonIgnore
    //@NotNull(message = "Manager is mandatory")
    private User manager;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    //@JsonIgnoreProperties("collaboratedProjects")
    @JsonIgnore
    private List<User> collaborators;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

}

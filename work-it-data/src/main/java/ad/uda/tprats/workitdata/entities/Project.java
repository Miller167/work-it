package ad.uda.tprats.workitdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "project")
@Data
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
    @JsonIgnoreProperties("managedProjects")
    //@NotNull(message = "Manager is mandatory")
    private User manager;

    //    @ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> collaborators;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

}

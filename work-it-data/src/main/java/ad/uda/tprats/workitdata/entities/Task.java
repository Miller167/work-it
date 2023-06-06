package ad.uda.tprats.workitdata.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull(message = "Title is mandatory")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "estimated_hours")
    private double estimatedHours;

    @Column(name = "total_hours")
    private double totalHours;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "creation_datetime")
    @CreationTimestamp
    private Date creationDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Creator is mandatory")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @NotNull(message = "Project is mandatory")
    private Project project;

}

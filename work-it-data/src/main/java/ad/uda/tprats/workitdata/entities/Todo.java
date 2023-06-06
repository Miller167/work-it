package ad.uda.tprats.workitdata.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "todo")
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull(message = "Title is mandatory")
    private String title;

    @Column(name = "is_checked")
    @NotNull(message = "isChecked is mandatory")
    private boolean isChecked;

    @Column(name = "creation_datetime")
    //@NotNull(message = "Creation time is mandatory")
    @CreationTimestamp
    private Date creationDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    //@NotNull(message = "User is mandatory")
    private User user;

}

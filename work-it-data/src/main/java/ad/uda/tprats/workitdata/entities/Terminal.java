package ad.uda.tprats.workitdata.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "terminal")
@Data
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull(message = "Name is mandatory")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "area_id")
    @NotNull(message = "Area is mandatory")
    private Area area;

}

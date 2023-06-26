package ad.uda.tprats.workitdata.dtos;

import ad.uda.tprats.workitdata.entities.Project;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Data
public class EventDTO {

	@Getter@Setter
	private long user;
	@Getter@Setter
	private String title;
	@Getter@Setter
	private String description;
	@Getter@Setter
	private boolean allDay;
	@Getter@Setter
	private Date startDatetime;
	@Getter@Setter
	private Date endDatetime;
	@Getter@Setter
	private Project project;
}

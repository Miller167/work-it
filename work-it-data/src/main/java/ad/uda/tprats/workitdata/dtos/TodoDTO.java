package ad.uda.tprats.workitdata.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Data
public class TodoDTO {

	@Getter@Setter
	private long user;
	@Getter@Setter
	private String title;
	@Getter@Setter
	private boolean checked;
	@Getter@Setter
	@CreationTimestamp
	private Date creationDateTime;
}

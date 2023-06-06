package ad.uda.tprats.workit.workitapi.helpers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;


@Data
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp;
    private String error;

    public ErrorResponse(String error) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.error = error;
    }
}

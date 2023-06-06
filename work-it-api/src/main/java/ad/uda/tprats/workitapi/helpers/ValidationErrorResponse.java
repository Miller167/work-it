package ad.uda.tprats.workit.workitapi.helpers;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationErrorResponse {

    private Map<String, String> status;
    private Map<String, String> data;

    public ValidationErrorResponse(Map<String, String> errors, Map<String, String> status) {
        this.status = status;
        this.data = errors;
    }
}

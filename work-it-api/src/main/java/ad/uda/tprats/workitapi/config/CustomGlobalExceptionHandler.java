package ad.uda.tprats.workitapi.config;

import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitapi.helpers.ErrorResponse;
import ad.uda.tprats.workitapi.helpers.ValidationErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@ControllerAdvice
public class CustomGlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    // https://mkyong.com/spring-boot/spring-rest-error-handling-example/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        Map<String, String> statusMap = new HashMap<>();

        statusMap.put ("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        statusMap.put ("message", HttpStatus.BAD_REQUEST.getReasonPhrase());

        Field[] clsFields = ex.getParameter().getParameter().getType().getDeclaredFields();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = getJsonProperty(clsFields, ((FieldError) error));
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ValidationErrorResponse(errors, statusMap), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ErrorResponse> customHandleBadRequest(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> customHandleInternalServerError(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method to get the original field name passed as the value of the @JsonProperty. Returns the field name in the class in case no JsonProperty value was given.
     *
     * @param clsFields, all the declared class fields
     * @param e, the error.
     * @return
     */
    private String getJsonProperty(Field[] clsFields, FieldError e) {

        JsonProperty[] alisas = getAnnotationsForField(clsFields, e.getField());
        if (alisas == null || alisas.length == 0) {
            return e.getField();
        }

        String values = alisas[0].value();
        if (values.isEmpty()) {
            return e.getField();
        }

        return values;
    }

    /**
     * Methot to get the annotation of a field.
     *
     * @param clsFields, all the declared class fields.
     * @param fieldName, the name of the field to get the annotation from.
     * @return
     */
    private JsonProperty[] getAnnotationsForField(Field[] clsFields, String fieldName) {
        Optional<Field> first = Stream.of(clsFields).filter(f -> f.getName().equals(fieldName)).findFirst();
        return first.map(field -> field.getAnnotationsByType(JsonProperty.class)).orElse(null);
    }

}

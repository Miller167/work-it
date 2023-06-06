package ad.uda.tprats.workit.workitapi.helpers;

public class CustomErrorException extends RuntimeException{
    public CustomErrorException(String message) {
        super(message);
    }

    public CustomErrorException(CustomError error) {
        super(error.getMessage());
    }

    @Override
    public String getMessage() {
        return super.getMessage() != null ? super.getMessage() : "Hi ha hagut un error";
    }

}

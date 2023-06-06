package ad.uda.tprats.workit.workitapi.helpers;

public enum CustomError {
    // Apis
    INVALID_API("L'API no és vàlida");

    private final String message;

    CustomError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;

    }
}

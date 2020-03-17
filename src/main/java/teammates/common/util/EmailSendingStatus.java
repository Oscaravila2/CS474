package teammates.common.util;

/**
 * Encapsulates the status of sending an email.
 */
public class EmailSendingStatus {

    private final int statusCode;
    private final String message;
    private final boolean isSuccess;

    public EmailSendingStatus(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.isSuccess = statusCode >= 200 && statusCode <= 299;
        assert checkSuccess(statusCode);
    }

    public static boolean checkSuccess(int statusCode){
        if(statusCode >= 200 && statusCode <= 299){
            if(this.isSuccess){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(!this.isSuccess){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

}

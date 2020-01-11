package utility;

public class CaptchaResponse {

    private boolean success;
    private String timestamp;
    private String hostName;
    private String errorCodes;

    public CaptchaResponse(boolean success, String timestamp, String hostName, String errorCodes) {
        this.success = success;
        this.timestamp = timestamp;
        this.hostName = hostName;
        this.errorCodes = errorCodes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(String errorCodes) {
        this.errorCodes = errorCodes;
    }

}

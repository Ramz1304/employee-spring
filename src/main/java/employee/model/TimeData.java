package employee.model;


import java.time.LocalDateTime;

public class TimeData {

    private String message;

    public TimeData() {
        message = "Activity time: " + LocalDateTime.now().toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
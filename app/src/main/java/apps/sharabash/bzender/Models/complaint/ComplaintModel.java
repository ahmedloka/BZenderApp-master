package apps.sharabash.bzender.Models.complaint;

import org.jetbrains.annotations.NotNull;

public class ComplaintModel {

    private String Message ;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @NotNull
    @Override
    public String toString() {
        return "ComplaintModel{" +
                "Message='" + Message + '\'' +
                '}';
    }
}

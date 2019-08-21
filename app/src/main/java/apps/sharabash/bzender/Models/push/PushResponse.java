package apps.sharabash.bzender.Models.push;

import org.jetbrains.annotations.NotNull;

public class PushResponse {

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
        return "PushResponse{" +
                "Message='" + Message + '\'' +
                '}';
    }
}

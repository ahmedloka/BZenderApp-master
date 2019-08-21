package apps.sharabash.bzender.Models;

import org.jetbrains.annotations.NotNull;

public class ForgetPasswordResponse {

    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NotNull
    @Override
    public String toString() {
        return "ForgetPasswordResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}

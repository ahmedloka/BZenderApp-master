package apps.sharabash.bzender.Models.push;

import org.jetbrains.annotations.NotNull;

public class PushNotification {

    private String Id;
    private String Token;
    private String OS;
    private String UserId;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    @NotNull
    @Override
    public String toString() {
        return "PushNotification{" +
                "Id='" + Id + '\'' +
                ", Token='" + Token + '\'' +
                ", OS='" + OS + '\'' +
                ", UserId='" + UserId + '\'' +
                '}';
    }
}

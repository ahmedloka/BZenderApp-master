package apps.sharabash.bzender.Models;

import org.jetbrains.annotations.NotNull;

class CustomMessage {
    private String message ;
    private String senderIdentityId  ;
    private String DateTime ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderIdentityId() {
        return senderIdentityId;
    }

    public void setSenderIdentityId(String senderIdentityId) {
        this.senderIdentityId = senderIdentityId;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    @NotNull
    @Override
    public String toString() {
        return "CustomMessage{" +
                "message='" + message + '\'' +
                ", senderIdentityId='" + senderIdentityId + '\'' +
                ", DateTime='" + DateTime + '\'' +
                '}';
    }
}

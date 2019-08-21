package apps.sharabash.bzender.Models.chatList;

import org.jetbrains.annotations.NotNull;

public class ChatList {
    private String ReceiverId;

    private String MessageDate;

    private String Id;

    private String SenderId;

    private String Body;

    public String getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(String ReceiverId) {
        this.ReceiverId = ReceiverId;
    }

    public String getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(String MessageDate) {
        this.MessageDate = MessageDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSenderId() {
        return SenderId;
    }

    public void setSenderId(String SenderId) {
        this.SenderId = SenderId;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String Body) {
        this.Body = Body;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [ReceiverId = " + ReceiverId + ", MessageDate = " + MessageDate + ", Id = " + Id + ", SenderId = " + SenderId + ", Body = " + Body + "]";
    }
}

package apps.sharabash.bzender.adapters;

public class ChatModel {

    private String senderId;
    private String receiverId;
    //private String img;
    private String Name;
    private String msg;

    //, String img

    public ChatModel(String senderId, String receiverId, String name, String msg) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        //this.img = img;
        Name = name;
        this.msg = msg;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

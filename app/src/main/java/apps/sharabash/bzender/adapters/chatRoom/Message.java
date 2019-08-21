package apps.sharabash.bzender.adapters.chatRoom;

class Message {

    private String text; // message body
    private String name;
    private String photo;
    private boolean belongsToCurrentUser; // is this message sent by us?
    private String receiverId;
    //private MemberData data; // data of the user that sent this message


    public Message(String text, String name, String photo, boolean belongsToCurrentUser) {
        this.text = text;
        this.name = name;
        this.photo = photo;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public Message(String text, String name, String photo, boolean belongsToCurrentUser, String receiverId) {
        this.text = text;
        this.name = name;
        this.photo = photo;
        this.belongsToCurrentUser = belongsToCurrentUser;
        this.receiverId = receiverId;
    }


    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public void setBelongsToCurrentUser(boolean belongsToCurrentUser) {
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }


}

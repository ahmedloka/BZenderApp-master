package apps.sharabash.bzender.adapters;

public class NotificationModel {

    private String desc;
    private String date;

    public NotificationModel(String desc, String date) {

        this.desc = desc;
        this.date = date;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

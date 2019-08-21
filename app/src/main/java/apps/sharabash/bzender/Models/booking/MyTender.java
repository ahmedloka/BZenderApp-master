package apps.sharabash.bzender.Models.booking;

import org.jetbrains.annotations.NotNull;

public class MyTender {
    private String startTime;
    private String endTime;
    private String tenderName;
    private String catId;
    private String id;
    private String catName;
    private int statusId;

    public MyTender(String startTime, String endTime, String tenderName, String catId, String id, String catName, int statusId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.tenderName = tenderName;
        this.catId = catId;
        this.id = id;
        this.catName = catName;
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    @NotNull
    @Override
    public String toString() {
        return "Booking{" +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", tenderName='" + tenderName + '\'' +
                ", catId='" + catId + '\'' +
                '}';
    }
}

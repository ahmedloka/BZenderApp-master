package apps.sharabash.bzender.adapters;

public class ItemBookingModel {

    private String Id;
    private String Name;
    private String startDate;
    private String EndDate;
    private String catName;
    private int statusId;


    public ItemBookingModel(String id, String name, String startDate, String endDate, String catName, int statusId) {
        Id = id;
        Name = name;
        this.startDate = startDate;
        EndDate = endDate;
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
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}

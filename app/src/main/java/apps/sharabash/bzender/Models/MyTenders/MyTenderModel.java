package apps.sharabash.bzender.Models.MyTenders;

import org.jetbrains.annotations.NotNull;

class MyTenderModel {
    private String CategoryID;

    private String TenderName;

    private String OwnerUserId;

    private String CityId;

    private String StartDateTender;

    private String ImageUrl;

    private String TenderFile;

    private String Id;

    private String TenderDescrioption;

    private String EndDateTender;

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getTenderName() {
        return TenderName;
    }

    public void setTenderName(String tenderName) {
        TenderName = tenderName;
    }

    public String getOwnerUserId() {
        return OwnerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        OwnerUserId = ownerUserId;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getStartDateTender() {
        return StartDateTender;
    }

    public void setStartDateTender(String startDateTender) {
        StartDateTender = startDateTender;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTenderFile() {
        return TenderFile;
    }

    public void setTenderFile(String tenderFile) {
        TenderFile = tenderFile;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTenderDescrioption() {
        return TenderDescrioption;
    }

    public void setTenderDescrioption(String tenderDescrioption) {
        TenderDescrioption = tenderDescrioption;
    }

    public String getEndDateTender() {
        return EndDateTender;
    }

    public void setEndDateTender(String endDateTender) {
        EndDateTender = endDateTender;
    }

    @NotNull
    @Override
    public String toString() {
        return "MyTenderModel{" +
                "CategoryID='" + CategoryID + '\'' +
                ", TenderName='" + TenderName + '\'' +
                ", OwnerUserId='" + OwnerUserId + '\'' +
                ", CityId='" + CityId + '\'' +
                ", StartDateTender='" + StartDateTender + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", TenderFile='" + TenderFile + '\'' +
                ", Id='" + Id + '\'' +
                ", TenderDescrioption='" + TenderDescrioption + '\'' +
                ", EndDateTender='" + EndDateTender + '\'' +
                '}';
    }
}

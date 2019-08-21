package apps.sharabash.bzender.Models.TendersDetails;

import org.jetbrains.annotations.NotNull;

public class TenderElectrical {

    private String Status;

    private String TenderId;

    private String OriginManufacturer;

    private String UnitType;

    private String Note;

    private String Model;

    private String Id;

    private String NumberOfUnit;

    private String YearOfManufacture;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTenderId() {
        return TenderId;
    }

    public void setTenderId(String tenderId) {
        TenderId = tenderId;
    }

    public String getOriginManufacturer() {
        return OriginManufacturer;
    }

    public void setOriginManufacturer(String originManufacturer) {
        OriginManufacturer = originManufacturer;
    }

    public String getUnitType() {
        return UnitType;
    }

    public void setUnitType(String unitType) {
        UnitType = unitType;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNumberOfUnit() {
        return NumberOfUnit;
    }

    public void setNumberOfUnit(String numberOfUnit) {
        NumberOfUnit = numberOfUnit;
    }

    public String getYearOfManufacture() {
        return YearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        YearOfManufacture = yearOfManufacture;
    }

    @NotNull
    @Override
    public String toString() {
        return "TenderElectrical{" +
                "Status='" + Status + '\'' +
                ", TenderId='" + TenderId + '\'' +
                ", OriginManufacturer='" + OriginManufacturer + '\'' +
                ", UnitType='" + UnitType + '\'' +
                ", Note='" + Note + '\'' +
                ", Model='" + Model + '\'' +
                ", Id='" + Id + '\'' +
                ", NumberOfUnit='" + NumberOfUnit + '\'' +
                ", YearOfManufacture='" + YearOfManufacture + '\'' +
                '}';
    }
}

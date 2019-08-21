package apps.sharabash.bzender.Models;

import org.jetbrains.annotations.NotNull;

public class FillDataElectrical {
    private String Status;

    private String TenderId;

    private String UnitType;

    private String Note;

    private String Model;

    private String Id;

    private String NumberOfUnit;

    private String Guarantee;

    public String getOriginManufacturer() {
        return OriginManufacturer;
    }

    public void setOriginManufacturer(String originManufacturer) {
        OriginManufacturer = originManufacturer;
    }

    private String YearOfManufacture;

    private String OriginManufacturer ;


    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getTenderId ()
    {
        return TenderId;
    }

    public void setTenderId (String TenderId)
    {
        this.TenderId = TenderId;
    }

    public String getUnitType ()
    {
        return UnitType;
    }

    public void setUnitType (String UnitType)
    {
        this.UnitType = UnitType;
    }

    public String getNote ()
    {
        return Note;
    }

    public void setNote (String Note)
    {
        this.Note = Note;
    }

    public String getModel ()
    {
        return Model;
    }

    public void setModel (String Model)
    {
        this.Model = Model;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getNumberOfUnit ()
    {
        return NumberOfUnit;
    }

    public void setNumberOfUnit (String NumberOfUnit)
    {
        this.NumberOfUnit = NumberOfUnit;
    }

    public String getGuarantee ()
    {
        return Guarantee;
    }

    public void setGuarantee (String Guarantee)
    {
        this.Guarantee = Guarantee;
    }

    public String getYearOfManufacture ()
    {
        return YearOfManufacture;
    }

    public void setYearOfManufacture (String YearOfManufacture)
    {
        this.YearOfManufacture = YearOfManufacture;
    }

    @NotNull
    @Override
    public String toString() {
        return "FillDataElectrical{" +
                "Status='" + Status + '\'' +
                ", TenderId='" + TenderId + '\'' +
                ", UnitType='" + UnitType + '\'' +
                ", Note='" + Note + '\'' +
                ", Model='" + Model + '\'' +
                ", Id='" + Id + '\'' +
                ", NumberOfUnit='" + NumberOfUnit + '\'' +
                ", Guarantee='" + Guarantee + '\'' +
                ", YearOfManufacture='" + YearOfManufacture + '\'' +
                ", OriginManufacturer='" + OriginManufacturer + '\'' +
                '}';
    }
}
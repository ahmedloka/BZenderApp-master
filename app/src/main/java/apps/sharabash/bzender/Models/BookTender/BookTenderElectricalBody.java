package apps.sharabash.bzender.Models.BookTender;

import org.jetbrains.annotations.NotNull;

class BookTenderElectricalBody {
    private String Status;

    private String TenderId;

    private String OwnerUserId;

    private String OriginManufacturer;

    private String UnitType;

    private String Note;

    private String Model;

    private String Id;

    private String NumberOfUnit;

    private String YearOfManufacture;

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

    public String getOwnerUserId ()
    {
        return OwnerUserId;
    }

    public void setOwnerUserId (String OwnerUserId)
    {
        this.OwnerUserId = OwnerUserId;
    }

    public String getOriginManufacturer ()
    {
        return OriginManufacturer;
    }

    public void setOriginManufacturer (String OriginManufacturer)
    {
        this.OriginManufacturer = OriginManufacturer;
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
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", TenderId = "+TenderId+", OwnerUserId = "+OwnerUserId+", OriginManufacturer = "+OriginManufacturer+", UnitType = "+UnitType+", Note = "+Note+", Model = "+Model+", Id = "+Id+", NumberOfUnit = "+NumberOfUnit+", YearOfManufacture = "+YearOfManufacture+"]";
    }
}


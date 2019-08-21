package apps.sharabash.bzender.Models.my_tenders;


import org.jetbrains.annotations.NotNull;

public class TenderElectrical
{
    private String Status;

    private String TenderId;

    private String OriginManufacturer;

    private String CrrentPoints;

    private String UnitType;

    private String Note;

    private String Model;

    private String Id;

    private String NumberOfUnit;

    private String YearOfManufacture;

    private String DeductPoints;

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

    public String getOriginManufacturer ()
{
    return OriginManufacturer;
}

    public void setOriginManufacturer (String OriginManufacturer)
    {
        this.OriginManufacturer = OriginManufacturer;
    }

    public String getCrrentPoints ()
{
    return CrrentPoints;
}

    public void setCrrentPoints (String CrrentPoints)
    {
        this.CrrentPoints = CrrentPoints;
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

    public String getDeductPoints ()
{
    return DeductPoints;
}

    public void setDeductPoints (String DeductPoints)
    {
        this.DeductPoints = DeductPoints;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", TenderId = "+TenderId+", OriginManufacturer = "+OriginManufacturer+", CrrentPoints = "+CrrentPoints+", UnitType = "+UnitType+", Note = "+Note+", Model = "+Model+", Id = "+Id+", NumberOfUnit = "+NumberOfUnit+", YearOfManufacture = "+YearOfManufacture+", DeductPoints = "+DeductPoints+"]";
    }
}


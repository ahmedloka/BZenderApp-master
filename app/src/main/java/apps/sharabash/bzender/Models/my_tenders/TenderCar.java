package apps.sharabash.bzender.Models.my_tenders;


import org.jetbrains.annotations.NotNull;

public class TenderCar
{
    private String FromKM;

    private String ViolationDocument;

    private String RegistrationFees;

    private String EngineCapacity;

    private String CrrentPoints;

    private String NumberOfCar;

    private String YearOfCar;

    private CarModel CarModel;

    private String TenderId;

    private String TransmissionType;

    private String CarModelId;

    private String Note;

    private String LicenseStatus;

    private CarType CarType;

    private String ToKM;

    private String Id;

    private String PossibilityOfExamination;

    private String CarTypeId;

    private String DeductPoints;

    public String getFromKM ()
    {
        return FromKM;
    }

    public void setFromKM (String FromKM)
    {
        this.FromKM = FromKM;
    }

    public String getViolationDocument ()
    {
        return ViolationDocument;
    }

    public void setViolationDocument (String ViolationDocument)
    {
        this.ViolationDocument = ViolationDocument;
    }

    public String getRegistrationFees ()
    {
        return RegistrationFees;
    }

    public void setRegistrationFees (String RegistrationFees)
    {
        this.RegistrationFees = RegistrationFees;
    }

    public String getEngineCapacity ()
    {
        return EngineCapacity;
    }

    public void setEngineCapacity (String EngineCapacity)
    {
        this.EngineCapacity = EngineCapacity;
    }

    public String getCrrentPoints ()
{
    return CrrentPoints;
}

    public void setCrrentPoints (String CrrentPoints)
    {
        this.CrrentPoints = CrrentPoints;
    }

    public String getNumberOfCar ()
    {
        return NumberOfCar;
    }

    public void setNumberOfCar (String NumberOfCar)
    {
        this.NumberOfCar = NumberOfCar;
    }

    public String getYearOfCar ()
    {
        return YearOfCar;
    }

    public void setYearOfCar (String YearOfCar)
    {
        this.YearOfCar = YearOfCar;
    }

    public CarModel getCarModel ()
    {
        return CarModel;
    }

    public void setCarModel (CarModel CarModel)
    {
        this.CarModel = CarModel;
    }

    public String getTenderId ()
    {
        return TenderId;
    }

    public void setTenderId (String TenderId)
    {
        this.TenderId = TenderId;
    }

    public String getTransmissionType ()
    {
        return TransmissionType;
    }

    public void setTransmissionType (String TransmissionType)
    {
        this.TransmissionType = TransmissionType;
    }

    public String getCarModelId ()
    {
        return CarModelId;
    }

    public void setCarModelId (String CarModelId)
    {
        this.CarModelId = CarModelId;
    }

    public String getNote ()
    {
        return Note;
    }

    public void setNote (String Note)
    {
        this.Note = Note;
    }

    public String getLicenseStatus ()
    {
        return LicenseStatus;
    }

    public void setLicenseStatus (String LicenseStatus)
    {
        this.LicenseStatus = LicenseStatus;
    }

    public CarType getCarType ()
    {
        return CarType;
    }

    public void setCarType (CarType CarType)
    {
        this.CarType = CarType;
    }

    public String getToKM ()
    {
        return ToKM;
    }

    public void setToKM (String ToKM)
    {
        this.ToKM = ToKM;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getPossibilityOfExamination ()
    {
        return PossibilityOfExamination;
    }

    public void setPossibilityOfExamination (String PossibilityOfExamination)
    {
        this.PossibilityOfExamination = PossibilityOfExamination;
    }

    public String getCarTypeId ()
    {
        return CarTypeId;
    }

    public void setCarTypeId (String CarTypeId)
    {
        this.CarTypeId = CarTypeId;
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
        return "ClassPojo [FromKM = "+FromKM+", ViolationDocument = "+ViolationDocument+", RegistrationFees = "+RegistrationFees+", EngineCapacity = "+EngineCapacity+", CrrentPoints = "+CrrentPoints+", NumberOfCar = "+NumberOfCar+", YearOfCar = "+YearOfCar+", CarModel = "+CarModel+", TenderId = "+TenderId+", TransmissionType = "+TransmissionType+", CarModelId = "+CarModelId+", Note = "+Note+", LicenseStatus = "+LicenseStatus+", CarType = "+CarType+", ToKM = "+ToKM+", Id = "+Id+", PossibilityOfExamination = "+PossibilityOfExamination+", CarTypeId = "+CarTypeId+", DeductPoints = "+DeductPoints+"]";
    }
}


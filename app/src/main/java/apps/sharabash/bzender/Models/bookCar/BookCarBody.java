package apps.sharabash.bzender.Models.bookCar;

import org.jetbrains.annotations.NotNull;

public class BookCarBody
{
    private String ViolationDocument;

    private String RegistrationFees;

    private String EngineCapacity;

    private String NumberOfCar;

    private String YearOfCar;

    private String Price;

    private String FromKMToKM;

    private String CarModel;

    private String TenderId;

    private String TransmissionType;

    private String LicenseStatus;

    private String Note;

    private String CarType;

    private String PossibilityOfExamination;

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

    public String getFromKMToKM ()
    {
        return FromKMToKM;
    }

    public void setFromKMToKM (String FromKMToKM)
    {
        this.FromKMToKM = FromKMToKM;
    }

    public String getCarModel ()
    {
        return CarModel;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }


    public void setCarModel (String CarModel)
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

    public String getLicenseStatus ()
    {
        return LicenseStatus;
    }

    public void setLicenseStatus (String LicenseStatus)
    {
        this.LicenseStatus = LicenseStatus;
    }

    public String getNote ()
    {
        return Note;
    }

    public void setNote (String Note)
    {
        this.Note = Note;
    }

    public String getCarType ()
    {
        return CarType;
    }

    public void setCarType (String CarType)
    {
        this.CarType = CarType;
    }

    public String getPossibilityOfExamination ()
    {
        return PossibilityOfExamination;
    }

    public void setPossibilityOfExamination (String PossibilityOfExamination)
    {
        this.PossibilityOfExamination = PossibilityOfExamination;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [ViolationDocument = "+ViolationDocument+", RegistrationFees = "+RegistrationFees+", EngineCapacity = "+EngineCapacity+", NumberOfCar = "+NumberOfCar+", YearOfCar = "+YearOfCar+", FromKMToKM = "+FromKMToKM+", CarModel = "+CarModel+", TenderId = "+TenderId+", TransmissionType = "+TransmissionType+", LicenseStatus = "+LicenseStatus+", Note = "+Note+", CarType = "+CarType+", PossibilityOfExamination = "+PossibilityOfExamination+"]";
    }
}


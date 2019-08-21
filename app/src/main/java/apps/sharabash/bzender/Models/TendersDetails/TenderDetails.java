package apps.sharabash.bzender.Models.TendersDetails;

import org.jetbrains.annotations.NotNull;

public class TenderDetails {
    private TenderCar TenderCar;

    private String Description;

    private String SponserCount;

    private String PathFile;

    private String ImageUrl;

    private String EndDate;

    private String CityEnglishName;

    private String StartDate;

    private String TenderName;

    private String Payed;

    private String statusId;

    private TenderElectrical TenderElectrical;

    private String CountryArabicName;

    private String Id;

    private String CityArabicName;

    private String statudName;

    private String CountryEnglishName;

    private String TenderType;

    public TenderCar getTenderCar ()
    {
        return TenderCar;
    }

    public void setTenderCar (TenderCar TenderCar)
    {
        this.TenderCar = TenderCar;
    }

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getSponserCount ()
    {
        return SponserCount;
    }

    public void setSponserCount (String SponserCount)
    {
        this.SponserCount = SponserCount;
    }

    public String getPathFile ()
    {
        return PathFile;
    }

    public void setPathFile (String PathFile)
    {
        this.PathFile = PathFile;
    }

    public String getImageUrl ()
    {
        return ImageUrl;
    }

    public void setImageUrl (String ImageUrl)
    {
        this.ImageUrl = ImageUrl;
    }

    public String getEndDate ()
    {
        return EndDate;
    }

    public void setEndDate (String EndDate)
    {
        this.EndDate = EndDate;
    }

    public String getCityEnglishName ()
    {
        return CityEnglishName;
    }

    public void setCityEnglishName (String CityEnglishName)
    {
        this.CityEnglishName = CityEnglishName;
    }

    public String getStartDate ()
    {
        return StartDate;
    }

    public void setStartDate (String StartDate)
    {
        this.StartDate = StartDate;
    }

    public String getTenderName ()
    {
        return TenderName;
    }

    public void setTenderName (String TenderName)
    {
        this.TenderName = TenderName;
    }

    public String getPayed ()
    {
        return Payed;
    }

    public void setPayed (String Payed)
    {
        this.Payed = Payed;
    }

    public String getStatusId ()
    {
        return statusId;
    }

    public void setStatusId (String statusId)
    {
        this.statusId = statusId;
    }

    public TenderElectrical getTenderElectrical ()
    {
        return TenderElectrical;
    }

    public void setTenderElectrical (TenderElectrical TenderElectrical)
    {
        this.TenderElectrical = TenderElectrical;
    }

    public String getCountryArabicName ()
    {
        return CountryArabicName;
    }

    public void setCountryArabicName (String CountryArabicName)
    {
        this.CountryArabicName = CountryArabicName;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getCityArabicName ()
    {
        return CityArabicName;
    }

    public void setCityArabicName (String CityArabicName)
    {
        this.CityArabicName = CityArabicName;
    }

    public String getStatudName ()
    {
        return statudName;
    }

    public void setStatudName (String statudName)
    {
        this.statudName = statudName;
    }

    public String getCountryEnglishName ()
    {
        return CountryEnglishName;
    }

    public void setCountryEnglishName (String CountryEnglishName)
    {
        this.CountryEnglishName = CountryEnglishName;
    }

    public String getTenderType ()
    {
        return TenderType;
    }

    public void setTenderType (String TenderType)
    {
        this.TenderType = TenderType;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [TenderCar = "+TenderCar+", Description = "+Description+", SponserCount = "+SponserCount+", PathFile = "+PathFile+", ImageUrl = "+ImageUrl+", EndDate = "+EndDate+", CityEnglishName = "+CityEnglishName+", StartDate = "+StartDate+", TenderName = "+TenderName+", Payed = "+Payed+", statusId = "+statusId+", TenderElectrical = "+TenderElectrical+", CountryArabicName = "+CountryArabicName+", Id = "+Id+", CityArabicName = "+CityArabicName+", statudName = "+statudName+", CountryEnglishName = "+CountryEnglishName+", TenderType = "+TenderType+"]";
    }
}


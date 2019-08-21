package apps.sharabash.bzender.Models.my_tenders;

import org.jetbrains.annotations.NotNull;

public class MyTendersBody {
    private String CategoryID;

    private TenderCar TenderCar;

    private String Address;

    private String StartDateTender;

    private String StatusName;

    private String TenderDescrioption;

    private String BookingCount;

    private String TenderName;

    private String OwnerUserId;

    private String CityId;

    private apps.sharabash.bzender.Models.my_tenders.TenderElectrical TenderElectrical;

    private int StatusId;

    private String CountryArabicName;

    private String Id;

    private String EndDateTender;

    private String CountryEnglishName;

    public String getCategoryID ()
    {
        return CategoryID;
    }

    public void setCategoryID (String CategoryID)
    {
        this.CategoryID = CategoryID;
    }

    public TenderCar getTenderCar ()
    {
        return TenderCar;
    }

    public void setTenderCar (TenderCar TenderCar)
    {
        this.TenderCar = TenderCar;
    }

    public String getAddress ()
    {
        return Address;
    }

    public void setAddress (String Address)
    {
        this.Address = Address;
    }

    public String getStartDateTender ()
    {
        return StartDateTender;
    }

    public void setStartDateTender (String StartDateTender)
    {
        this.StartDateTender = StartDateTender;
    }

    public String getStatusName ()
    {
        return StatusName;
    }

    public void setStatusName (String StatusName)
    {
        this.StatusName = StatusName;
    }

    public String getTenderDescrioption ()
    {
        return TenderDescrioption;
    }

    public void setTenderDescrioption (String TenderDescrioption)
    {
        this.TenderDescrioption = TenderDescrioption;
    }

    public String getBookingCount ()
    {
        return BookingCount;
    }

    public void setBookingCount (String BookingCount)
    {
        this.BookingCount = BookingCount;
    }

    public String getTenderName ()
    {
        return TenderName;
    }

    public void setTenderName (String TenderName)
    {
        this.TenderName = TenderName;
    }

    public String getOwnerUserId ()
    {
        return OwnerUserId;
    }

    public void setOwnerUserId (String OwnerUserId)
    {
        this.OwnerUserId = OwnerUserId;
    }

    public String getCityId ()
    {
        return CityId;
    }

    public void setCityId (String CityId)
    {
        this.CityId = CityId;
    }

    public apps.sharabash.bzender.Models.my_tenders.TenderElectrical getTenderElectrical ()
    {
        return TenderElectrical;
    }

    public void setTenderElectrical (apps.sharabash.bzender.Models.my_tenders.TenderElectrical TenderElectrical)
    {
        this.TenderElectrical = TenderElectrical;
    }

    public int getStatusId ()
    {
        return StatusId;
    }

    public void setStatusId (int StatusId)
    {
        this.StatusId = StatusId;
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

    public String getEndDateTender ()
    {
        return EndDateTender;
    }

    public void setEndDateTender (String EndDateTender)
    {
        this.EndDateTender = EndDateTender;
    }

    public String getCountryEnglishName ()
    {
        return CountryEnglishName;
    }

    public void setCountryEnglishName (String CountryEnglishName)
    {
        this.CountryEnglishName = CountryEnglishName;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [CategoryID = "+CategoryID+", TenderCar = "+TenderCar+", Address = "+Address+", StartDateTender = "+StartDateTender+", StatusName = "+StatusName+", TenderDescrioption = "+TenderDescrioption+", BookingCount = "+BookingCount+", TenderName = "+TenderName+", OwnerUserId = "+OwnerUserId+", CityId = "+CityId+", TenderElectrical = "+TenderElectrical+", StatusId = "+StatusId+", CountryArabicName = "+CountryArabicName+", Id = "+Id+", EndDateTender = "+EndDateTender+", CountryEnglishName = "+CountryEnglishName+"]";
    }
}



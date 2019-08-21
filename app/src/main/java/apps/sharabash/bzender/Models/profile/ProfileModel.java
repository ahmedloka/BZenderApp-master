package apps.sharabash.bzender.Models.profile;

import org.jetbrains.annotations.NotNull;

public class ProfileModel {

    private String MyReservedTenderCount;

    private String Points;

    private String Email;

    private String UserName;

    private String MytenderCount;

    private String PhoneNumber;

    private String CityName;

    private String CountryCode;

    private String ImgUrl;

    public String getMyReservedTenderCount ()
    {
        return MyReservedTenderCount;
    }

    public void setMyReservedTenderCount (String MyReservedTenderCount)
    {
        this.MyReservedTenderCount = MyReservedTenderCount;
    }

    public String getPoints ()
    {
        return Points;
    }

    public void setPoints (String Points)
    {
        this.Points = Points;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public String getMytenderCount ()
    {
        return MytenderCount;
    }

    public void setMytenderCount (String MytenderCount)
    {
        this.MytenderCount = MytenderCount;
    }

    public String getPhoneNumber ()
    {
        return PhoneNumber;
    }

    public void setPhoneNumber (String PhoneNumber)
    {
        this.PhoneNumber = PhoneNumber;
    }

    public String getCityName ()
    {
        return CityName;
    }

    public void setCityName (String CityName)
    {
        this.CityName = CityName;
    }

    public String getCountryCode ()
    {
        return CountryCode;
    }

    public void setCountryCode (String CountryCode)
    {
        this.CountryCode = CountryCode;
    }

    public String getImgUrl ()
    {
        return ImgUrl;
    }

    public void setImgUrl (String ImgUrl)
    {
        this.ImgUrl = ImgUrl;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [MyReservedTenderCount = "+MyReservedTenderCount+", Points = "+Points+", Email = "+Email+", UserName = "+UserName+", MytenderCount = "+MytenderCount+", PhoneNumber = "+PhoneNumber+", CityName = "+CityName+", CountryCode = "+CountryCode+", ImgUrl = "+ImgUrl+"]";
    }
}


package apps.sharabash.bzender.Models.signUp;

import org.jetbrains.annotations.NotNull;

public class SignUpRequest {

    private String CountryCodeId;

    private String Email;

    private String CityId;

    private String Imagedata;

    private String FullName;

    private String PhoneNumber;

    private String ImageUrl;

    private String Password;

    public String getCountryCodeId ()
    {
        return CountryCodeId;
    }

    public void setCountryCodeId (String CountryCodeId)
    {
        this.CountryCodeId = CountryCodeId;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getCityId ()
    {
        return CityId;
    }

    public void setCityId (String CityId)
    {
        this.CityId = CityId;
    }

    public String getImagedata ()
    {
        return Imagedata;
    }

    public void setImagedata (String Imagedata)
    {
        this.Imagedata = Imagedata;
    }

    public String getFullName ()
    {
        return FullName;
    }

    public void setFullName (String FullName)
    {
        this.FullName = FullName;
    }

    public String getPhoneNumber ()
    {
        return PhoneNumber;
    }

    public void setPhoneNumber (String PhoneNumber)
    {
        this.PhoneNumber = PhoneNumber;
    }

    public String getImageUrl ()
    {
        return ImageUrl;
    }

    public void setImageUrl (String ImageUrl)
    {
        this.ImageUrl = ImageUrl;
    }

    public String getPassword ()
    {
        return Password;
    }

    public void setPassword (String Password)
    {
        this.Password = Password;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [CountryCodeId = "+CountryCodeId+", Email = "+Email+", CityId = "+CityId+", Imagedata = "+Imagedata+", FullName = "+FullName+", PhoneNumber = "+PhoneNumber+", ImageUrl = "+ImageUrl+", Password = "+Password+"]";
    }
}


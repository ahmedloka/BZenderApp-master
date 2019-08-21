package apps.sharabash.bzender.Models;

import org.jetbrains.annotations.NotNull;

public class EditProfileResponse {

    private String CountryCodeId;

    private String Email;

    private String UserName;

    private String CityId;

    private String PhoneNumber;

    private String ImgUrl;

    public String getCountryCodeId() {
        return CountryCodeId;
    }

    public void setCountryCodeId(String CountryCodeId) {
        this.CountryCodeId = CountryCodeId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String CityId) {
        this.CityId = CityId;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [CountryCodeId = " + CountryCodeId + ", Email = " + Email + ", UserName = " + UserName + ", CityId = " + CityId + ", PhoneNumber = " + PhoneNumber + ", ImgUrl = " + ImgUrl + "]";
    }
}



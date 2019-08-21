package apps.sharabash.bzender.Models.signUp;

import org.jetbrains.annotations.NotNull;

public class CountryCodeResponse {

    private String CountryArabicName;

    private String Id;

    private String CountryCode;

    private String CountryEnglishName;

    public String getCountryArabicName() {
        return CountryArabicName;
    }

    public void setCountryArabicName(String countryArabicName) {
        CountryArabicName = countryArabicName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getCountryEnglishName() {
        return CountryEnglishName;
    }

    public void setCountryEnglishName(String countryEnglishName) {
        CountryEnglishName = countryEnglishName;
    }

    @NotNull
    @Override
    public String toString() {
        return "CountryCodeResponse{" +
                "CountryArabicName='" + CountryArabicName + '\'' +
                ", Id='" + Id + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", CountryEnglishName='" + CountryEnglishName + '\'' +
                '}';
    }
}

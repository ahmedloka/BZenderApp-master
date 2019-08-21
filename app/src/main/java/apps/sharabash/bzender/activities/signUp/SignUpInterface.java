package apps.sharabash.bzender.activities.signUp;

import java.util.List;

import apps.sharabash.bzender.Models.signUp.CountryCodeResponse;

public interface SignUpInterface {
    void getAllCodes(List<CountryCodeResponse> countryCodeResponseList);
}

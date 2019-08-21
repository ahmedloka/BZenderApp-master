package apps.sharabash.bzender.Models;

public class CustomerAddress {
    private String Address;

    private String CustomerAddressId;

    private String Latitude;

    private String CityName;

    private String Longitude;


    public CustomerAddress(String address, String customerAddressId, String latitude, String cityName, String longitude) {
        Address = address;
        CustomerAddressId = customerAddressId;
        Latitude = latitude;
        CityName = cityName;
        Longitude = longitude;

    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCustomerAddressId() {
        return CustomerAddressId;
    }

    public void setCustomerAddressId(String customerAddressId) {
        CustomerAddressId = customerAddressId;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}

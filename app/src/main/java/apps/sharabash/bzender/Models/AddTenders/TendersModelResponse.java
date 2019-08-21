package apps.sharabash.bzender.Models.AddTenders;

public class TendersModelResponse {
    private String StartDate;

    private String Id;

    private String EndDate;

    private String Bookingcount;

    private String ArabicName;

    private String EnglishName;

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getBookingcount() {
        return Bookingcount;
    }

    public void setBookingcount(String bookingcount) {
        Bookingcount = bookingcount;
    }

    public String getArabicName() {
        return ArabicName;
    }

    public void setArabicName(String arabicName) {
        ArabicName = arabicName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }
}

package apps.sharabash.bzender.Models.bookCar;

import org.jetbrains.annotations.NotNull;

public class BookCarResponse {
    private String TenderCarBookingId ;

    public String getTenderCarBookingId() {
        return TenderCarBookingId;
    }

    public void setTenderCarBookingId(String tenderCarBookingId) {
        TenderCarBookingId = tenderCarBookingId;
    }

    @NotNull
    @Override
    public String toString() {
        return "BokCarResponse{" +
                "TenderCarBookingId='" + TenderCarBookingId + '\'' +
                '}';
    }
}

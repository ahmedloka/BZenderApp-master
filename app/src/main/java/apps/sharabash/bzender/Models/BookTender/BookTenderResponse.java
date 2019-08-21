package apps.sharabash.bzender.Models.BookTender;

import org.jetbrains.annotations.NotNull;

class BookTenderResponse {
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
        return "BookTenderResponse{" +
                "TenderCarBookingId='" + TenderCarBookingId + '\'' +
                '}';
    }
}
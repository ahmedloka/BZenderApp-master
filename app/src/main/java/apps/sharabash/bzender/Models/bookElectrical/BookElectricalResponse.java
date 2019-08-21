package apps.sharabash.bzender.Models.bookElectrical;

import org.jetbrains.annotations.NotNull;

public class BookElectricalResponse {
    private String TenderElectricalBookingId ;

    public String getTenderElectricalBookingId() {
        return TenderElectricalBookingId;
    }

    public void setTenderElectricalBookingId(String tenderElectricalBookingId) {
        TenderElectricalBookingId = tenderElectricalBookingId;
    }

    @NotNull
    @Override
    public String toString() {
        return "BookElectricalResponse{" +
                "TenderElectricalBookingId='" + TenderElectricalBookingId + '\'' +
                '}';
    }
}

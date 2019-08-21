package apps.sharabash.bzender.Models.imgs;

import org.jetbrains.annotations.NotNull;

public class UploadElectricalRequest {

    private String tenderElectricalBookingImageId ;

    public String getTenderElectricalBookingImageId() {
        return tenderElectricalBookingImageId;
    }

    public void setTenderElectricalBookingImageId(String tenderElectricalBookingImageId) {
        this.tenderElectricalBookingImageId = tenderElectricalBookingImageId;
    }

    @NotNull
    @Override
    public String toString() {
        return "UploadElectricalRequest{" +
                "tenderElectricalBookingImageId='" + tenderElectricalBookingImageId + '\'' +
                '}';
    }
}

package apps.sharabash.bzender.Models.imgs;

import org.jetbrains.annotations.NotNull;

public class UploadElectricalImage {
    private String TenderElectricalBookingId;

    private String Image;

    public String getTenderElectricalBookingId() {
        return TenderElectricalBookingId;
    }

    public void setTenderElectricalBookingId(String TenderElectricalBookingId) {
        this.TenderElectricalBookingId = TenderElectricalBookingId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [TenderElectricalBookingId = " + TenderElectricalBookingId + ", Image = " + Image + "]";
    }
}

package apps.sharabash.bzender.Models.imgs;

import org.jetbrains.annotations.NotNull;

public class UploadCarIamge {

    private String TenderCarBookingId;

    private String Image;

    public String getTenderCarBookingId() {
        return TenderCarBookingId;
    }

    public void setTenderCarBookingId(String tenderCarBookingId) {
        TenderCarBookingId = tenderCarBookingId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @NotNull
    @Override
    public String toString() {
        return "UploadCarIamge{" +
                "TenderCarBookingId='" + TenderCarBookingId + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}

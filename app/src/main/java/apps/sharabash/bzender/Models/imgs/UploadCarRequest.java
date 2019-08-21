package apps.sharabash.bzender.Models.imgs;

import org.jetbrains.annotations.NotNull;

public class UploadCarRequest {

    private String tenderCarBookingImageId ;

    public String getTenderCarBookingImageId() {
        return tenderCarBookingImageId;
    }

    public void setTenderCarBookingImageId(String tenderCarBookingImageId) {
        this.tenderCarBookingImageId = tenderCarBookingImageId;
    }

    @NotNull
    @Override
    public String toString() {
        return "UploadCarRequest{" +
                "tenderCarBookingImageId='" + tenderCarBookingImageId + '\'' +
                '}';
    }
}

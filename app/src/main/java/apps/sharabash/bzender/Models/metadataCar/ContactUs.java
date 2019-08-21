package apps.sharabash.bzender.Models.metadataCar;


import org.jetbrains.annotations.NotNull;

public class ContactUs {
    private String TwitterUrl;

    private String Email;

    private String Address;

    private String FaceBookUrl;

    private String Phone;

    private String Id;

    private String WhatsApp;

    private String Instagram;

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getTwitterUrl() {
        return TwitterUrl;
    }

    public void setTwitterUrl(String TwitterUrl) {
        this.TwitterUrl = TwitterUrl;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getFaceBookUrl() {
        return FaceBookUrl;
    }

    public void setFaceBookUrl(String FaceBookUrl) {
        this.FaceBookUrl = FaceBookUrl;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getWhatsApp() {
        return WhatsApp;
    }

    public void setWhatsApp(String WhatsApp) {
        this.WhatsApp = WhatsApp;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [TwitterUrl = " + TwitterUrl + ", Email = " + Email + ", Address = " + Address + ", FaceBookUrl = " + FaceBookUrl + ", Phone = " + Phone + ", Id = " + Id + ", WhatsApp = " + WhatsApp + "]";
    }
}
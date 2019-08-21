package apps.sharabash.bzender.Models.home;

public class getCategoryResponse {
    private String ImgUrl;

    private String Id;

    private String EnglishName;

    private String ArabicName;

    public String getImageUrl() {
        return ImgUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImgUrl = imageUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getArabicName() {
        return ArabicName;
    }

    public void setArabicName(String arabicName) {
        ArabicName = arabicName;
    }
}

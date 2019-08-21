package apps.sharabash.bzender.Models.AddTenders;

import org.jetbrains.annotations.NotNull;

public class AllCitiesModel {

    private String Id;

    private String ArabicName;

    private String EnglishName;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getArabicName() {
        return ArabicName;
    }

    public void setArabicName(String ArabicName) {
        this.ArabicName = ArabicName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String EnglishName) {
        this.EnglishName = EnglishName;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [Id = " + Id + ", ArabicName = " + ArabicName + ", EnglishName = " + EnglishName + "]";
    }
}

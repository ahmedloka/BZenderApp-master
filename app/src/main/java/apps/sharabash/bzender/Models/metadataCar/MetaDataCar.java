package apps.sharabash.bzender.Models.metadataCar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MetaDataCar {
    private List<CarTypes> CarTypes;

    private List<CarModels> CarModels;

    private List<TermsAndCondition> TermsAndCondition;

    private List<ContactUs> ContactUs;

    public List<apps.sharabash.bzender.Models.metadataCar.TermsAndCondition> getTermsAndCondition() {
        return TermsAndCondition;
    }

    public void setTermsAndCondition(List<apps.sharabash.bzender.Models.metadataCar.TermsAndCondition> termsAndCondition) {
        TermsAndCondition = termsAndCondition;
    }

    public List<apps.sharabash.bzender.Models.metadataCar.ContactUs> getContactUs() {
        return ContactUs;
    }

    public void setContactUs(List<apps.sharabash.bzender.Models.metadataCar.ContactUs> contactUs) {
        ContactUs = contactUs;
    }

    public List<CarTypes> getCarTypes() {
        return CarTypes;
    }

    public void setCarTypes(List<CarTypes> CarTypes) {
        this.CarTypes = CarTypes;
    }

    public List<CarModels> getCarModels() {
        return CarModels;
    }

    public void setCarModels(List<CarModels> CarModels) {
        this.CarModels = CarModels;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [CarTypes = " + CarTypes + ", CarModels = " + CarModels + "]";
    }
}

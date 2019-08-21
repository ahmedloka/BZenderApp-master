package apps.sharabash.bzender.Models.BookTender;

import org.jetbrains.annotations.NotNull;

class BookTenderBody {
    private String ViolationDocument;

    private String RegistrationFees;

    private String EngineCapacity;

    private String NumberOfCar;

    private String YearOfCar;

    private String FromKMToKM;

    private String CarModel;

    private String TenderId;

    private String OwnerUserId;

    private String TransmissionType;

    private String LicenseStatus;

    private String Note;

    private String CarType;

    private String Id;

    private String PossibilityOfExamination;

    public String getViolationDocument() {
        return ViolationDocument;
    }

    public void setViolationDocument(String violationDocument) {
        ViolationDocument = violationDocument;
    }

    public String getRegistrationFees() {
        return RegistrationFees;
    }

    public void setRegistrationFees(String registrationFees) {
        RegistrationFees = registrationFees;
    }

    public String getEngineCapacity() {
        return EngineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        EngineCapacity = engineCapacity;
    }

    public String getNumberOfCar() {
        return NumberOfCar;
    }

    public void setNumberOfCar(String numberOfCar) {
        NumberOfCar = numberOfCar;
    }

    public String getYearOfCar() {
        return YearOfCar;
    }

    public void setYearOfCar(String yearOfCar) {
        YearOfCar = yearOfCar;
    }

    public String getFromKMToKM() {
        return FromKMToKM;
    }

    public void setFromKMToKM(String fromKMToKM) {
        FromKMToKM = fromKMToKM;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public String getTenderId() {
        return TenderId;
    }

    public void setTenderId(String tenderId) {
        TenderId = tenderId;
    }

    public String getOwnerUserId() {
        return OwnerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        OwnerUserId = ownerUserId;
    }

    public String getTransmissionType() {
        return TransmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        TransmissionType = transmissionType;
    }

    public String getLicenseStatus() {
        return LicenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        LicenseStatus = licenseStatus;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPossibilityOfExamination() {
        return PossibilityOfExamination;
    }

    public void setPossibilityOfExamination(String possibilityOfExamination) {
        PossibilityOfExamination = possibilityOfExamination;
    }

    @NotNull
    @Override
    public String toString() {
        return "BookTenderBody{" +
                "ViolationDocument='" + ViolationDocument + '\'' +
                ", RegistrationFees='" + RegistrationFees + '\'' +
                ", EngineCapacity='" + EngineCapacity + '\'' +
                ", NumberOfCar='" + NumberOfCar + '\'' +
                ", YearOfCar='" + YearOfCar + '\'' +
                ", FromKMToKM='" + FromKMToKM + '\'' +
                ", CarModel='" + CarModel + '\'' +
                ", TenderId='" + TenderId + '\'' +
                ", OwnerUserId='" + OwnerUserId + '\'' +
                ", TransmissionType='" + TransmissionType + '\'' +
                ", LicenseStatus='" + LicenseStatus + '\'' +
                ", Note='" + Note + '\'' +
                ", CarType='" + CarType + '\'' +
                ", Id='" + Id + '\'' +
                ", PossibilityOfExamination='" + PossibilityOfExamination + '\'' +
                '}';
    }
}

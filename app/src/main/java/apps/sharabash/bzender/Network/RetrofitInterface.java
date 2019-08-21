package apps.sharabash.bzender.Network;


import java.util.List;

import apps.sharabash.bzender.Models.AddTenders.AddTinderPojo;
import apps.sharabash.bzender.Models.AddTenders.AddTinderResponse;
import apps.sharabash.bzender.Models.AddTenders.AllCitiesModel;
import apps.sharabash.bzender.Models.AddTenders.TendersModelResponse;
import apps.sharabash.bzender.Models.EditProfileResponse;
import apps.sharabash.bzender.Models.FillDataCar;
import apps.sharabash.bzender.Models.FillDataElectrical;
import apps.sharabash.bzender.Models.ForgetPasswordBody;
import apps.sharabash.bzender.Models.ForgetPasswordResponse;
import apps.sharabash.bzender.Models.GetOffers;
import apps.sharabash.bzender.Models.TendersDetails.TenderDetails;
import apps.sharabash.bzender.Models.TendersDetails.electrical.TenderDetailsElectrical;
import apps.sharabash.bzender.Models.VerifyBody;
import apps.sharabash.bzender.Models.allTinders.car.AllTender;
import apps.sharabash.bzender.Models.allTinders.electrical.AllTenderElectrical;
import apps.sharabash.bzender.Models.bookCar.BookCarBody;
import apps.sharabash.bzender.Models.bookCar.BookCarResponse;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalBody;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalResponse;
import apps.sharabash.bzender.Models.changePassword.ChangePasswordModel;
import apps.sharabash.bzender.Models.chatList.AllUserMessage;
import apps.sharabash.bzender.Models.complaint.ComplaintModel;
import apps.sharabash.bzender.Models.complaint.ComplaintResponse;
import apps.sharabash.bzender.Models.editProfile.EditProfileModel;
import apps.sharabash.bzender.Models.home.getCategoryResponse;
import apps.sharabash.bzender.Models.imgs.UploadCarIamge;
import apps.sharabash.bzender.Models.imgs.UploadCarRequest;
import apps.sharabash.bzender.Models.imgs.UploadElectricalImage;
import apps.sharabash.bzender.Models.imgs.UploadElectricalRequest;
import apps.sharabash.bzender.Models.login.loginRequestModel;
import apps.sharabash.bzender.Models.login.loginResponse;
import apps.sharabash.bzender.Models.metadataCar.MetaDataCar;
import apps.sharabash.bzender.Models.my_tenders.MyBookingBody;
import apps.sharabash.bzender.Models.my_tenders.MyTendersBody;
import apps.sharabash.bzender.Models.notification.NotificationResponse;
import apps.sharabash.bzender.Models.profile.ProfileModel;
import apps.sharabash.bzender.Models.push.PushNotification;
import apps.sharabash.bzender.Models.push.PushResponse;
import apps.sharabash.bzender.Models.signUp.CountryCodeResponse;
import apps.sharabash.bzender.Models.signUp.SignUpRequest;
import apps.sharabash.bzender.Models.signUp.signUpResponse;
import apps.sharabash.bzender.Models.singleChat.SingleChatResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {

    @POST("api/Account/Login")
    Observable<loginResponse> login(@Body loginRequestModel loginRequestModel);

    @POST("api/Account/Register")
    Observable<signUpResponse> signUp(@Body SignUpRequest signUpRequest);

    @GET("api/Category")
    Observable<List<getCategoryResponse>> getCategory();

    @GET("api/Category/GetCategory")
    Observable<List<TendersModelResponse>> getTenderByCategory(@Query("id") String id);

    @GET("api/GetTender")
    Observable<TenderDetails> getTenderCarDetails(@Query("id") String id);

    @GET("api/GetTender")
    Observable<TenderDetailsElectrical> getTenderElectricalDetails(@Query("id") String id);

    @POST("api/AddTender")
    Observable<AddTinderResponse> addTinder(@Body AddTinderPojo addTinderPojo);


    @GET("Api/City/GetAllCities")
    Observable<List<AllCitiesModel>> getAllCities();



    @POST("Api/Complaint/Addcomplaint")
    Observable<ComplaintResponse> sendComplaint(@Body ComplaintModel complaint);


    @GET("api/Profile")
    Observable<ProfileModel> getProfileData();

    @POST("/api/Profile/SaveToProfile")
    Observable<EditProfileResponse> editProfile(@Body EditProfileModel editProfileModel);


    @POST("api/Account/ChangePassword")
    Observable<ChangePasswordModel> changePassword(@Body ChangePasswordModel changePasswordModel);

    @GET("api/Country/GetAll")
    Observable<List<CountryCodeResponse>> getCountriesCode();

    @POST("api/PushTokens/AddToken")
    Observable<PushResponse> pushNotification(@Body PushNotification pushNotification);

    @GET("api/GetTenderByCategoryId")
    Observable<List<AllTender>> getAllTendersCar(@Query("categoryId") String catId);

    @GET("api/GetTenderByCategoryId")
    Observable<List<AllTenderElectrical>> getAllTendersElectrical(@Query("categoryId") String catId);

    @GET("api/GetMetaData")
    Observable<MetaDataCar> getMetaData();


    @POST("api/AddTenderCar")
    Observable<FillDataCar> fillDataCar(@Body FillDataCar fillDataCar);

    @POST("api/AddTenderElectrical")
    Observable<FillDataElectrical> fillDataElectrical(@Body FillDataElectrical fillDataCar);

    @GET("api/GetOffers")
    Observable<GetOffers> getAllImages();

    @POST("api/AddTenderCarBooking")
    Observable<BookCarResponse> bookCar(@Body BookCarBody bookCarBody);

    @POST("api/AddTenderElectricalBooking")
    Observable<BookElectricalResponse> bookElectrical(@Body BookElectricalBody bookCarBody);

    @POST("api/AddTenderCarBookingImage")
    Observable<UploadCarRequest> uploadCarImage(@Body UploadCarIamge uploadCarIamge);

    @POST("api/AddTenderElectricalBookingImage")
    Observable<UploadElectricalRequest> uploadElectricalImage(@Body UploadElectricalImage uploadElectricalImage);


    @GET("api/GetBooking")
    Observable<MyBookingBody> getMyBooking();


    @GET("MyTenders")
    Observable<List<MyTendersBody>> getMyTender();

    @GET("api/Account/Verify")
    Observable<VerifyBody> verifyAcc(@Query("Vcode") String code, @Query("Email") String email, @Query("Password") String password);


    @POST("api/Account/forgetpassword")
    Observable<ForgetPasswordResponse> forgetPasword(@Body ForgetPasswordBody forgetPasswordBody);

    @GET("api/GetNotifications")
    Observable<NotificationResponse> getNotifications(@Query("Page") int number);

    @GET("api/getAllUserMessagesWithOthers")
    Observable<AllUserMessage> getAllUserChat();


    @GET("api/GetChatMessages")
    Observable<SingleChatResponse> getChatChatRoomData(@Query("partnerId") String senderId , @Query("page") int page );


}
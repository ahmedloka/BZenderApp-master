package apps.sharabash.bzender.Utills;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import apps.sharabash.bzender.Models.metadataCar.CarModels;
import apps.sharabash.bzender.Models.metadataCar.CarTypes;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.activities.ChooseHowItWork;
import apps.sharabash.bzender.activities.Home.Home;
import apps.sharabash.bzender.activities.imagesBook.ImagesActivity;
import apps.sharabash.bzender.activities.verfication.VerificationActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import libs.mjn.prettydialog.PrettyDialog;

import static android.content.Context.MODE_PRIVATE;


public class Constant {
    public static final String BASE_URL_HTTP = "http://pixelserver-001-site29.ctempurl.com/";

    public static final String BASE_URL_HTTP_Get_Address = "https://maps.googleapis.com/maps/api/";


    public static final String URL_KEY = "webViewKey";
    public static final String counteryID = "CountryID";
    public static final String counteryName = "1";
    public static final String counterylogo = "counterylogo";
    public static final String cityID = "CityID";
    public static final String cityName = "City_name";
    public static final String citylogo = "citylogo";
    public static final String UserID = "UserID";
    public static final String MerchantID = "MerchantID";
    public static final String BranchAR = "BranchAR";
    public static final String lat = "lat";
    public static final String lng = "lng";
    public static final String BranchEN = "BranchEN";
    public static final String Username = "Username";
    public static final String UsernameAr = "UsernameAr";
    public static final String Useremail = "Usersemail";
    public static final String mobile = "mobile";
    public static final String mobileKey = "mobileKey";
    public static final String QR = "QR";
    public static final String Activationcode = "Activationcode";
    public static final String Activationstate = "Activationstate";
    public static final String expiredate = "expiredate";
    public static final String gender = "gender";
    public static final String birthdate = "birthdate";
    public static final String nationalityid = "nationalityid";
    public static final String imageUri = "imageUri";
    public static final String status = "status";
    public static final String nationalitynameen = "nationalitynameen";
    public static final String nationalitynamear = "nationalitynamear";
    public static final String language = "Language";
    public static final String UserFirstNameAr = "UserFirstNameAr";
    public static final String UserImage = "UserImage";
    public static final String UserLastNameAr = "UserLastNameAr";
    public static final String ANDROID_OS = "1";
    public static final String TENDER_ID = "tender_id";
    public static final String ELECTRICAL = "ELECTRICAL";
    public static final String CAR = "CAR";
    public static final String PASSWORD = "Password";
    public static final String IMAGE_URL = "IMAGE_URL";
    public static final String devicetoken = "devicetoken";
    public static final List<CarTypes> wheelDataCarType = new ArrayList<>();
    public static final List<CarModels> wheelDataCarModel = new ArrayList<>();
    public static final String RECEIVER_ID = "RECEIVER_ID";
    public static String INSTGRAM_URL;
    public static String FACE_BOOK_URL = "";
    public static String TWITTER_URL = "";
    public static String WHATS_APP_NUMBER = "";
    public static int ADD_TENDER_ID;
    public static int SELECTED_TENDER_TYPE;
    public static String TIMER;
    public static String logoutNotificationTitle = "logoutNotificationTitle";
    public static String logoutNotificationBody = "logoutNotificationBody";
    public static String categoryId = "";
    public static String tenderId = "";
    public static String BOOKING_ID;
    public static String CAR_BOOKING_ID;
    public static String TYPE;
    public static String NAME = "NAME";
    public static String SENDER_ID = "SENDER_ID";
    public static String USER_ID_CHAT = "USER_ID_CHAT";
    public static String OLD_BASE64;

    public static String parseXML(String response) {
        String json = "";
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(response));

            Document doc = builder.parse(src);

            json = doc.getElementsByTagName("string").item(0).getTextContent();
            Log.i("1", json);
            Gson gson = new Gson();
            // Toast.makeText(getApplicationContext(),likeModel.getResultMsg(),Toast.LENGTH_SHORT).show();


        } catch (ParserConfigurationException e) {


            e.printStackTrace();
        } catch (SAXException e) {


            e.printStackTrace();
        } catch (IOException e) {


            e.printStackTrace();
        }
        return json;
    }

    public static String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        Log.d("BASE64", "convertToBase64: " + Base64.encodeToString(imgByte, Base64.DEFAULT));

        return Base64.encodeToString(imgByte, Base64.DEFAULT);

    }

    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    public static boolean isLTR(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_LEFT_TO_RIGHT ||
                directionality == Character.DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING;
    }


    public static AlertDialog.Builder buildDialog(Activity c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet connection.");
        builder.setMessage("You have no internet connection");
        builder.setMessage("please check internet connection");
        builder.setIcon(R.drawable.wifi_ic);
        builder.setCancelable(false);

        builder.setPositiveButton("Reload", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(c, "please check internet connection", Toast.LENGTH_LONG).show();
                //builder.show();
                c.finish();
                c.startActivities(new Intent[]{c.getIntent()});
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                c.finish();
            }
        });
        return builder;
    }


    public static void ErrorDialog(Activity c, String errorMessage) {

        android.support.v7.app.AlertDialog.Builder builder;
        android.support.v7.app.AlertDialog dialog;
        builder = new android.support.v7.app.AlertDialog.Builder(c);

        @SuppressLint("InflateParams")
        View mview = c.getLayoutInflater().inflate(R.layout.dialog_error, null);
        builder.setView(mview);
        dialog = builder.create();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView message = mview.findViewById(R.id.message);
        TextView cancel = mview.findViewById(R.id.cancel);
        TextView ok = mview.findViewById(R.id.ok);
        message.setText(errorMessage);
        cancel.setOnClickListener(v2 -> {
            dialog.dismiss();
        });
        ok.setOnClickListener(v2 -> {
            dialog.dismiss();
        });
    }


    public static String getLng(Context context) {
        SharedPreferences mSharedPreferences;

        mSharedPreferences = context.getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        return mSharedPreferences.getString(Constant.language, Locale.getDefault().getLanguage());
    }

    public static void changeLang(Context mContext, String countryCode) {
        Resources res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(countryCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }


    public static AlertDialog showLoaderDialog(Context context) {
        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(context);

        View mView = ((Activity) context).getLayoutInflater().inflate(R.layout.dilaog_loader_layout, null);

        AVLoadingIndicatorView mAvi = mView.findViewById(R.id.avi);
        mAvi.show();

        builder1.setView(mView);
        dialog1 = builder1.create();
        Window window = dialog1.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog1.show();

        return dialog1;

    }


    public static void showSuccessTenderBookDialog(Context context, String message, String id, String type) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_success)
                .setTitle(message)
                .addButton(context.getString(R.string.done), android.R.color.white, R.color.color_green, () -> {
                    Intent intent = new Intent(context, ImagesActivity.class);
                    intent.putExtra(Constant.BOOKING_ID, id);
                    intent.putExtra(Constant.TYPE, type);
                    context.startActivity(intent);
                    Animatoo.animateZoom(context);
                    ((Activity) context).finish();

                    if (prettyDialog.isShowing()) {
                        prettyDialog.dismiss();
                    }

                })
                .show();
    }

    public static void showErrorDialog(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_error)
                .setTitle(message)
                .addButton(context.getString(R.string.ok), android.R.color.white, R.color.pdlg_color_red, prettyDialog::dismiss)
                .show();
    }

    public static void showErrorDialogVerification(Context context) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_error)
                .setTitle(context.getString(R.string.response_ten))
                .addButton(context.getString(R.string.ok), android.R.color.white, R.color.pdlg_color_red, () -> {
                    Intent intent = new Intent(context, VerificationActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
                    ((Activity) context).finish();

                    if (prettyDialog.isShowing())
                        prettyDialog.dismiss();
                })
                .show();
    }

    public static void showSuccessDialogAndSetClassForEdit(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);
        prettyDialog
                .setTitle(message)
                .setIcon(R.drawable.ic_success)
                .addButton(context.getString(R.string.done), android.R.color.white, R.color.color_green, () -> {
                    NavUtils.navigateUpFromSameTask(((Activity) context));
                    if (prettyDialog.isShowing())
                        prettyDialog.dismiss();

                    Intent intent = new Intent(context, Home.class);
                    ((Activity) context).overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                })
                .show();
    }

    public static void showSuccessDialogAndSetClassFor(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);
        prettyDialog
                .setTitle(message)
                .setIcon(R.drawable.ic_success)
                .addButton(context.getString(R.string.done), android.R.color.white, R.color.color_green, () -> {
                    Intent intent = new Intent(context, Home.class);
                    ((Activity) context).overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                    if (prettyDialog.isShowing())
                        prettyDialog.dismiss();
                })
                .show();
    }

    public static void showSuccessDialogAndSetClassForIntent(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);
        prettyDialog
                .setTitle(message)
                .setIcon(R.drawable.ic_success)
                .addButton(context.getString(R.string.done), android.R.color.white, R.color.color_green, () -> {
                    NavUtils.navigateUpFromSameTask(((Activity) context));
                    Animatoo.animateSlideLeft(context);
                    if (prettyDialog.isShowing())
                        prettyDialog.dismiss();
                })
                .show();
    }

    public static void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
    public static void showSuccessDialogForVerfication(Context context, String message, String name) {
        PrettyDialog prettyDialog = new PrettyDialog(context);
        prettyDialog
                .setTitle(message)
                .setIcon(R.drawable.ic_success)
                .addButton(context.getString(R.string.done), android.R.color.white, R.color.color_green, () -> {
                    Intent intent = new Intent(context, ChooseHowItWork.class);
                    intent.putExtra(Constant.Username, name);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                    Animatoo.animateSplit(context);

                    if (prettyDialog.isShowing())
                        prettyDialog.dismiss();
                })
                .show();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        view = ((AppCompatActivity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void getErrorDependingOnResponse(Context context, String response) {
        String message = null;
        switch (response) {
            case "1":
                message = context.getString(R.string.response_one);
                break;
            case "2":
                message = context.getString(R.string.response_two);
                break;
            case "3":
                message = context.getString(R.string.response_three);
                break;
            case "4":
                message = context.getString(R.string.response_four);
                break;
            case "5":
                message = context.getString(R.string.response_five);
                break;
            case "6":
                message = context.getString(R.string.response_six);
                break;
            case "7":
                message = context.getString(R.string.response_seven);
                break;
            case "8":
                message = context.getString(R.string.response_eight);
                break;
            case "9":
                message = context.getString(R.string.response_nine);
                break;
            case "10":
                message = context.getString(R.string.response_ten);
                break;
            case "11":
                message = context.getString(R.string.response_eleven);
                break;
            case "12":
                message = context.getString(R.string.response_twelve);
                break;
            case "13":
                message = context.getString(R.string.response_thirteen);
                break;
            case "14":
                message = context.getString(R.string.response_fourteen);
                break;
            case "15":
                message = context.getString(R.string.response_fivteen);
                break;
            case "16":
                message = context.getString(R.string.response_sexteen);
                break;
            case "17":
                message = context.getString(R.string.response_sevteen);
                break;
            case "18":
                message = context.getString(R.string.response_eighteen);
                break;
            case "19":
                message = context.getString(R.string.response_ninteen);
                break;
            case "20":
                message = context.getString(R.string.response_twenty);
                break;
            case "21":
                message = context.getString(R.string.response_twenty_one);
                break;
            case "24":
                message = context.getString(R.string.response_twenty_four);
                break;
            case "25":
                message = context.getString(R.string.response_twenty_five);
                break;
            case "26":
                message = context.getString(R.string.response_twenty_sex);
                break;
            case "96":
                message = context.getString(R.string.response_ninty_sex);
                break;
            case "98":
                message = context.getString(R.string.response_ninty_eight);
                break;
            case "99":
                message = context.getString(R.string.response_ninty_nine);
                break;
            case "101":
                message = context.getString(R.string.response_one_hundred_and_one);
                break;
            default:
                message = context.getString(R.string.default_error);
                break;
        }

        Constant.showErrorDialog(context, message);

        Log.d("ERROR_TAG", "handleError: " + response);
    }

    public static void hideStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = activity.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


    public static Bitmap imageView2Bitmap(CircleImageView view) {
        Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
        return bitmap;
    }


}

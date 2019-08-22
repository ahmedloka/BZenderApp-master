package apps.sharabash.bzender.activities.signUp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import apps.sharabash.bzender.BuildConfig;
import apps.sharabash.bzender.Models.AddTenders.AllCitiesModel;
import apps.sharabash.bzender.Models.EditProfileResponse;
import apps.sharabash.bzender.Models.signUp.CountryCodeResponse;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyTextView;
import apps.sharabash.bzender.activities.AddTender.AddTinderInterface;
import apps.sharabash.bzender.activities.AddTender.AddTinderPresenter;
import apps.sharabash.bzender.activities.editProfile.EditProfileInterface;
import apps.sharabash.bzender.adapters.filterAreaAdapter;
import apps.sharabash.bzender.adapters.filterAreaModelRecycler;
import de.hdodenhof.circleimageview.CircleImageView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static apps.sharabash.bzender.Utills.Constant.convertToBase64;

@RuntimePermissions
public class SignUp extends AppCompatActivity implements View.OnClickListener, EditProfileInterface, AddTinderInterface, SignUpInterface {

    private static final String TAG = "Sign Up";
    private static final int CHOOSE_IMAGE_FROM_GALLERY = 1;
    private static final int CAPTURE_IMAGE = 2;
    private final ArrayList<filterAreaModelRecycler> DialogList = new ArrayList<>();
    private EditText mETFullName;
    private EditText mETEmailAddress;
    private EditText password;
    private EditText mETConfirmPassword;
    private MyTextView address;
    private EditText mETPhoneNumber;
    private SignUpPresenter signUpPresenter;
    private RecyclerView DialogRecyclerView;
    private filterAreaAdapter filterAreaAdapter1;
    private String cityId = "-1";
    private String countryCode = "-1";
    private String language;
    private MyTextView mTxtCountryCode;
    private CircleImageView imgProfile;
    private List<CountryCodeResponse> getAllCountiresCodes;
    private List<AllCitiesModel> getAllCitiesForDialog;
    private String mCurrentPhotoPath;
    private Bitmap bitmap;
    private Uri mProfileImageUri;
    private String base64;
    private ScrollView mImgBg;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideLeft(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        initViews();
    }

    private void initViews() {

        mImgBg = findViewById(R.id.img_bg);
        mImgBg.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(SignUp.this , v);
            return true;
        });

        AVLoadingIndicatorView mAviSignUp = findViewById(R.id.avi);

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());

        AddTinderPresenter addTinderPresenter = new AddTinderPresenter(this, this);
        addTinderPresenter.getAllCities();

        mTxtCountryCode = findViewById(R.id.country_code);
        mTxtCountryCode.setOnClickListener(this);

        mETFullName = findViewById(R.id.user_name);
        mETEmailAddress = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
        mETConfirmPassword = findViewById(R.id.confirm_password);

        address = findViewById(R.id.address);
        address.setOnClickListener(this);

        mETPhoneNumber = findViewById(R.id.phone_number);
        Button mBtnSignUp = findViewById(R.id.mBtnSignUp);
        imgProfile = findViewById(R.id.img_profile);
        imgProfile.setOnClickListener(this);

        CircleImageView imgAdd = findViewById(R.id.img_add);
        imgAdd.setOnClickListener(this);

        signUpPresenter = new SignUpPresenter(this, this);
        signUpPresenter.getAllCountriesCode();

        mBtnSignUp.setOnClickListener(this);
    }

    @Override
    public void getEditProfileData(EditProfileResponse editProfileModel) {
        Log.d(TAG, "inActivity: " + editProfileModel.toString());
    }

    @Override
    public void getAllCities(List<AllCitiesModel> getAllCities) {
        Log.d(TAG, "getAllCities: " + getAllCities.get(0).toString());
        getAllCitiesForDialog = getAllCities;
    }

    private void shwoDialogForCities() {
        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(this);
        View mview = getLayoutInflater().inflate(R.layout.area_dialog, null);
        TextView header = mview.findViewById(R.id.DialogHeader);
        TextView All = mview.findViewById(R.id.All);
        All.setVisibility(View.GONE);
        if (language.equals("ar")) {
            header.setText("اختر المحافظة");
        } else {
            header.setText("Select City");
        }
        DialogRecyclerView = mview.findViewById(R.id.Recycler_Dialog_cities);
        filterAreaAdapter1 = new filterAreaAdapter(DialogList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false);

        DialogRecyclerView.setLayoutManager(linearLayoutManager);
        DialogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initionilizationOFCities();
        DialogRecyclerView.setAdapter(filterAreaAdapter1);
        builder1.setView(mview);
        dialog1 = builder1.create();
        Window window = dialog1.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog1.show();
        filterAreaAdapter1.setOnItemClickListener(v1 -> {
            int position = DialogRecyclerView.getChildAdapterPosition(v1);
            String position1 = (String.valueOf(position));

            cityId = getAllCitiesForDialog.get(position).getId();
            address.setText(DialogList.get(position).CityName);


            dialog1.dismiss();
        });

    }

    private void initionilizationOFCities() {
        DialogList.clear();
        for (int k = 0; k < getAllCitiesForDialog.size(); k++) {
            if (language.equals("ar")) {
                DialogList.add(new filterAreaModelRecycler(
                        getAllCitiesForDialog.get(k).getArabicName()
                        , ""
                        , -1
                        , Integer.parseInt(getAllCitiesForDialog.get(k).getId())));
            } else {
                DialogList.add(new filterAreaModelRecycler(
                        getAllCitiesForDialog.get(k).getEnglishName()
                        , ""
                        , -1
                        , Integer.parseInt(getAllCitiesForDialog.get(k).getId())));
            }
        }
        filterAreaAdapter1.update(DialogList);


    }

    private void showDialogForCountriesCodes() {
        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(this);
        View mview = getLayoutInflater().inflate(R.layout.area_dialog, null);
        TextView header = mview.findViewById(R.id.DialogHeader);
        TextView All = mview.findViewById(R.id.All);
        All.setVisibility(View.GONE);
        if (language.equals("ar")) {
            header.setText("اختر كود المحافظة");
        } else {
            header.setText("Select country code");
        }
        DialogRecyclerView = mview.findViewById(R.id.Recycler_Dialog_cities);
        filterAreaAdapter1 = new filterAreaAdapter(DialogList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false);

        DialogRecyclerView.setLayoutManager(linearLayoutManager);
        DialogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initionilizationCodes();
        DialogRecyclerView.setAdapter(filterAreaAdapter1);
        builder1.setView(mview);
        dialog1 = builder1.create();
        Window window = dialog1.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog1.show();
        filterAreaAdapter1.setOnItemClickListener(v1 -> {
            int position = DialogRecyclerView.getChildAdapterPosition(v1);
            String position1 = (String.valueOf(position));

            countryCode = getAllCountiresCodes.get(position).getId();
            mTxtCountryCode.setText(getAllCountiresCodes.get(position).getCountryCode());


            dialog1.dismiss();
        });

    }

    private void initionilizationCodes() {
        DialogList.clear();
        for (int k = 0; k < getAllCountiresCodes.size(); k++) {
            if (language.equals("ar")) {
                DialogList.add(new filterAreaModelRecycler(
                        getAllCountiresCodes.get(k).getCountryArabicName()
                        , ""
                        , -1
                        , Integer.parseInt(getAllCountiresCodes.get(k).getId())));
            } else {
                DialogList.add(new filterAreaModelRecycler(
                        getAllCountiresCodes.get(k).getCountryEnglishName()
                        , ""
                        , -1
                        , Integer.parseInt(getAllCountiresCodes.get(k).getId())));
            }
        }
        filterAreaAdapter1.update(DialogList);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE_FROM_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {


            Uri selectedImage = data.getData();


            Glide.with(this)
                    .asBitmap()
                    .load(selectedImage)
                    .apply(new RequestOptions().override(600, 200))
                    .placeholder(R.drawable.edit_img)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            imgProfile.setImageBitmap(resource);
                            convertToBase64(resource);
                            base64 = convertToBase64(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            imgProfile.setImageResource(R.drawable.edit_img);

                        }
                    });

        }

        if (requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK) {

            // Show the thumbnail on ImageView
            Uri imageUri = Uri.parse(mCurrentPhotoPath);
            File file = new File(imageUri.getPath());
            try {
                InputStream ims = new FileInputStream(file);


                Glide.with(this)
                        .asBitmap()
                        .apply(new RequestOptions().override(600, 200))
                        .load(mCurrentPhotoPath)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                imgProfile.setImageBitmap(resource);
                                convertToBase64(resource);
                                base64 = convertToBase64(resource);
                                galleryAddPic();
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                imgProfile.setImageResource(R.drawable.edit_img);
                            }
                        });

            } catch (FileNotFoundException e) {
                return;
            }

            // ScanFile so it will be appeared on Gallery
            MediaScannerConnection.scanFile(SignUp.this,
                    new String[]{imageUri.getPath()}, null,
                    (path, uri) -> {
                    });

        }


    }

    private void showImageChooser() {

        final String[] options = {getString(R.string.gellery), getString(R.string.camera)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.choose_an_img))
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        Log.d("GALLERY", "GALLERY: ");
                        SignUpPermissionsDispatcher.startGALLERYWithPermissionCheck(SignUp.this);
                    } else if (which == 1) {
                        ///// CAMERA
                        SignUpPermissionsDispatcher.startCameraWithPermissionCheck(SignUp.this);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    @Override
    public void getAllCodes(List<CountryCodeResponse> countryCodeResponseList) {
        getAllCountiresCodes = countryCodeResponseList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address:
                shwoDialogForCities();
                break;
            case R.id.country_code:
                showDialogForCountriesCodes();
                break;
            case R.id.img_profile:
                showImageChooser();
                break;
            case R.id.img_add:
                showImageChooser();
                break;
            case R.id.mBtnSignUp:
                Log.d(TAG, "email: " + mETEmailAddress.getText().toString());
                Log.d(TAG, "phone: " + mETPhoneNumber.getText().toString());
                Log.d(TAG, "pass: " + password.getText().toString());
                Log.d(TAG, "pass: " + mETConfirmPassword.getText().toString());
                Log.d(TAG, "img: " + base64);
                Log.d(TAG, "city: " + cityId);
                Log.d(TAG, "country: " + countryCode);

                try {
                    if (base64.length() != 0) {
                        Constant.OLD_BASE64 = base64;
                    }
                    signUpPresenter.validationSignUp(mETEmailAddress.getText().toString(),
                            mETPhoneNumber.getText().toString(), password.getText().toString(),
                            mETFullName.getText().toString(), cityId, countryCode, mETConfirmPassword.getText().toString(), base64);
                } catch (NullPointerException e) {
                    signUpPresenter.validationSignUp(mETEmailAddress.getText().toString(),
                            mETPhoneNumber.getText().toString(), password.getText().toString(),
                            mETFullName.getText().toString(), cityId, countryCode, mETConfirmPassword.getText().toString(), "");
                }
                break;
        }
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    void startCamera() {
        try {
            dispatchTakePictureIntent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////////////
    // GALLERY //
    ////////////

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    void startGALLERY() {
        pickFromGallery();
    }

    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        // Launching the Intent
        startActivityForResult(intent, CHOOSE_IMAGE_FROM_GALLERY);

    }

    private void dispatchTakePictureIntent() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                return;
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        createImageFile());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAPTURE_IMAGE);
            }
        }
    }

    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    void showRationaleForCamera(final PermissionRequest request) {
        new android.app.AlertDialog.Builder(this)
                .setMessage("Access to External Storage is required")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);

        Uri contentUri = null;
        try {
            contentUri = FileProvider.getUriForFile(this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    createImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SignUpPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


}

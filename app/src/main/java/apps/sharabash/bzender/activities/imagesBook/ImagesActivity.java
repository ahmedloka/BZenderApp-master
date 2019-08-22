package apps.sharabash.bzender.activities.imagesBook;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.support.v4.app.NavUtils;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import apps.sharabash.bzender.BuildConfig;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.activities.Home.Home;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static apps.sharabash.bzender.Utills.Constant.convertToBase64;

@RuntimePermissions
public class ImagesActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSE_IMAGE_FROM_GALLERY = 1;
    private static final int CAPTURE_IMAGE = 2;
    private AppCompatImageView imgOne;
    private AppCompatImageView imgTwo;
    private AppCompatImageView imgThree;
    private AppCompatImageView imgFour;
    private AppCompatImageView imgFive;
    private AppCompatImageView imgSix;
    private String imageBASE46;
    private String mCurrentPhotoPath;
    private Uri mProfileImageUri;

    private boolean oneSelected = false;
    private boolean twoSelected = false;
    private boolean threeSelected = false;
    private boolean fourSelected = false;
    private boolean fiveSelected = false;
    private boolean sixSelected = false;


    private String id, type;

    private ImagesPresenter imagesPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        imagesPresenter = new ImagesPresenter(this);

        initViews();


        id = getIntent().getStringExtra(Constant.BOOKING_ID);
        Log.d("IDID_BOOKING", "onCreate: " + id);
        type = getIntent().getStringExtra(Constant.TYPE);
        Log.d("TYPE", "onCreate: " + type);


    }

    private void initViews() {

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });

        ButtonBook mBtnOk = findViewById(R.id.btn_ok);
        mBtnOk.setOnClickListener(this);

        imgOne = findViewById(R.id.img_one);
        imgOne.setOnClickListener(this);

        imgTwo = findViewById(R.id.img_two);
        imgTwo.setOnClickListener(this);

        imgThree = findViewById(R.id.img_three);
        imgThree.setOnClickListener(this);

        imgFour = findViewById(R.id.img_four);
        imgFour.setOnClickListener(this);

        imgFive = findViewById(R.id.img_five);
        imgFive.setOnClickListener(this);

        imgSix = findViewById(R.id.img_sex);
        imgSix.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_one:
                oneSelected = true;
                twoSelected = false;
                threeSelected = false;
                fourSelected = false;
                fiveSelected = false;
                sixSelected = false;

                showImageChooser();
                break;
            case R.id.img_two:
                oneSelected = false;
                twoSelected = true;
                threeSelected = false;
                fourSelected = false;
                fiveSelected = false;
                sixSelected = false;

                showImageChooser();

                break;
            case R.id.img_three:
                oneSelected = false;
                twoSelected = false;
                threeSelected = true;
                fourSelected = false;
                fiveSelected = false;
                sixSelected = false;

                showImageChooser();

                break;
            case R.id.img_four:
                oneSelected = false;
                twoSelected = false;
                threeSelected = false;
                fourSelected = true;
                fiveSelected = false;
                sixSelected = false;

                showImageChooser();

                break;
            case R.id.img_five:
                oneSelected = false;
                twoSelected = false;
                threeSelected = false;
                fourSelected = false;
                fiveSelected = true;
                sixSelected = false;

                showImageChooser();

                break;
            case R.id.img_sex:
                oneSelected = false;
                twoSelected = false;
                threeSelected = false;
                fourSelected = false;
                fiveSelected = false;
                sixSelected = true;

                showImageChooser();

                break;
            case R.id.btn_ok:
                Intent intent = new Intent(this, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                Animatoo.animateFade(this);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE_FROM_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {


            Uri selectedImage = data.getData();
            if (!selectedImage.toString().isEmpty()) {
                Log.d("NOT EMPTY", "onActivityResult: " + "NOT EDMPTY");
                if (oneSelected) {

                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgOne, selectedImage);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgOne, selectedImage);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                } else if (twoSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgTwo, selectedImage);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgTwo, selectedImage);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                } else if (threeSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgThree, selectedImage);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgThree, selectedImage);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                } else if (fourSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgFour, selectedImage);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgFour, selectedImage);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                } else if (fiveSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgFive, selectedImage);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgFive, selectedImage);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                } else if (sixSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgSix, selectedImage);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgSix, selectedImage);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                }
            } else {
                Log.d("IMG_BASE56GALLERY", "onClick: " + "There is no iamge selected");
                if (oneSelected) {
                    imgOne.setImageResource(R.drawable.img_bg_book);
                } else if (twoSelected) {
                    imgTwo.setImageResource(R.drawable.img_bg_book);
                } else if (threeSelected) {
                    imgThree.setImageResource(R.drawable.img_bg_book);
                } else if (fourSelected) {
                    imgFour.setImageResource(R.drawable.img_bg_book);
                } else if (fiveSelected) {
                    imgFive.setImageResource(R.drawable.img_bg_book);
                } else if (sixSelected) {
                    imgSix.setImageResource(R.drawable.img_bg_book);
                }


            }
        }

        if (requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            // Show the thumbnail on ImageView
            Uri imageUri = Uri.parse(mCurrentPhotoPath);

            if (!imageUri.toString().isEmpty()) {
                //imageBASE46 = Constant.convertToBase64(bitmap);
                //  Log.d("IMG_BASE56GALLERY", "onClick: " + imageBASE46);
                if (oneSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgOne, imageUri);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgOne, imageUri);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }

                } else if (twoSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgTwo, imageUri);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgTwo, imageUri);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }

                } else if (threeSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgThree, imageUri);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgThree, imageUri);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }

                } else if (fourSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgFour, imageUri);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgFour, imageUri);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                } else if (fiveSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgFive, imageUri);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgFive, imageUri);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                } else if (sixSelected) {
                    if (type.equals(Constant.CAR)) {
                        setImageWithGlide(this, imgSix, imageUri);
                        imagesPresenter.uploadCarImage(id, imageBASE46);
                    } else {
                        setImageWithGlide(this, imgSix, imageUri);
                        imagesPresenter.uploadElectricalImage(id, imageBASE46);
                    }
                }


            } else {
                Log.d("IMG_BASE56GALLERY", "onClick: " + "There is no iamge selected");
                if (oneSelected) {
                    imgOne.setImageResource(R.drawable.img_bg_book);
                } else if (twoSelected) {
                    imgTwo.setImageResource(R.drawable.img_bg_book);
                } else if (threeSelected) {
                    imgThree.setImageResource(R.drawable.img_bg_book);
                } else if (fourSelected) {
                    imgFour.setImageResource(R.drawable.img_bg_book);
                } else if (fiveSelected) {
                    imgFive.setImageResource(R.drawable.img_bg_book);
                } else if (sixSelected) {
                    imgSix.setImageResource(R.drawable.img_bg_book);
                }


            }


            // ScanFile so it will be appeared on Gallery
            MediaScannerConnection.scanFile(this,
                    new String[]{imageUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
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
                        ImagesActivityPermissionsDispatcher.startGALLERYWithPermissionCheck(this);
                    } else if (which == 1) {
                        ///// CAMERA
                        ImagesActivityPermissionsDispatcher.startCameraWithPermissionCheck(this);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    void startCamera() {
        try {
            dispatchTakePictureIntent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                .setMessage(getString(R.string.access_to_external_required))
                .setPositiveButton(getString(R.string.allow), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton(getString(R.string.deny), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

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
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
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
        ImagesActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void setImageWithGlide(Activity activity, AppCompatImageView img, Uri selectedImg) {
        Glide.with(activity)
                .asBitmap()
                .load(selectedImg)
                .apply(new RequestOptions().centerCrop())
                .placeholder(R.drawable.img_bg_book)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        img.setImageBitmap(resource);
                        imageBASE46 = convertToBase64(resource);
                        Log.d("NOT EMPTY", "onResourceReady:1 " + "NOT EMPTY");

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        img.setImageResource(R.drawable.img_bg_book);

                    }
                });
    }

}

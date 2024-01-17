package com.android.nunuwa_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.nunuwa_app.Logic.logicToUIs;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Sign_out_Activity extends AppCompatActivity {

    Bitmap bitmap;
    RelativeLayout container;
    public static final int CAMERA_CODE = 12;
    ImageView imgUser;
    public static final int GALLERY_CODE = 112;
    Uri filePath = null;

    TextView toLogin, toRegiste;
    RelativeLayout relForL, relForReg;
    ImageView shopCrt;
    TextView shopNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        container = (RelativeLayout) findViewById(R.id.container);

        toLogin = findViewById(R.id.toLogin);
        toRegiste = findViewById(R.id.toRegister);
        relForL = findViewById(R.id.relForLogin);
        relForReg = findViewById(R.id.relForReg);


        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);
    }


    public void toMenu(View view) {
        logicToUIs.conerMenu(Sign_out_Activity.this, view);
    }

    public void BckBtn(View view) {
        onBackPressed();
    }

    public void toLog() {
        relForReg.setVisibility(View.GONE);
        relForL.setVisibility(View.VISIBLE);
        toLogin.setEnabled(false);
        toRegiste.setEnabled(true);
    }
    public void toLogin(View view) {
        toLog();
    }

    public void toReg() {
        relForReg.setVisibility(View.VISIBLE);
        relForL.setVisibility(View.GONE);
        toRegiste.setEnabled(false);
        toLogin.setEnabled(true);
    }
    public void toRegister(View view) {
        toReg();
    }

    public void ToCamera(View view) {

        Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCam, CAMERA_CODE);
    }

    public void ToGallery(View view) {

        Intent pictureActionIntent = null;

        pictureActionIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(
                pictureActionIntent,
                GALLERY_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK && data != null) {

            try {
                bitmap = (Bitmap) data.getExtras().get("data");
                imgUser.setImageBitmap(bitmap);
                imgUser.setVisibility(View.VISIBLE);
//
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                if (bitmap != null){
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    imgUser.setImageBitmap(bitmap);
                    imgUser.setVisibility(View.VISIBLE);

                } else {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    imgUser.setImageBitmap(bitmap);
                    imgUser.setVisibility(View.VISIBLE);

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public void signoutbtn(View view) {

    }

}

package com.android.nunuwa_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.nunuwa_app.Logic.logicToUIs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ContactUs extends AppCompatActivity {

    Bitmap bitmap;
    public static final int CAMERA_CODE = 12;
    ImageView imgUser;
    public static final int GALLERY_CODE = 112;
    Uri filePath = null;
    ImageView shopCrt;
    TextView shopNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);

        imgUser = findViewById(R.id.imgUser);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        Menu menu = navigation.getMenu();
//        MenuItem menuItem = menu.getItem(2);
//        menuItem.setChecked(true);

    }

    public void toMenu(View view) {
        logicToUIs.conerMenu(ContactUs.this, view);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.Home:
                    startActivity(new Intent(ContactUs.this, MainActivity.class));

                    return true;

                case R.id.fvorite:
                    startActivity(new Intent(ContactUs.this, ContactUs.class));

                    return true;

                case R.id.account:
                    startActivity(new Intent(ContactUs.this, my_account.class));
                    return true;

                case R.id.cart:
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Intent intentMarket = new Intent(ContactUs.this, MyCart.class);
                            startActivity(intentMarket);
                            finish();
                        }
                    }, 600);
                    return true;

            }
            return false;
        }

    };

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

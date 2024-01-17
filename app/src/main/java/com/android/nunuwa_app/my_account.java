package com.android.nunuwa_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nunuwa_app.Logic.logicToUIs;
import com.android.nunuwa_app.MyDpter.Ct_dpter;
import com.android.nunuwa_app.MyDpter.Item_Dter;
import com.android.nunuwa_app.MyDpter.Recycler_Item_front;
import com.android.nunuwa_app.MyDpter.Recycler_related;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.BubbleToggleView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class my_account extends AppCompatActivity {


    Bitmap bitmap;
    public static final int CAMERA_CODE = 12;
    ImageView imgUser;
    public static final int GALLERY_CODE = 112;

    Recycler_Item_front mAdapterH;
    List<Item_Dter.items_product> lstItem;
    Uri filePath = null;
    LinearLayoutManager layoutManager3, layoutManager4;
    ImageView shopCrt;
    TextView shopNum;
    BubbleToggleView my_home, c_myccout, myorders, c_item_rest,c_search;
    BubbleNavigationLinearView bubbleNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        imgUser = findViewById(R.id.imgUser);
        bubbleNavigation = findViewById(R.id.bot_navigation_Lin);

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
////        BottomNavigationViewHelper.disableShiftMode(navigation);
//        Menu menu = navigation.getMenu();
//        MenuItem menuItem = menu.getItem(3);
//        menuItem.setChecked(true);


        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);

        layoutManager3 = new LinearLayoutManager(my_account.this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager4 = new LinearLayoutManager(my_account.this, LinearLayoutManager.HORIZONTAL, false);


        my_home = findViewById(R.id.myhome);
        c_myccout = findViewById(R.id.c_myccout);
        myorders = findViewById(R.id.myorders);
        c_item_rest = findViewById(R.id.c_item_rest);
        c_search = findViewById(R.id.c_search);


        c_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(my_account.this, Search_ui.class));
            }
        });


        my_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(my_account.this, MainActivity.class));
                //my_home.toggle();
            }
        });

        c_myccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(my_account.this, my_account.class));
                c_myccout.toggle();
            }
        });

        c_item_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(my_account.this, Ct_items.class));
                //c_item_rest.toggle();
            }
        });

        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(my_account.this, MyCart.class));
            }
        });

        bubbleNavigation.setCurrentActiveItem(0);

        new myTaskHWHorizontl().execute();
        new myTaskHWHorizontlPurch().execute();

    }

    public void toMenu(View view) {
        logicToUIs.conerMenu(my_account.this, view);
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


    public class myTaskHWHorizontl extends AsyncTask<Void, List, String> {

        Recycler_related mAdapter;
        private int count=0;
        @Override
        protected void onPreExecute() {
            lstItem = new ArrayList<>();
            mAdapter = new Recycler_related(my_account.this, lstItem);
//            setProgressBarIndeterminate(false);
//            setProgressBarVisibility(true);

        }

        @Override
        protected String doInBackground(Void... voids) {

//
//            stringRequest = new StringRequest(com.android.volley.Request.Method.GET, urlStyleHS, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {

            try {

//                lstItem.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "120$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Iron","Ssenoga", "", "100$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Plstics","Ssenoga", "", "34$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Vehicules ger","Ssenoga", "", "21$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "12$", R.drawable.img3));
//                publishProgress(lstItem);


            } catch (Exception e) {
                Toast.makeText(my_account.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {
            RecyclerView myrx4 = (RecyclerView) findViewById(R.id.recyclerRequests);
            myrx4.setNestedScrollingEnabled(false);
            myrx4.setLayoutManager(layoutManager3);
            myrx4.setAdapter(mAdapter);


//            count ++;
//            setProgress((int)(((double)count/lstItem.size())*10000));

        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
        }

    }


    public class myTaskHWHorizontlPurch extends AsyncTask<Void, List, String> {

        Recycler_related mAdapter;
        private int count=0;
        @Override
        protected void onPreExecute() {
            lstItem = new ArrayList<>();
            mAdapter = new Recycler_related(my_account.this, lstItem);
//            setProgressBarIndeterminate(false);
//            setProgressBarVisibility(true);

        }

        @Override
        protected String doInBackground(Void... voids) {

//
//            stringRequest = new StringRequest(com.android.volley.Request.Method.GET, urlStyleHS, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {

            try {
//                lstItem.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "120$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Iron","Ssenoga", "", "100$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Plstics","Ssenoga", "", "34$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Vehicules ger","Ssenoga", "", "21$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "12$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "120$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Iron","Ssenoga", "", "100$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Plstics","Ssenoga", "", "34$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Vehicules ger","Ssenoga", "", "21$", R.drawable.img3));
//                lstItem.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "12$", R.drawable.img3));
//                publishProgress(lstItem);


            } catch (Exception e) {
                Toast.makeText(my_account.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {
            RecyclerView RelMyPurch = (RecyclerView) findViewById(R.id.recyclerPurchase);
            RelMyPurch.setNestedScrollingEnabled(false);
            RelMyPurch.setLayoutManager(layoutManager4);
            Recycler_related mAdapterHPurch = new Recycler_related(my_account.this, lstItem);
            RelMyPurch.setAdapter(mAdapterHPurch);


//            count ++;
//            setProgress((int)(((double)count/lstItem.size())*10000));

        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
        }

    }


}

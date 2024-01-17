package com.android.nunuwa_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nunuwa_app.Logic.logicToUIs;
import com.android.nunuwa_app.MyDpter.Item_Dter;
import com.android.nunuwa_app.MyDpter.Recycler_related;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class item_desc extends AppCompatActivity {

    List<Item_Dter.items_product> lstREcom, lstREcom2;
    LinearLayoutManager layoutManagerREcom, layoutManagerREcom2;
    RecyclerView REc_recent, Rec_rel;
    ImageView shopCrt;
    TextView shopNum;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.im2, R.drawable.img4, R.drawable.img7, R.drawable.img3, R.drawable.img5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_desc);

        lstREcom = new ArrayList<>();
        lstREcom2 = new ArrayList<>();
        layoutManagerREcom = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerREcom2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        Rec_rel = findViewById(R.id.recyclerRel);
        REc_recent = findViewById(R.id.recyclerRec);

        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(item_desc.this, "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });
        new myTask_ReL().execute();
        new myTask_REcent().execute();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void toMenu(View view) {
        logicToUIs.conerMenu(item_desc.this, view);
    }


    class myTask_ReL extends AsyncTask<Void, List, String> {

        Recycler_related mAdapter_Recom;
        private int count=0;
        @Override
        protected void onPreExecute() {
            mAdapter_Recom = new Recycler_related(item_desc.this, lstREcom);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
//                lstREcom.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "120$", R.drawable.img8));
//                lstREcom.add(new Item_Dter.items_product("Iron","Ssenoga", "", "100$", R.drawable.img3));
//                lstREcom.add(new Item_Dter.items_product("Plstics","Ssenoga", "", "34$", R.drawable.im2));
//                lstREcom.add(new Item_Dter.items_product("Vehicules ger","Ssenoga", "", "21$", R.drawable.img5));
//                lstREcom.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "12$", R.drawable.img9));
//                publishProgress(lstREcom);

            } catch (Exception e) {
//                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            Rec_rel.setNestedScrollingEnabled(false);
            Rec_rel.setLayoutManager(layoutManagerREcom);
            Rec_rel.setAdapter(mAdapter_Recom);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
//            Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
        }
    }


    class myTask_REcent extends AsyncTask<Void, List, String> {

        Recycler_related mAdapter_Recom;
        private int count=0;
        @Override
        protected void onPreExecute() {
            mAdapter_Recom = new Recycler_related(item_desc.this, lstREcom2);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
//                lstREcom2.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "120$", R.drawable.img8));
//                lstREcom2.add(new Item_Dter.items_product("Iron","Ssenoga", "", "100$", R.drawable.img3));
//                lstREcom2.add(new Item_Dter.items_product("Plstics","Ssenoga", "", "34$", R.drawable.im2));
//                lstREcom2.add(new Item_Dter.items_product("Vehicules ger","Ssenoga", "", "21$", R.drawable.img5));
//                lstREcom2.add(new Item_Dter.items_product("Electronics","Ssenoga", "", "12$", R.drawable.img9));
//                publishProgress(lstREcom2);

            } catch (Exception e) {
//                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            REc_recent.setNestedScrollingEnabled(false);
            REc_recent.setLayoutManager(layoutManagerREcom2);
            REc_recent.setAdapter(mAdapter_Recom);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
//            Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
        }
    }


}

package com.android.nunuwa_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
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
import com.android.nunuwa_app.MyDpter.Recycler_Cart;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.BubbleToggleView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MyCart extends AppCompatActivity {

    RecyclerView myrx3;
    LinearLayoutManager layoutManager3;
    ImageView shopCrt;
    TextView shopNum;
    BubbleNavigationLinearView bubbleNavigation;
    BubbleToggleView my_home, c_myccout, myorders, c_item_rest, c_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);



//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        Menu menu = navigation.getMenu();
//        MenuItem menuItem = menu.getItem(1);
//        menuItem.setChecked(true);
        layoutManager3 = new LinearLayoutManager(MyCart.this, LinearLayoutManager.VERTICAL, false);

        bubbleNavigation = findViewById(R.id.bot_navigation_Lin);

        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);

        my_home = findViewById(R.id.myhome);
        c_myccout = findViewById(R.id.c_myccout);
        myorders = findViewById(R.id.myorders);
        c_item_rest = findViewById(R.id.c_item_rest);
        c_search = findViewById(R.id.c_search);


        c_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCart.this, Search_ui.class));
            }
        });

        my_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCart.this, MainActivity.class));
                //my_home.toggle();
            }
        });

        c_myccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCart.this, my_account.class));
//                c_myccout.toggle();
            }
        });

        c_item_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCart.this, Ct_items.class));
                //c_item_rest.toggle();
            }
        });

        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCart.this, MyCart.class));
                myorders.toggle();
            }
        });


        bubbleNavigation.setCurrentActiveItem(2);
        new myTaskHW().execute();

    }


    public void toMenu(View view) {
        logicToUIs.conerMenu(MyCart.this, view);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.Home:
                    startActivity(new Intent(MyCart.this, MainActivity.class));

                    return true;

                case R.id.fvorite:
                    startActivity(new Intent(MyCart.this, ContactUs.class));

                    return true;

                case R.id.account:
                    startActivity(new Intent(MyCart.this, my_account.class));
                    return true;

                case R.id.cart:
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Intent intentMarket = new Intent(MyCart.this, MyCart.class);
                            startActivity(intentMarket);
                            finish();
                        }
                    }, 600);
                    return true;

            }
            return false;
        }

    };




    class myTaskHW extends AsyncTask<Void, List, String> {

        Recycler_Cart mAdapter;
        private int count=0;
        List<Ct_dpter.items> lstItem;
        @Override
        protected void onPreExecute() {
            lstItem = new ArrayList<>();

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

                lstItem.add(new Ct_dpter.items("How to dwell in the Holy Spirit","$4.50", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.im2));
                lstItem.add(new Ct_dpter.items("How to dwell in the Holy Spirit","$4.50", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.im2));
                lstItem.add(new Ct_dpter.items("How to dwell in the Holy Spirit","$4.50", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.im2));
                lstItem.add(new Ct_dpter.items("How to dwell in the Holy Spirit","$4.50", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.im2));
                lstItem.add(new Ct_dpter.items("How to dwell in the Holy Spirit","$4.50", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.im2));
                lstItem.add(new Ct_dpter.items("How to dwell in the Holy Spirit","$4.50", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.im2));
                lstItem.add(new Ct_dpter.items("How to dwell in the Holy Spirit","$4.50", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.im2));


                publishProgress(lstItem);


            } catch (Exception e) {
                Toast.makeText(MyCart.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            myrx3 = (RecyclerView) findViewById(R.id.recyclerSpearItem);
            myrx3.setNestedScrollingEnabled(false);
            myrx3.setLayoutManager(layoutManager3);
            mAdapter = new Recycler_Cart(MyCart.this, lstItem);
            myrx3.setAdapter(mAdapter);


//            count ++;
//            setProgress((int)(((double)count/lstItem.size())*10000));

        }

        @Override
        protected void onPostExecute(String result) {

            setProgressBarVisibility(false);
        }

    }

}

package com.android.nunuwa_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nunuwa_app.Logic.MyData;
import com.android.nunuwa_app.Logic.logicToUIs;
import com.android.nunuwa_app.MyDpter.Ct_dpter;
import com.android.nunuwa_app.MyDpter.Item_Dter;
import com.android.nunuwa_app.MyDpter.Recycler_related;
import com.android.nunuwa_app.MyDpter.sttus_Dpter;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.BubbleToggleView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.squareup.picasso.Downloader;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alert, alert1;
    List<Ct_dpter.items> lstItem, lstItemStus;
    List<Item_Dter.items_product> lstREcom;
    List<Item_Dter.items_product>  lstItemPro, lstItemOther;
    LinearLayoutManager layoutManager3, layoutManager4, layoutManagerItem, layoutManagerREcom,layoutManagerOther;
    RecyclerView myrx3, relSttus, relItems, relOtherItems, myrxRecom;
    Item_Dter mAdapterItem, item_dterOther;
    BubbleNavigationLinearView bubbleNavigation;
    BubbleToggleView my_home, c_myccout, myorders, c_item_rest, c_search;
    ImageView shopCrt;
    TextView shopNum;
    Item_Dter.items_product listiTEMpRO2;
    CarouselView carouselView;
    RequestQueue requestQueue;
    int[] sampleImages = {R.drawable.im2, R.drawable.img4, R.drawable.img7, R.drawable.img3, R.drawable.img5};
    List<Item_Dter.items_product> lstProNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstREcom = new ArrayList<>();
        lstProNew = new ArrayList<>();
        myrxRecom = findViewById(R.id.rec_Recom);
        relOtherItems = findViewById(R.id.rec_Other_Item);
        relSttus = findViewById(R.id.relSttus);
        relItems = findViewById(R.id.rec_Item);
        myrx3 = findViewById(R.id.rec_Ct);
        lstREcom = new ArrayList<>();
        lstItemStus = new ArrayList<>();
        lstItem = new ArrayList<>();
        lstItemPro = new ArrayList<>();
        lstItemOther = new ArrayList<>();
        item_dterOther = new Item_Dter(MainActivity.this, lstItemOther);
        mAdapterItem = new Item_Dter(MainActivity.this, lstProNew);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);

        bubbleNavigation = findViewById(R.id.bot_navigation_Lin);

        my_home = findViewById(R.id.myhome);
        c_myccout = findViewById(R.id.c_myccout);
        myorders = findViewById(R.id.myorders);
        c_item_rest = findViewById(R.id.c_item_rest);
        c_search = findViewById(R.id.c_search);


        c_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Search_ui.class));
            }
        });

        my_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                my_home.toggle();
            }
        });

        c_myccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, my_account.class));
                //c_myccout.toggle();
            }
        });

        c_item_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ct_items.class));
                //c_item_rest.toggle();
            }
        });

        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyCart.class));
                //myorders.toggle();
            }
        });


        bubbleNavigation.setCurrentActiveItem(1);

        layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerItem = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManagerREcom = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerOther = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new myTask_Other_Item().execute();
                new myTask_Ct().execute();
                new myTask_Sttus().execute();
                new myTask_Item2().execute();
                new myTask_Recom().execute();
            }
        }, 1000);


        try {
            //logicToUIs.BottomLogic(MainActivity.this, bubbleNavigation, 1);
        }catch (Exception e){
            Log.e("error: ", e.toString());
        }

        alert1 = new AlertDialog.Builder(MainActivity.this);
//        try {
//            SharedPreferences prfs = getSharedPreferences("MyPrefsfile", MODE_PRIVATE);
//            email_user = prfs.getString("email", "My Account");
//            User_id = prfs.getString("user_id", "No id");
//
//            if (!email_user.equals("My Account")) {
//                String username = prfs.getString("username", "username");
//            } else {
//
//            }
//        }catch (Exception e) {
//            //Toast.makeText(this, "error " +e, Toast.LENGTH_SHORT).show();
//        }
        LayoutInflater inflater1 = LayoutInflater.from(MainActivity.this);
        View alertLayout = inflater1.inflate(R.layout.stting_popup, null);


        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                MainActivity.super.onBackPressed();
            }
        });

        final AlertDialog dialog = alert.create();
        dialog.setCancelable(true);
        //dialog.show();


        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


    public void toCt(View view) {
        startActivity(new Intent(MainActivity.this, Ct_items.class));
    }

    public void toItemS(View view) {
        startActivity(new Intent(MainActivity.this, ItemsList.class));
    }

    public void toMenu(View view) {
        logicToUIs.conerMenu(MainActivity.this, view);
    }

    class myTask_Sttus extends AsyncTask<Void, List, String> {

        sttus_Dpter mAdapter;
        private int count=0;

//         "id": 7,
//                 "code": null,
//                 "name": "Electroniques",
//                 "slug": "electroniques",
//                 "display_mode": "products_and_description",
//                 "description": "<p>Electroniques</p>",
//                 "meta_title": "Electroniques",
//                 "meta_description": "Electroniques",
//                 "meta_keywords": "Electroniques",
//                 "status": 1,
//                 "image_url": null,
//                 "additional": null,
//                 "created_at": "2020-11-01T16:43:52.000000Z",
//                 "updated_at": "2020-11-01T16:43:52.000000Z"
        @Override
        protected void onPreExecute() {

            mAdapter = new sttus_Dpter(MainActivity.this, lstItemStus);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                lstItemStus.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img8));
                lstItemStus.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img7));
                lstItemStus.add(new Ct_dpter.items("Cups","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img8));
                lstItemStus.add(new Ct_dpter.items("Shoes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img9));
                lstItemStus.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lstItemStus.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                publishProgress(lstItemStus);

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            relSttus.setNestedScrollingEnabled(false);
            relSttus.setLayoutManager(layoutManager4);
            relSttus.setAdapter(mAdapter);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
        }
    }

    class myTask_Ct extends AsyncTask<Void, List, String> {

        Ct_dpter mAdapter_Ct;
        private int count=0;
        @Override
        protected void onPreExecute() {

            mAdapter_Ct = new Ct_dpter(MainActivity.this, lstItem);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                lstItem.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lstItem.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                lstItem.add(new Ct_dpter.items("Cups","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lstItem.add(new Ct_dpter.items("Shoes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                lstItem.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lstItem.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                publishProgress(lstItem);

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            myrx3.setNestedScrollingEnabled(false);
            myrx3.setLayoutManager(layoutManager3);
            myrx3.setAdapter(mAdapter_Ct);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
//            Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
        }
    }

    class myTask_Item2 extends AsyncTask<Void, Integer, String> {


        private int count=0;
        @Override
        protected void onPreExecute() {

            //mAdapter_Recom = new Recycler_item(MainActivity.this, lstREcom);
        }

        @Override
        protected String doInBackground(Void... voids) {

//            String urlSpec = MyData.URL + "script/get_recent_post.php";
            //String urlSpec = MyData.URL + "descendant-categories?parent_id=3";
            String urlSpec = MyData.URL + "products?page=1";
            //final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlSpec , new Response.Listener<JSONArray>() {
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (urlSpec ,null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    final int numberOfItemsInResp = response.length();
                    for (int i = 0; i < numberOfItemsInResp; i++) {

                        JSONObject itemObject = null;
                        listiTEMpRO2 = new Item_Dter.items_product();
                        if (response.length() < 15) {
//                            ThereisMore = false;
//                            LodMoreTxt.setVisibility(View.GONE);
                        } else {
//                            LodMoreTxt.setVisibility(View.VISIBLE);
                        }
                        try {
                            JSONObject jsonObject1 = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject1.getJSONArray("data");
                            Log.d("Json response", "onResponse: "+jsonObject1.toString());
                            //Toast.makeText(MainActivity.this, "RESULT."+jsonObject1.toString(), Toast.LENGTH_SHORT).show();

                            itemObject = jsonArray.getJSONObject(i);

//                            "id": 3,
//                                    "type": "simple",
//                                    "name": "Lenovo IdeaPad Yoga 500 15 Hybrid (2-in-1) White",
//                                    "url_key": "lenovo-ideapad-yoga-500-15-hybrid-2-in-1-white",
//                                    "price": "600.0000",
//                                    "formated_price": "$600.00",
//        ...
//                            "sku": "5626",
//                                    "images": [
//                            {...}
//        ],
//                            "base_image": {...},
//                            "variants": [],
//                            "in_stock": true,
//                                    "reviews": {...},
//                            "is_saved": false,
//                                    "created_at": "2020-09-09 03:31:47",
//                                    "updated_at": "2

                            listiTEMpRO2.setItem_id(itemObject.getString("id"));
                            listiTEMpRO2.setItem_name(itemObject.getString("name"));
                            listiTEMpRO2.setItem_date(itemObject.getString("created_at"));
                            listiTEMpRO2.setItem_price(itemObject.getString("formated_price"));
                            //listiTEMpRO2.setItem_image(itemObject.getString("description"));

                            lstProNew.add(listiTEMpRO2);

                            publishProgress(i);

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Network Error! please check connection..."+e.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("error", e.toString());
                        }
                    }
                }
            },  new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(MainActivity.this, "Network Error! please check connection...", Toast.LENGTH_SHORT).show();
                        Log.e("error",error.toString());
                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(MainActivity.this, "Failed to load data...", Toast.LENGTH_SHORT).show();

                    } else if (error instanceof ServerError) {
                        Log.e("error", error.toString());
                        Toast.makeText(MainActivity.this, "Failed to get data, Server error..."+error.toString(), Toast.LENGTH_SHORT).show();

                    } else if (error instanceof NetworkError) {
                        Toast.makeText(MainActivity.this, "Network Error! please check connection...", Toast.LENGTH_SHORT).show();
                        Log.e("errorrrrrr",error.toString());
                    } else if (error instanceof ParseError) {
                        Toast.makeText(MainActivity.this, "Failed to get data, please try again..."+error.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No more result available..."+error.toString(), Toast.LENGTH_SHORT).show();
                        //noItem.setVisibility(View.VISIBLE);
                    }
                }
            });

            int socketTimeout = 30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(policy);
            requestQueue.add(jsonObjectRequest);
            return String.valueOf(jsonObjectRequest);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            relItems.setNestedScrollingEnabled(false);
            relItems.setLayoutManager(layoutManagerItem);
            relItems.setAdapter(mAdapterItem);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
//          Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
        }
    }

    class myTask_Item extends AsyncTask<Void, List, String> {


        //         "id": 7,
//                 "code": null,
//                 "name": "Electroniques",
//                 "slug": "electroniques",
//                 "display_mode": "products_and_description",
//                 "description": "<p>Electroniques</p>",
//                 "meta_title": "Electroniques",
//                 "meta_description": "Electroniques",
//                 "meta_keywords": "Electroniques",
//                 "status": 1,
//                 "image_url": null,
//                 "additional": null,
//                 "created_at": "2020-11-01T16:43:52.000000Z",
//                 "updated_at": "2020-11-01T16:43:52.000000Z"
        private int count=0;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
//                lstItemPro.add(new Item_Dter.items_product("Electronics","Electronics", "1", "130$", R.drawable.img8));
//                lstItemPro.add(new Item_Dter.items_product("Dippers","Electronics", "1", "220$", R.drawable.img3));
//                lstItemPro.add(new Item_Dter.items_product("PLstics","Electronics", "1", "120$", R.drawable.im2));
//                publishProgress(lstItemPro);

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            relItems.setNestedScrollingEnabled(false);
            relItems.setLayoutManager(layoutManagerItem);
            relItems.setAdapter(mAdapterItem);
        }

        @Override
        protected void onPostExecute(String result) {
//            setProgressBarVisibility(false);
        }
    }



    class myTask_Recom extends AsyncTask<Void, List, String> {

        Recycler_related mAdapter_Recom;
        private int count=0;
        @Override
        protected void onPreExecute() {

            mAdapter_Recom = new Recycler_related(MainActivity.this, lstREcom);
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

            myrxRecom.setNestedScrollingEnabled(false);
            myrxRecom.setLayoutManager(layoutManagerREcom);
            myrxRecom.setAdapter(mAdapter_Recom);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
//            Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
        }
    }

    class myTask_Other_Item extends AsyncTask<Void, List, String> {


        private int count=0;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

//                lstItemOther.add(new Item_Dter.items_product("","Electronics", "1", "170$", R.drawable.img7));
//                lstItemOther.add(new Item_Dter.items_product("","MY Clothes", "1", "110$", R.drawable.img9));
//                lstItemOther.add(new Item_Dter.items_product("","Electronics", "1", "100$", R.drawable.img3));
//                lstItemOther.add(new Item_Dter.items_product("","Electronics", "1", "30$", R.drawable.img8));
//
//                publishProgress(lstItemOther);

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            relOtherItems.setNestedScrollingEnabled(false);
            relOtherItems.setLayoutManager(layoutManagerOther);
            relOtherItems.setAdapter(item_dterOther);
        }

        @Override
        protected void onPostExecute(String result) {
//            setProgressBarVisibility(false);
        }
    }

}

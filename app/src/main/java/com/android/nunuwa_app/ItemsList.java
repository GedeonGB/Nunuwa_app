package com.android.nunuwa_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nunuwa_app.Logic.MyData;
import com.android.nunuwa_app.Logic.logicToUIs;
import com.android.nunuwa_app.MyDpter.Item_Dter;
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
import com.synnapps.carouselview.CarouselView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemsList extends AppCompatActivity {


    RecyclerView RecItems;
    LinearLayoutManager layoutManagerItem;
    Item_Dter mAdapterItem;
    ImageView shopCrt;
    TextView shopNum;
    Item_Dter.items_product listiTEMpRO2;
    RequestQueue requestQueue;
    List<Item_Dter.items_product> lstProNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        RecItems = findViewById(R.id.rec_Item);

        lstProNew = new ArrayList<>();
        //mAdapterItem = new Item_Dter(ItemsList.this, lstProNew);
        layoutManagerItem = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapterItem = new Item_Dter(ItemsList.this, lstProNew);


        requestQueue = Volley.newRequestQueue(ItemsList.this);
        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);
        new myTask_Item2().execute();

    }

    public void toMenu(View view) {
        logicToUIs.conerMenu(ItemsList.this, view);
    }



    class myTask_Item2 extends AsyncTask<Void, Integer, String> {



        private int count=0;

        @Override
        protected void onPreExecute() {
            listiTEMpRO2 = new Item_Dter.items_product();
            //mAdapter_Recom = new Recycler_item(ItemsList.this, lstREcom);
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
                        JSONObject itemObjectImg = null;


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

                            itemObject = jsonArray.getJSONObject(i);
                            //Toast.makeText(ItemsList.this, "RESULT.:"+numberOfItemsInResp, Toast.LENGTH_SHORT).show();

                            listiTEMpRO2.setItem_id(itemObject.getString("id"));
                            listiTEMpRO2.setItem_name(itemObject.getString("name"));
                            //listiTEMpRO2.setItem_date(itemObject.getString("created_at"));
                            listiTEMpRO2.setItem_price(itemObject.getString("formated_price"));

//                            JSONArray jsonArrayImg = itemObject.getJSONArray("image");
//                            itemObjectImg = jsonArrayImg.getJSONObject(i);
//                            listiTEMpRO2.setItem_image(itemObjectImg.getString("original_image_url"));

                            lstProNew.add(listiTEMpRO2);

                            publishProgress(i);

                        } catch (JSONException e) {
                            Toast.makeText(ItemsList.this, "Network Error! please check connection..."+e.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("error", e.toString());
                        }
                    }
                }
            },  new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(ItemsList.this, "Network Error! please check connection...", Toast.LENGTH_SHORT).show();
                        Log.e("error",error.toString());
                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(ItemsList.this, "Failed to load data...", Toast.LENGTH_SHORT).show();

                    } else if (error instanceof ServerError) {
                        Log.e("error", error.toString());
                        Toast.makeText(ItemsList.this, "Failed to get data, Server error..."+error.toString(), Toast.LENGTH_SHORT).show();

                    } else if (error instanceof NetworkError) {
                        Toast.makeText(ItemsList.this, "Network Error! please check connection...", Toast.LENGTH_SHORT).show();
                        Log.e("errorrrrrr",error.toString());
                    } else if (error instanceof ParseError) {
                        Toast.makeText(ItemsList.this, "Failed to get data, please try again..."+error.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ItemsList.this, "No more result available..."+error.toString(), Toast.LENGTH_SHORT).show();
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
            RecItems.setNestedScrollingEnabled(false);
            RecItems.setLayoutManager(layoutManagerItem);
            RecItems.setAdapter(mAdapterItem);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
//          Toast.makeText(ItemsList.this, "worked", Toast.LENGTH_SHORT).show();
        }
    }


//    class myTask_Item extends AsyncTask<Void, List, String> {
//
//
//        private int count=0;
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//
//            try {
//                lstItemPro.add(new Item_Dter.items_product("Electronics","Electronics", "1", "130$", R.drawable.img8));
//                lstItemPro.add(new Item_Dter.items_product("Dippers","Electronics", "1", "220$", R.drawable.img3));
//                lstItemPro.add(new Item_Dter.items_product("PLstics","Electronics", "1", "120$", R.drawable.im2));
//                publishProgress(lstItemPro);
//
//            } catch (Exception e) {
//                Toast.makeText(ItemsList.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//            return "All the styles have been loaded successfully";
//        }
//
//        @Override
//        protected void onProgressUpdate(List... values) {
//
//            RecItems.setNestedScrollingEnabled(false);
//            RecItems.setLayoutManager(layoutManagerItem);
//            RecItems.setAdapter(mAdapterItem);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
////            setProgressBarVisibility(false);
//        }
//    }

}

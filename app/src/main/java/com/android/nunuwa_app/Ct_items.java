package com.android.nunuwa_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nunuwa_app.Logic.logicToUIs;
import com.android.nunuwa_app.MyDpter.Ct_dpter;
import com.android.nunuwa_app.MyDpter.Recycler_Item_front;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.BubbleToggleView;

import java.util.ArrayList;
import java.util.List;

public class Ct_items extends AppCompatActivity {

    List<Ct_dpter.items> lst_ct_item;
    GridLayoutManager grlayoutManager3;
    Recycler_Item_front recycler_item_front;
    RecyclerView rec_Item_ct;
    TextView title_textItems;
    BubbleNavigationLinearView bubbleNavigation;
    ImageView shopCrt;
    TextView shopNum;
    BubbleToggleView my_home, c_myccout, myorders, c_item_rest, c_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_items);
        title_textItems = findViewById(R.id.title_textItems);
        lst_ct_item = new ArrayList<>();
        rec_Item_ct = findViewById(R.id.rec_Item_ct);
        grlayoutManager3 = new GridLayoutManager(this,1);
        recycler_item_front = new Recycler_Item_front(Ct_items.this, lst_ct_item);
        bubbleNavigation = findViewById(R.id.bot_navigation_Lin);

        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);

        try {
            //logicToUIs.BottomLogic(Ct_items.this, bubbleNavigation, 3);
        }catch (Exception e){
            Log.e("error: ", e.toString());
        }



        my_home = findViewById(R.id.myhome);
        c_myccout = findViewById(R.id.c_myccout);
        myorders = findViewById(R.id.myorders);
        c_item_rest = findViewById(R.id.c_item_rest);
        c_search = findViewById(R.id.c_search);


        c_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ct_items.this, Search_ui.class));
            }
        });

        my_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ct_items.this, MainActivity.class));
                //my_home.toggle();
            }
        });

        c_myccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ct_items.this, my_account.class));
//                c_myccout.toggle();
            }
        });

        c_item_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ct_items.this, Ct_items.class));
                c_item_rest.toggle();
            }
        });

        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ct_items.this, MyCart.class));
//                myorders.toggle();
            }
        });

        bubbleNavigation.setCurrentActiveItem(3);

        new myTask_Ct().execute();
    }


    public void toMenu(View view) {
        logicToUIs.conerMenu(Ct_items.this, view);
    }

    class myTask_Ct extends AsyncTask<Void, List, String> {

   private int count=0;
        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                lst_ct_item.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lst_ct_item.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                lst_ct_item.add(new Ct_dpter.items("Cups","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lst_ct_item.add(new Ct_dpter.items("Shoes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                lst_ct_item.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lst_ct_item.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                publishProgress(lst_ct_item);

            } catch (Exception e) {
                Toast.makeText(Ct_items.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return "All the styles have been loaded successfully";
        }

        @Override
        protected void onProgressUpdate(List... values) {

            rec_Item_ct.setNestedScrollingEnabled(false);
            rec_Item_ct.setLayoutManager(grlayoutManager3);
            rec_Item_ct.setAdapter(recycler_item_front);
        }

        @Override
        protected void onPostExecute(String result) {
            setProgressBarVisibility(false);
//            Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
        }
    }

}

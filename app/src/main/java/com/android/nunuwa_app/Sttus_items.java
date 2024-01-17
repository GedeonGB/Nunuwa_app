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
import com.android.nunuwa_app.MyDpter.Ct_dpter;
import com.android.nunuwa_app.MyDpter.sttus_Dpter;

import java.util.ArrayList;
import java.util.List;


public class Sttus_items extends AppCompatActivity {
    ImageView shopCrt;
    TextView shopNum;
    List<Ct_dpter.items> lstItem, lstItemStus;
    LinearLayoutManager layoutManager4;
    RecyclerView relSttus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sttus_items);

        relSttus = findViewById(R.id.relSttus);
//        shopCrt = findViewById(R.id.shopping_crt);
//        shopNum = findViewById(R.id.shopping_crt_num);
//        logicToUIs.getToCrt(this,  shopCrt, shopNum);
        lstItemStus = new ArrayList<>();
        layoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        new myTask_Sttus().execute();
    }

    public void toMenu(View view) {
        logicToUIs.conerMenu(Sttus_items.this, view);

    }

    class myTask_Sttus extends AsyncTask<Void, List, String> {

        sttus_Dpter mAdapter;
        private int count=0;

        @Override
        protected void onPreExecute() {

            mAdapter = new sttus_Dpter(Sttus_items.this, lstItemStus);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                lstItemStus.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img8));
                lstItemStus.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img7));
                lstItemStus.add(new Ct_dpter.items("Cups","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img8));
                lstItemStus.add(new Ct_dpter.items("Shoes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img9));
                lstItemStus.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lstItemStus.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img8));
                lstItemStus.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img7));
                lstItemStus.add(new Ct_dpter.items("Cups","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img8));
                lstItemStus.add(new Ct_dpter.items("Shoes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img9));
                lstItemStus.add(new Ct_dpter.items("Electronics","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img3));
                lstItemStus.add(new Ct_dpter.items("CLothes","Ssenoga", "here is the description of this ctegory, so the user my know wht is it dbout before clicking in it", R.drawable.img4));
                publishProgress(lstItemStus);

            } catch (Exception e) {
                Toast.makeText(Sttus_items.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

}

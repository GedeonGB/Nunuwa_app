package com.android.nunuwa_app;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.nunuwa_app.Logic.logicToUIs;


public class transaction_ui extends AppCompatActivity {

    ImageView shopCrt;
    TextView shopNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_ui);

        shopCrt = findViewById(R.id.shopping_crt);
        shopNum = findViewById(R.id.shopping_crt_num);
        logicToUIs.getToCrt(this,  shopCrt, shopNum);
    }

    public void toMenu(View view) {
        logicToUIs.conerMenu(transaction_ui.this, view);
    }

}

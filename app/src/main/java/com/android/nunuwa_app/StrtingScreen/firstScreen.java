package com.android.nunuwa_app.StrtingScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.nunuwa_app.MainActivity;
import com.android.nunuwa_app.R;

public class firstScreen extends AppCompatActivity {

    RelativeLayout relServiceHours, relComeTomYlOC;
    boolean isRelCOmeTO = false;
    Button idBtnBack;
    TextView num_pge_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        relComeTomYlOC = findViewById(R.id.relComeTomYlOC);
        relServiceHours = findViewById(R.id.relServiceHours);
        idBtnBack = findViewById(R.id.idBtnBack);
        num_pge_id = findViewById(R.id.num_pge_id);
    }

    public void ComeToMyLoc(View view) {

    }

    public void btnSearchMap(View view) {
    }

    public void idBtnNext(View view) {
        if (!isRelCOmeTO) {
            idBtnBack.setVisibility(View.VISIBLE);
            relServiceHours.setVisibility(View.GONE);
            num_pge_id.setText("2/2");
            relComeTomYlOC.setVisibility(View.VISIBLE);
            isRelCOmeTO=true;
        } else {
            startActivity(new Intent(firstScreen.this, MainActivity.class));
        }
    }

    public void idBtnBack(View view) {
        idBtnBack.setVisibility(View.GONE);
        relServiceHours.setVisibility(View.VISIBLE);
        num_pge_id.setText("1/2");
        relComeTomYlOC.setVisibility(View.GONE);
        isRelCOmeTO=false;
    }


}

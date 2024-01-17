package com.android.nunuwa_app.Logic;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.android.nunuwa_app.ContactUs;
import com.android.nunuwa_app.Ct_items;
import com.android.nunuwa_app.MainActivity;
import com.android.nunuwa_app.MyCart;
import com.android.nunuwa_app.R;
import com.android.nunuwa_app.Sign_out_Activity;
import com.android.nunuwa_app.my_account;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class logicToUIs {
    Context context;


    public static void getToCrt(final Context context, ImageView shopCrt, TextView shopCrt_num) {
        //shopcrt_num will be updted when connected to bckend
        shopCrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MyCart.class));
            }
        });
    }


    public static void conerMenu(final Context context, View view){
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_right, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                    switch (item.getItemId()) {
                    case R.id.toSignIn:
                        Intent i = new Intent(new Intent(context, Sign_out_Activity.class));
                        i.putExtra("from", "signin");
                        context.startActivity(i);
                        return true;

                    case R.id.toSignUp:
                        Intent iSUp = new Intent(new Intent(context, Sign_out_Activity.class));
                        iSUp.putExtra("from", "signup");
                        context.startActivity(iSUp);
                        return true;

                    case R.id.lngue:
                        context.startActivity(new Intent(context, my_account.class));
                        return true;

                    case R.id.contct:
                        context.startActivity(new Intent(context, ContactUs.class));
                        return true;

                }
                return true;
            }
        });
        popupMenu.show();

    }


    public static void BottomLogic(final Context context, final BubbleNavigationLinearView bubbleNavigation, int i) {
        bubbleNavigation.setCurrentActiveItem(i);
        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {

                    case R.id.myhome:
                        Log.e("clicked", "position"+position);
                        context.startActivity(new Intent(context, MainActivity.class));
                        return;

                    case R.id.fvorite:
                        Log.e("clicked", "position"+position);
                        context.startActivity(new Intent(context, ContactUs.class));
                        return;

                    case R.id.c_myccout:
                        Log.e("clicked", "position"+position);
                        context.startActivity(new Intent(context, my_account.class));
                        return;
                    case R.id.myct:
                        Log.e("clicked", "position"+position);
                        context.startActivity(new Intent(context, Ct_items.class));
                        return;

                    case R.id.myorders:
                        Log.e("clicked", "position"+position);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                context.startActivity(new Intent(context, MyCart.class));
                                }
                        }, 600);
                }}
        });
    }
}

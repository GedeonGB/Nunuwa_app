package com.android.nunuwa_app.MyDpter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nunuwa_app.R;
import com.android.nunuwa_app.item_desc;
import com.android.nunuwa_app.transaction_ui;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gb on 10/30/19.
 */

public class Recycler_Cart extends RecyclerView.Adapter<Recycler_Cart.MyViewHolderItem> {


    private Context aContext;

    private final int SPLASH_TIME_OUT = 900;
    private List<Ct_dpter.items> aData;
    Boolean LinIsVisible = false;


    AlertDialog.Builder alert;
    Animation zoomOut;

    public Recycler_Cart(Context aContext, List<Ct_dpter.items> aData) {
        this.aContext = aContext;
        this.aData = aData;
    }


    @Override

    public Recycler_Cart.MyViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(aContext);
        view = inflater.inflate(R.layout.crd_profile, parent, false);

        alert = new AlertDialog.Builder(aContext);

        return new Recycler_Cart.MyViewHolderItem(view);
    }


    @Override

    public void onBindViewHolder(final Recycler_Cart.MyViewHolderItem holder, final int position) {

        final String itemNameStr = aData.get(position).getCt_item_name();
        final String itemDateStr = aData.get(position).getCt_item_id();
        final String itemShopSTr = aData.get(position).getCt_item_name();
        final int imgInt = aData.get(position).getCt_item_image();
        holder.datetxt.setText(itemDateStr);
        holder.nametxt.setText(itemNameStr);
        holder.shoptxt.setText(itemShopSTr);
        Picasso.with(aContext).load(imgInt).into(holder.imageItem);

//        Blurry.with(aContext).capture(view).into(holder.imageItem);

        Picasso.with(aContext).load(imgInt).into(holder.imageItem);

        holder.btnPLusCrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aContext, "Value added", Toast.LENGTH_SHORT).show();
            }
        });

        LinIsVisible = false;

        holder.imageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LinIsVisible) {

                    LinIsVisible=true;
                } else {

                    LinIsVisible=false;
                }
            }
        });
//
        //holder.rel_item_progress.setVisibility(View.GONE);



//        holder.cardImtem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(aContext, "Clicked", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(aContext, itemDescription.class);
//                i.putExtra("itemName",itemNameStr);
//                i.putExtra("itemDate",itemDateStr);
//                i.putExtra("itemShop",itemShopSTr);
//                i.putExtra("itemImg",imgInt);
//                aContext.startActivity(i);
//
//            }
//        });



    }

    @Override

    public int getItemCount() {
        return aData.size();
    }

    public static class MyViewHolderItem extends RecyclerView.ViewHolder {

        TextView datetxt;
        TextView nametxt;
        TextView shoptxt, pricetxt;
        ImageView imageItem;
        TextView btnPLusCrt, btnMinusCrt;


        public MyViewHolderItem(View itemView) {
            super(itemView);


            datetxt = (TextView) itemView.findViewById(R.id.itemDte);
            pricetxt = itemView.findViewById(R.id.itemPrice);
            nametxt = (TextView) itemView.findViewById(R.id.itemName);
            shoptxt = (TextView) itemView.findViewById(R.id.itemSHop);
            imageItem = (ImageView) itemView.findViewById(R.id.ItemImageCart);
            btnPLusCrt = itemView.findViewById(R.id.btnPlusCrt);

        }
    }
}


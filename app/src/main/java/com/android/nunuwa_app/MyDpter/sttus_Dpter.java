package com.android.nunuwa_app.MyDpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nunuwa_app.R;
import com.android.nunuwa_app.Sttus_items;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class sttus_Dpter extends RecyclerView.Adapter<sttus_Dpter.MyViewHolderItem> {


    private Context aContext;

    private final int SPLASH_TIME_OUT = 900;
    private List<Ct_dpter.items> aData;
    AlertDialog.Builder alert;

    public sttus_Dpter(Context aContext, List<Ct_dpter.items> aData) {
        this.aContext = aContext;
        this.aData = aData;
    }


    @Override

    public sttus_Dpter.MyViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(aContext);
        view = inflater.inflate(R.layout.item_cercle_sttus, parent, false);
        alert = new AlertDialog.Builder(aContext);
        return new sttus_Dpter.MyViewHolderItem(view);
    }


    @Override

    public void onBindViewHolder(final sttus_Dpter.MyViewHolderItem holder, final int position) {

        final int imgInt = aData.get(position).getCt_item_image();
        holder.nametxt.setText(aData.get(position).getCt_item_name());
        Picasso.with(aContext).load(imgInt).into(holder.cirImgSttus);

        holder.cirImgSttus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, Sttus_items.class));
            }
        });
//        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                holder.rel_item_progress.setVisibility(View.VISIBLE);
//
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Intent i = new Intent(aContext, item_desc.class);
//                        i.putExtra("itemName",itemNameStr);
//                        i.putExtra("itemDate",itemDateStr);
//                        i.putExtra("itemShop",itemShopSTr);
//                        i.putExtra("itemImg",imgInt);
//                        aContext.startActivity(i);
//                    }
//                }, SPLASH_TIME_OUT);
//
//
//            }
//        });

//        holder.cardImtem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(aContext, "Clicked", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(aContext, item_desc.class);
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

        TextView nametxt, txt_id;
        CircleImageView cirImgSttus;


        public MyViewHolderItem(View itemView) {
            super(itemView);
            nametxt = itemView.findViewById(R.id.txt_sttus);
            cirImgSttus = itemView.findViewById(R.id.cirImgSttus);

        }
    }
}

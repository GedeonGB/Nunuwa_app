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

import com.android.nunuwa_app.Ct_items;
import com.android.nunuwa_app.ItemsList;
import com.android.nunuwa_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Ct_dpter extends RecyclerView.Adapter<Ct_dpter.MyViewHolderItem> {


    private Context aContext;
    private final int SPLASH_TIME_OUT = 900;
    private List<Ct_dpter.items> aData;
    AlertDialog.Builder alert;

    public Ct_dpter(Context aContext, List<items> aData) {
        this.aContext = aContext;
        this.aData = aData;
    }


    @Override

    public Ct_dpter.MyViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(aContext);
        view = inflater.inflate(R.layout.crd_ct, parent, false);
        return new Ct_dpter.MyViewHolderItem(view);
    }


    @Override

    public void onBindViewHolder(final Ct_dpter.MyViewHolderItem holder, final int position) {


        final int imgInt = aData.get(position).getCt_item_image();
        holder.nametxt.setText(aData.get(position).getCt_item_name());
        Picasso.with(aContext).load(imgInt).into(holder.imageItem);
        holder.cardImtem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, Ct_items.class));
            }
        });

        holder.nametxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, ItemsList.class));
            }
        });
//        Toast.makeText(aContext, "doInbcg", Toast.LENGTH_SHORT).show();

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

        TextView nametxt;
        ImageView imageItem;
        CardView cardImtem;


        public MyViewHolderItem(View itemView) {
            super(itemView);
            nametxt = itemView.findViewById(R.id.txt_ct_crd);
            imageItem = itemView.findViewById(R.id.img_ct_crd);
            cardImtem = itemView.findViewById(R.id.crd_ct_id);

        }
    }

    public static class items {
        private String ct_item_name;
        private String ct_item_id, short_desc;
        private int ct_item_image;


        public items(String ItemNameStr, String ct_item_id, String short_desc, int ct_item_image) {
            this.ct_item_name = ItemNameStr;
            this.ct_item_id = ct_item_id;
            this.short_desc = short_desc;
            this.ct_item_image = ct_item_image;
        }

        public int getCt_item_image() {
            return ct_item_image;
        }

        public void setCt_item_image(int ct_item_image) {
            this.ct_item_image = ct_item_image;
        }

        public String getCt_item_id() {
            return ct_item_id;
        }

        public void setCt_item_id(String ct_item_id) {
            this.ct_item_id = ct_item_id;
        }

        public String getCt_item_name() {
            return ct_item_name;
        }

        public void setCt_item_name(String ct_item_name) {
            this.ct_item_name = ct_item_name;
        }

        public String getShort_desc() {
            return short_desc;
        }

        public void setShort_desc(String short_desc) {
            this.short_desc = short_desc;
        }
    }
}


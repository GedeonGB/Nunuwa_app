package com.android.nunuwa_app.MyDpter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nunuwa_app.R;
import com.android.nunuwa_app.item_desc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Item_Dter extends RecyclerView.Adapter<Item_Dter.MyViewHolderItem> {


    private Context aContext;

    private final int SPLASH_TIME_OUT = 900;
    private List<items_product> aData;


    AlertDialog.Builder alert;
    Animation zoomOut;

    public Item_Dter(Context aContext, List<items_product> aData) {
        this.aContext = aContext;
        this.aData = aData;
    }

    @Override
    public Item_Dter.MyViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(aContext);
        view = inflater.inflate(R.layout.crd_item, parent, false);
        alert = new AlertDialog.Builder(aContext);
        return new Item_Dter.MyViewHolderItem(view);
    }


    @Override

    public void onBindViewHolder(final Item_Dter.MyViewHolderItem holder, final int position) {

        //final int imgInt = aData.get(position).getItem_image();
        holder.txt_title.setText(aData.get(position).getItem_name());
        holder.txt_price.setText(aData.get(position).getItem_price());
   //     Picasso.with(aContext).load(imgInt).into(holder.img_item);
        //Toast.makeText(aContext, "worked", Toast.LENGTH_SHORT).show();

        holder.txt_detils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, item_desc.class));
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Picasso.with(aContext).load(MyData.URL+aData.get(position).getItem_image()).into(holder.ItemImg);
                Picasso.with(aContext).load(aData.get(position).getItem_image()).into(holder.img_item);
                //holder.mkLoader.setVisibility(View.GONE);
            }
        }, 1000);
        holder.shooping_crt_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aContext, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                holder.rel_item_progress.setVisibility(View.VISIBLE);
//
////                KProgressHUD.create(aContext)
//                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                        .setLabel("Please wait")
//                        .setDetailsLabel("loading details for "+itemNameStr)
//                        .setCancellable(true)
//                        .setAnimationSpeed(2)
//                        .setDimAmount(0.5f)
//                        .show();
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
//
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

        TextView txt_price, txt_title, txt_detils;
        ImageView img_item, shooping_crt_item;
        CardView cardImtem;

        public MyViewHolderItem(View itemView) {
            super(itemView);

            txt_price = itemView.findViewById(R.id.txt_item_price);
            shooping_crt_item = itemView.findViewById(R.id.shooping_crt_item);
            txt_title = itemView.findViewById(R.id.txt_item_title);
            img_item = itemView.findViewById(R.id.img_item_crd);
            txt_detils = itemView.findViewById(R.id.txt_detils);
            cardImtem = itemView.findViewById(R.id.crd_item_id);

        }
    }


    public static class items_product {
        private String item_name, item_id;
        private String item_price;
        private String item_date;
        private String item_image;

        public items_product(){

        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }


        public String getItem_date() {
            return item_date;
        }

        public void setItem_date(String item_date) {
            this.item_date = item_date;
        }

        public items_product(String ItemNameStr, String date, String item_id, String ItemPrice, String thumbnail) {
            this.item_date = date;
            this.item_name = ItemNameStr;
            this.item_id = item_id;
            this.item_price = ItemPrice;
            this.item_image = thumbnail;
        }

        public String getItem_image() {
            return item_image;
        }

        public void setItem_image(String item_image) {
            this.item_image = item_image;
        }

        public String getItem_price() {
            return item_price;
        }

        public void setItem_price(String item_price) {
            this.item_price = item_price;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }
    }
}
